package com.sensorsdata.focus.channel.msg.utils;

import com.sensorsdata.focus.channel.msg.PushChannelMsg;
import com.sensorsdata.focus.channel.msg.body.PushMsg;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@UtilityClass
@Slf4j
public class ChannelMsgUtil {
  private static String generateTaskKey(PushMsg pushTask) {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("title='").append(pushTask.getMsgTitle()).append('\'');
    stringBuilder.append(", text='").append(pushTask.getMsgContent()).append('\'');
    stringBuilder.append(", landingType=").append(pushTask.getLandingType());
    stringBuilder.append(", linkUrl='").append(pushTask.getLinkUrl()).append('\'');
    stringBuilder.append(", customized='").append(pushTask.getCustomized()).append('\'');
    return stringBuilder.toString();
  }

  private static Collection<List<PushChannelMsg>> groupByTaskContent(List<PushChannelMsg> pushChannelMsgList) {
    // 将内容相同的推送任务聚合
    Map<String, List<PushChannelMsg>> groupByNode = new LinkedHashMap<>();
    for (PushChannelMsg task : pushChannelMsgList) {
      String key = generateTaskKey(task.getPushMsg());
      List<PushChannelMsg> sameContentTaskList = groupByNode.computeIfAbsent(key, k -> new ArrayList<>());
      sameContentTaskList.add(task);
    }
    return groupByNode.values();
  }

  /**
   * 按推送内容分组，generateTaskKey 相同为内容相同，每组最多 batchSize 个任务
   */
  public static Collection<List<PushChannelMsg>> groupByTaskContent(List<PushChannelMsg> messagingTasks,
      int batchSize) {
    Collection<List<PushChannelMsg>> groupByCollection = groupByTaskContent(messagingTasks);
    List<List<PushChannelMsg>> result = new ArrayList<>();
    for (List<PushChannelMsg> oneGroup : groupByCollection) {
      if (oneGroup.size() < batchSize) {
        result.add(oneGroup);
      } else {
        for (int i = 0; i < oneGroup.size(); i += batchSize) {
          result.add(oneGroup.subList(i, Math.min(i + batchSize, oneGroup.size())));
        }
      }
    }
    return result;
  }

  private static final String SF_DATA_PLACEHOLDER = "{sf_data}";
  private static final String SF_CUSTOMIZED_PLACEHOLDER = "{sf_customized}";
  private static final String SF_CUSTOMIZED_PLACEHOLDER_WITH_END_SEMICOLON = "{sf_customized};";

  public static String generateIntentFromTemplate(PushChannelMsg pushChannelMsg, String channelLevel3rdIntentTemplate) {
    PushMsg pushMsg = pushChannelMsg.getPushMsg();
    String intentTemplate = channelLevel3rdIntentTemplate;
    if (StringUtils.isNotBlank(intentTemplate)) {
      String encodedSfData;
      try {
        encodedSfData = URLEncoder.encode(pushChannelMsg.getSfData(), "UTF-8");
      } catch (UnsupportedEncodingException e) {
        log.error("can't do url encode. [sf_data='{}']", pushChannelMsg.getSfData());
        // 不支持 UTF-8 编码会触发这个异常，但不可能啊
        throw new RuntimeException(e);
      }
      String intent = StringUtils.replace(intentTemplate, SF_DATA_PLACEHOLDER, encodedSfData);
      if (pushMsg.getCustomized() != null && !pushMsg.getCustomized().isEmpty()) {
        for (Map.Entry<String, String> customPair : pushMsg.getCustomized().entrySet()) {
          String key = String.format("{sf_customized.%s}", customPair.getKey());
          intent = StringUtils.replace(intent, key, customPair.getValue());
        }
      }

      int customizedIndex = intent.indexOf(SF_CUSTOMIZED_PLACEHOLDER);
      if (customizedIndex >= 0) {
        StringBuilder paramBuilder = new StringBuilder();
        for (Map.Entry<String, String> customPair : pushMsg.getCustomized().entrySet()) {
          if (StringUtils.isBlank(customPair.getKey())) {
            continue;
          }
          paramBuilder.append(String.format("S.%s=%s;", customPair.getKey(), customPair.getValue()));
        }

        int placeHolderEndIndex = SF_CUSTOMIZED_PLACEHOLDER.length() + customizedIndex;
        String placeHolder = SF_CUSTOMIZED_PLACEHOLDER;
        if (placeHolderEndIndex < intent.length() && intent.charAt(placeHolderEndIndex) == ';') {
          placeHolder = SF_CUSTOMIZED_PLACEHOLDER_WITH_END_SEMICOLON;
        }
        intent = StringUtils.replace(intent, placeHolder, paramBuilder.toString());
      }
      return intent;
    }
    return null;
  }


}

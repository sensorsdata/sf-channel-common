/*
 * Copyright 2019 Sensors Data Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sensorsdata.focus.channel.push;


import com.sensorsdata.focus.channel.entry.MessagingTask;
import com.sensorsdata.focus.channel.entry.PushTask;

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
public class PushTaskUtils {

  private static String generateTaskKey(PushTask pushTask) {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("title='").append(pushTask.getMsgTitle()).append('\'');
    stringBuilder.append(", text='").append(pushTask.getMsgContent()).append('\'');
    stringBuilder.append(", landingType=").append(pushTask.getLandingType());
    stringBuilder.append(", linkUrl='").append(pushTask.getLinkUrl()).append('\'');
    stringBuilder.append(", customized='").append(pushTask.getCustomized()).append('\'');
    return stringBuilder.toString();
  }

  private static Collection<List<MessagingTask>> groupByTaskContent(List<MessagingTask> messagingTasks) {
    // 将内容相同的推送任务聚合
    Map<String, List<MessagingTask>> groupByNode = new LinkedHashMap<>();
    for (MessagingTask task : messagingTasks) {
      String key = generateTaskKey(task.getPushTask());
      List<MessagingTask> sameContentTaskList = groupByNode.computeIfAbsent(key, k -> new ArrayList<>());
      sameContentTaskList.add(task);
    }
    return groupByNode.values();
  }

  /**
   * 按推送内容分组，generateTaskKey 相同为内容相同，每组最多 batchSize 个任务
   */
  public static Collection<List<MessagingTask>> groupByTaskContent(List<MessagingTask> messagingTasks,
      int batchSize) {
    Collection<List<MessagingTask>> groupByCollection = groupByTaskContent(messagingTasks);
    List<List<MessagingTask>> result = new ArrayList<>();
    for (List<MessagingTask> oneGroup : groupByCollection) {
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

  public static String generateIntentFromTemplate(PushTask pushTask, String intentTemplate) {
    // TODO: 优化
    String encodedSfData;
    try {
      encodedSfData = URLEncoder.encode(pushTask.getSfData(), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      log.error("can't do url encode. [sf_data='{}']", pushTask.getSfData());
      throw new RuntimeException(e);
    }
    String intent = StringUtils.replace(intentTemplate, SF_DATA_PLACEHOLDER, encodedSfData);

    if (pushTask.getCustomized() != null) {
      for (Map.Entry<String, String> customPair : pushTask.getCustomized().entrySet()) {
        String key = String.format("{sf_customized.%s}", customPair.getKey());
        intent = StringUtils.replace(intent, key, customPair.getValue());
      }
    }

    int customizedIndex = intent.indexOf(SF_CUSTOMIZED_PLACEHOLDER);
    if (customizedIndex >= 0) {
      StringBuilder paramBuilder = new StringBuilder();
      if (pushTask.getCustomized() != null) {
        for (Map.Entry<String, String> customPair : pushTask.getCustomized().entrySet()) {
          if (StringUtils.isBlank(customPair.getKey())) {
            continue;
          }
          paramBuilder.append(String.format("S.%s=%s;", customPair.getKey(), customPair.getValue()));
        }
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
}

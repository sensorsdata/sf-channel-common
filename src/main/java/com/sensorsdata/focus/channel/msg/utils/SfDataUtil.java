package com.sensorsdata.focus.channel.msg.utils;

import com.sensorsdata.focus.channel.entry.LandingType;
import com.sensorsdata.focus.channel.msg.ExtraData;
import com.sensorsdata.focus.channel.msg.PushChannelMsg;
import com.sensorsdata.focus.channel.msg.body.PushMsg;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * sfData 是 push task 的第一级字段与 customized merge 后的结果.
 * 这是一个特殊逻辑.
 * 我将其单独放在一个类里，以强化其存在感.
 * <p>
 * 返回值为 sf_data 字段的结构.
 * 其 "形状" 见 http://doc.sensorsdata.cn/pages/viewpage.action?pageId=16037066 文档定义.
 */
@UtilityClass
public class SfDataUtil {

  private static Object instance = new Object();
  /**
   * 这是一段比较恶心的 merge 代码.
   *
   * @param messagingTask
   * @return
   * @throws java.io.IOException
   */
  public static LinkedHashMap<String,Object> buildSfData(PushChannelMsg messagingTask) throws IOException {

    ExtraData extraData = new ExtraData();
    if (messagingTask.getExtraData()!=null){
      extraData = messagingTask.getExtraData();
    }
    PushMsg pushTask = messagingTask.getPushMsg();
    LinkedHashMap<String,Object> sfDataNode = new LinkedHashMap<>();
    LinkedHashMap<String,Object> customizedNode = new LinkedHashMap<>();

    if (pushTask.getCustomized()!=null && !pushTask.getCustomized().isEmpty()) {
      for (Map.Entry<String, String> entry : pushTask.getCustomized().entrySet()) {
        if (StringUtils.isBlank(entry.getKey())) {
          continue;
        }
        customizedNode.put(entry.getKey(), entry.getValue());
      }
    }
    sfDataNode.put("customized", customizedNode);
    if (pushTask.getLandingType().equals(LandingType.LINK)) {
      sfDataNode.put("sf_link_url", pushTask.getLinkUrl());
    }
    sfDataNode.put("sf_landing_type", pushTask.getLandingType().toString());

    sfDataNode.put("sf_msg_id", messagingTask.getChannelMsg().getMsgId()); // random 一个.


    sfDataNode.put("sf_plan_id", messagingTask.getChannelMsg().getExtraData());

    sfDataNode.put("sf_audience_id", String.valueOf(extraData.getPlanAudienceId()));

    sfDataNode.put("sf_plan_strategy_id",
        Optional.ofNullable(extraData.getPlanStrategyId()).map(Object::toString).orElse(null));

    sfDataNode.put("sf_strategy_unit_id",
        Optional.ofNullable(extraData.getStrategyUnitId()).map(Object::toString).orElse(null));

    sfDataNode.put("sf_plan_type", extraData.getPlanType());

    return sfDataNode;
  }




}

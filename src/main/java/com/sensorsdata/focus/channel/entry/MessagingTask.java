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

package com.sensorsdata.focus.channel.entry;

import lombok.Data;

import java.util.Map;

@Data
public class MessagingTask {

  /**
   * 长整数，在神策分析中用户的唯一标识
   */
  private Long userId;

  /**
   * 用户属性
   */
  private Map<String, Object> userProfile;

  /**
   * 神策项目 ID
   */
  private Integer projectId;

  /**
   * 神策项目名
   */
  private String projectName;

  /**
   * 消息 ID
   */
  private String msgId;

  /**
   * 运营计划 ID
   */
  private Integer planId;

  /**
   * 受众 ID
   */
  private Integer planAudienceId;
  /**
   * 计划受众计算时间
   */
  private Long planAudienceEntryTime;
  /**
   * 实验组 ID
   */
  private Integer planStrategyId;
  /**
   * 策略器 ID
   */
  private Integer strategyUnitId;
  /**
   * 计划类型
   */
  private String planType;
  /**
   * 发起发送消息的请求的时间
   */
  private Long triggerTime;

  /**
   * 发送通道 ID
   */
  private Integer channelId;

  /**
   * 推送任务
   */
  private PushTask pushTask;

  /**
   * 短信任务
   */
  protected TextMsgTask textMsgTask;

  /**
   * 是否发送成功
   */
  private boolean success = true;

  /**
   * 如果发送失败，失败的原因
   */
  private String failReason;
}

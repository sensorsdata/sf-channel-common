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
package com.sensorsdata.focus.channel.msg;

import lombok.Data;

import java.util.Map;

/**
 * 神策系统中的统一通道消息
 *
 * @version 1.0.0
 * @since 2020/06/22 13:39
 */
@Data
public class ChannelMsg {
  /** 神策系统 msgId 全局唯一 */
  private String msgId;
  /** 通道返回 msgId，在调用完通道接口之后需要设置此字段 */
  private String channelMsgId;

  /** 长整数，神策系统的用户唯一标识 */
  private Long userId;
  private Map<String, Object> userProfile;
  /** 神策项目 id */
  private Integer projectId;
  /** 神策项目名称 */
  private String projectName;
  /** 消息来源信息(产品线) */
  private MsgSource msgSource ;
  /**
   * 产品线来源相关的额外业务数据
   * 例如 神策智能运营产品线含如下字段：
   *    Integer planId - 计划 id，
   *    String planType - 计划类型(中文名称)，
   *    Integer planAudienceId - 受众 id，select all 的情况无值，
   *    Integer planStrategyId - 对照组/策略组，
   *    Integer strategyUnitId - 策略器 ID，
   *    Long enterPlanTime - 进入计划的时间
   */
  private Map<String, Object> extraData;
  /** 发起发送消息请求的时间，用于计算到发送完成的时间间隔或者收到消息的时间间隔 */
  private Long triggerTime;
  /** channel ID */
  private Integer channelId;

  /**
   * 通道消息体
   */
  private String body;

  /** 调用通道发送接口是否成功 */
  private boolean success = false;
  /** 如果调用通道发送接口失败，失败的原因 */
  private String failReason;

  /**
   * 消息来源定义
   */
  public enum MsgSource {
    EXTERNAL, SA, SP, SJ, SF, SR, SRM, SPS, SM, SDG, SFO, SPE, SPT, SCA
  }

}

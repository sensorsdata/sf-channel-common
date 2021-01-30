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
  /** 神策系统 msgId 全局唯一，用途：消息发送去重用的默认 sendId */
  private String msgId;
  /** 通道返回 msgId，在调用完通道接口之后需要设置此字段 */
  private String channelMsgId;
  /** 长整数，神策系统的用户唯一标识，用途：按userId去重*/
  private Long userId;
  /** 神策项目 id，用途：微信 client 中如果公众号未授权，则将计划变更未暂停 */
  private Integer projectId;
  /** channel ID，用途：微信 client 中如果公众号未授权，则将计划变更未暂停 */
  private Integer channelId;
  /** 发起发送消息请求的时间，用于计算到发送完成的时间间隔或者收到消息的时间间隔 */
  private Long triggerTime;
  /** 发送 ID，例如：对于微信就是 openId，对于短信就是手机号 */
  private String sendId ;

  /**
   * 通道消息体
   */
  private String body;

  /** 调用通道发送接口是否成功 */
  private boolean success = false;
  /** 如果调用通道发送接口失败，失败的原因 */
  private String failReason;

  /**
   * 神策扩展信息，可能包含如下几类信息
   *
   * 1. 产品组件来源相关的额外业务数据：
   *    例如 神策智能运营产品线含如下字段：
   *       Integer planId - 计划 id，
   *       String planType - 计划类型(中文名称)，
   *       Integer planAudienceId - 受众 id，select all 的情况无值，
   *       Integer planStrategyId - 对照组/策略组，
   *       Integer strategyUnitId - 策略器 ID，
   *       Long enterPlanTime - 进入计划的时间
   *  2. 消息来源信息(产品组件) ：
   *       MsgSource msgSource - 消息来源
   *  3.   String projectName - 项目名称
   *  4.   Map<String, Object> userProfile - 用户属性信息
   */
  private Map<String, Object> extraData;

}

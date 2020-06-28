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

  /**
   * 通道消息体
   */
  private String body;

  /** 调用通道发送接口是否成功 */
  private boolean success = false;
  /** 如果调用通道发送接口失败，失败的原因 */
  private String failReason;

  /**
   * 神策扩展信息
   */
  private ExtraData extraData;

}

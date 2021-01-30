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
 * 消息回执
 *
 * @version 1.0.0
 * @since 2020/06/27 19:51
 */
@Data
public class ReceiptOfMsg {
  /** channel ID */
  private String channelServiceCode;
  /** 通道返回 msgId，用于关联发送消息内容 */
  private String msgId;
  // 接收状态：SUCCESS 代表成功；非SUCCESS代表失败
  private String status;
  // 状态报告错误代码
  private String errCode;
  // 状态报告错误代码的描述
  private String errDesc;

  public enum ReceiptStatus{
    /** 成功 */
    SUCCESS,
    /** 失败 */
    FAILED,
    /** 用户拒收，目前只有微信模板消息回执中会有此状态 */
    USER_BLOCK,
  }
}

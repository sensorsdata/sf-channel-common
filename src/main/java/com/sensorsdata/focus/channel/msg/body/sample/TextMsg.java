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
package com.sensorsdata.focus.channel.msg.body.sample;

import lombok.Data;

import java.util.Map;

/**
 * 短信通道消息体
 *
 * @version 1.0.0
 * @since 2020/06/22 15:40
 */
@Data
public class TextMsg {
  /**
   * 短信模板 id
   */
  private String templateId;
  /**
   * 短信模板参数值
   */
  private Map<String, String> parameters;
  /**
   * 短信体
   */
  private String templateContent;
  /**
   * 手机号
   */
  private String phoneNumber;
  /**
   * 签名ID，该字段为空则使用应用默认签名（极光短信使用）
   */
  private Integer smsSign;
}

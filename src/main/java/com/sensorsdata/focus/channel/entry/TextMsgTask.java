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
public class TextMsgTask {

  /**
   * 手机号
   */
  private String phoneNumber;

  /**
   * 短信模板 ID
   */
  private String templateId;

  /**
   * 短信模板的参数，用来替换短信内容中的宏
   */
  private Map<String, String> parameters;

  /**
   * 模板的具体内容
   */
  private String templateContent;

  /**
   * 短信的签名 ID
   */
  private Integer smsSign;
}

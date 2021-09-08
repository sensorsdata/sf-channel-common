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
public class PushTask {

  /**
   * 客户端 ID，即推送 ID
   */
  private String clientId;

  /**
   * 推送标题
   */
  private String msgTitle;

  /**
   * 通知内容
   */
  private String msgContent;

  /**
   * 落地页类型
   */
  private LandingType landingType;

  /**
   * landingType=LINK 时，打开页面 url
   */
  private String linkUrl;

  /**
   * landingType=CUSTOMIZED 时，自定义的数据字段信息.
   */
  private Map<String, String> customized;

  /**
   * SF 内置的数据封装格式
   */
  private String sfData;

  /**
   * 通知栏图标
   */
  private String notificationIcon;

  /**
   * 通知栏高级设置
   */
  private String advancedSetting;

}

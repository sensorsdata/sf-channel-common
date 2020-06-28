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
 * 微信服务号模板消息体
 *
 * @version 1.0.0
 * @since 2020/06/22 16:06
 */
@Data
public class WechatTemplateMsg {
  /**
   * 公众号的用户的唯一标识
   */
  private String openId;
  /**
   * 微信模板 id
   */
  private String templateId;

  /**
   * 落地页类型
   */
  private WxLandingType landingType;

  /**
   * wxLandingType=H5 模式下，打开页面 url
   */
  private String url;

  /**
   * 跳小程序所需数据
   */
  private MiniprogramParam miniprogramParam;

  /**
   * 模板数据
   */
  private Map<String, TemplateData> data;

  @Data
  public static class TemplateData {
    private String value;
    private String color;
  }

  @Data
  public static class MiniprogramParam {
    private String appId;
    private String pagepath;
  }

  public enum  WxLandingType {
    H5,
    MINIPROGRAM,
    NONE;
  }

}

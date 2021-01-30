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
package com.sensorsdata.focus.channel.msg.body;

import com.sensorsdata.focus.channel.entry.LandingType;

import lombok.Data;

import java.util.Map;

/**
 * PUSH 消息体定义
 *
 * @version 1.0.0
 * @since 2020/06/22 14:35
 */
@Data
public class PushMsg {

  /**
   * 标题，不一定有，iOS 没有
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
   * LINK 类型，打开页面 url
   */
  private String linkUrl;

  /**
   * landingType=customized 模式下，自定义的数据字段信息.
   */
  private Map<String, String> customized;

}

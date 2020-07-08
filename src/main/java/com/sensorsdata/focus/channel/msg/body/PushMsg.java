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

  /**
   * 厂商通道 intent 模板，将其中的 SF_DATA 字符串替换成 json
   * 例如 intent:#Intent;action=com.sensors.test;component=com.sensorsdata.android.push/.TestActivity;S.test={sf_customized.news_id};S.sf_data={sf_data};end
   * 替换成 intent:#Intent;action=com.sensors.test;component=com.sensorsdata.android.push/.TestActivity;S.test=678;S.sf_data=%7B%22customized%22%3A%7B%22book_id%22%3A%22123456%22%2C%22news_id%22%3A%22678%22%2C%22push_time%22%3A%222019-08-13+12%3A12%3A27.468%22%7D%2C%22sf_landing_type%22%3A%22CUSTOMIZED%22%2C%22sf_msg_id%22%3A%22c8f276f6-a339-4d2b-8216-c34b2f6ec586%22%2C%22sf_plan_id%22%3A%223%22%2C%22sf_audience_id%22%3A%225%22%2C%22sf_audience_entry_time%22%3A1565669547442%2C%22sf_plan_strategy_id%22%3A%224%22%7D;end
   */
  private String intentTemplate;

  /**
   * 有的厂商通道需要 activity 字段
   */
  private String activityForNativeChannel;

}

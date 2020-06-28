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

import java.util.Map;

/**
 * 消息体处理类，用于构建实际消息体 json 串
 * @version 1.0.0
 * @since 2020/06/24 11:59
 */
public interface MsgBodyHandler {

  /**
   * 返回实际的消息体 json 串，如果有占位符，则进行替换
   *
   * @param msgBody           符合本消息体规范的消息体；
   *                          占位 key 的规则：${user.VIPgrade!\"123\"}"}
   *                          样例：{"template_id":"123","template_param_list":[{"name":"1","value":"${user.VIPgrade!\"123\"}"}],"template_content":null}
   * @param msgChannelConfig  通道配置
   *                          样例：{"url":"http://10.120.156.66:8997/webhook","method":"POST","plan_params_tpl":[{"name":"mo_var_1","cname":"你好","data_type":"STRING","number_edit_style":null,"string_edit_style":"RICH"}],"user_property_params":[{"name":"dong_key_1","value":"${user.getui_id!\"空省\"}"}]}
   * @param propsMap          属性 Map，用于替换消息体中的占位符
   * @return 实际消息对象 json 串
   * @throws java.lang.Exception 处理报错会抛出异常
   */
  String processBody(String msgBody, String msgChannelConfig, Map<String, Object> propsMap) throws Exception ;

}

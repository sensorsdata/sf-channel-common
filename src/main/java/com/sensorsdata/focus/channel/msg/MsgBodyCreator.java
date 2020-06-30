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

import java.util.Collections;
import java.util.Map;

/**
 * 消息体处理类，用于构建实际消息体 json 串
 * @version 1.0.0
 * @since 2020/06/24 11:59
 */
public abstract class MsgBodyCreator {
  /**
   * 使用 msgChannel 配置初始化 MsgBodyCreator
   * @param msgChannelConfig 通道配置
   *                         样例：{"url":"http://10.120.156.66:8997/webhook","method":"POST","plan_params_tpl":[{"name":"mo_var_1","cname":"你好","data_type":"STRING","number_edit_style":null,"string_edit_style":"RICH"}],"user_property_params":[{"name":"dong_key_1","value":"${user.getui_id!\"空省\"}"}]}
   */
  public MsgBodyCreator(String msgChannelConfig) {

  }

  /**
   * 返回实际的消息体 json 串，如果有占位符，则进行替换
   *
   * @param msgTemplate       符合本消息体规范的消息体模板；
   *                          占位 key 的规则：${user.VIPgrade!\"123\"}"}
   *                          样例：{"template_id":"123","template_param_list":[{"name":"1","value":"${user.VIPgrade!\"123\"}"}],"template_content":null}
   *
   * @param propsMap          属性 Map，用于替换消息体中的占位符
   * @return 实际消息对象 json 串
   * @throws java.lang.Exception 处理报错会抛出异常
   */
  public abstract String createMsgBody(String msgTemplate, Map<String, Object> propsMap) throws Exception ;

  /**
   * 根据实际消息体返回对应的神策埋点事件属性 Map；如果需要对消息体进行神策事件埋点，请在子类中实现此方法。
   *
   * 事件属性规范：
   *    PUSH：$sf_msg_title, $sf_msg_content, $sf_link_url
   *    短信：$sf_msg_content : 模板id or 短信内容
   *    微信：$sf_msg_content : 模板id ， $sf_link_url ：H5 跳转链接
   *    WebHook：$sf_msg_content : parameters
   * 此接口返回的事件属性影响：只影响事件筛选，不影响业务逻辑执行
   *    比如：已经配置好的计划中使用了 $sf_link_url ，如果没有返回这个属性的埋点值，则无法筛选出对应的结果数据，会对统计结果造成影响
   *
   * @param msgBody    待发送的消息体
   * @return 神策埋点需要事件属性 Map
   * @throws Exception 处理报错会抛出异常
   */
  public Map<String, Object> getEventProperties(String msgBody) throws Exception {
    return Collections.emptyMap() ;
  }

}

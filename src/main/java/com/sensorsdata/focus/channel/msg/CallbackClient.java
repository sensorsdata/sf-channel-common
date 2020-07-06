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

import java.util.List;
import java.util.Map;

/**
 * 自定义消息回执接收 client 需要实现此接口
 *
 * @version 1.0.0
 * @since 2020/06/27 16:42
 */
public interface CallbackClient {

  /**
   * 消息验签并获取统一消息回执
   * @param channelServiceCode 通道厂商代码
   * @param paramMap  回执请求参数
   * @return 消息回执
   * @throws java.lang.Exception 在验签失败或者其他异常的情况下会抛出异常
   */
  List<ReceiptOfMsg> verifyAndGetReceipt(String channelServiceCode, Map<String, String> paramMap) throws Exception ;

}

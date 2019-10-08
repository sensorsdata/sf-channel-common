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

package com.sensorsdata.focus.channel;

import com.sensorsdata.focus.channel.entry.MessagingTask;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * client 需要继承自该抽象类。
 * 实现类会被按如下描述使用：
 * 1. 通过反射创建该类的实例；
 * 2. 调用 initChannelClient(channelConfig)，参数为 @SfChannelConfig 注解的类；
 * 3. 调用 send(messagingTasks) 接口。在一个实例的生命周期内可能会被多次调用 send(messagingTasks) 接口；
 * 4. 调用 close() 关闭资源
 */
public abstract class ChannelClient implements Closeable {

  /**
   * 初始化 ChannelClient。
   *
   * @param channelConfig 被注解 @SfChannelConfig 并继承 ChannelConfig 的类实例，参数来自前端配置
   */
  public abstract void initChannelClient(ChannelConfig channelConfig) throws Exception;

  /**
   * 触发一批任务发送。一个实例可能会被多次调用该接口。
   *
   * @param messagingTasks 需要发送的数据
   */
  public abstract void send(List<MessagingTask> messagingTasks) throws Exception;

  /**
   * 关闭 client。如果需要释放资源，请在子类实现该函数。
   */
  @Override
  public void close() throws IOException {
  }
}

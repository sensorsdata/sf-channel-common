package com.sensorsdata.focus.channel.msg;

/**
 * 消息体创建工厂
 *
 * @version 1.0.0
 * @since 2020/06/29 12:17
 */
public interface MsgBodyFactory {

  /**
   *  返回消息创建器
   * @param msgChannelId  通道 ID
   * @return MsgBodyCreator 对象
   */
  MsgBodyCreator getMsgBodyCreator(Integer msgChannelId) ;

}

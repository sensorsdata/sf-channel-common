package com.sensorsdata.focus.channel.msg;

import com.sensorsdata.focus.channel.msg.body.PushMsg;
import com.sensorsdata.focus.channel.msg.utils.SfDataUtil;

import com.alibaba.fastjson.JSON;

/**
 * 业务逻辑需要的 PUSH MSG 对象
 */
public class PushChannelMsg {

  /**
   * 神策系统中的统一通道消息
   */
  private ChannelMsg channelMsg;

  /**
   * PUSH 消息体对象 channelMsg body 转换
   */
  private PushMsg pushMsg;

  /**
   * channelMsg extraData 对象
   */
  private ExtraData extraData;

  /**
   * 转换的 sfData
   */
  private String sfData;

  public ChannelMsg getChannelMsg() {
    return channelMsg;
  }

  public PushMsg getPushMsg() {
    return pushMsg;
  }

  public ExtraData getExtraData() {
    return extraData;
  }

  public String getSfData() {
    return sfData;
  }

  private PushChannelMsg buildChannelMsg(ChannelMsg channelMsg){
    this.channelMsg = channelMsg;
    return this;
  }

  private PushChannelMsg buildPushMsg(PushMsg pushMsg){
    this.pushMsg = pushMsg;
    return this;
  }

  private PushChannelMsg buildExtraData(ExtraData extraData){
    this.extraData = extraData;
    return this;
  }
  private PushChannelMsg buildSfData(String sfData){
    this.sfData = sfData;
    return this;
  }

  public static PushChannelMsg build(ChannelMsg channelMsg) throws Exception {
    PushMsg pushMsg = JSON.parseObject(channelMsg.getBody(), PushMsg.class);
    String extraDataStr = JSON.toJSONString(channelMsg.getExtraData());
    ExtraData extraData = JSON.parseObject(extraDataStr, ExtraData.class);
    PushChannelMsg pushChannelMsg = new PushChannelMsg().
        buildChannelMsg(channelMsg).
        buildPushMsg(pushMsg).
        buildExtraData(extraData);
    String sfData = JSON.toJSONString(SfDataUtil.buildSfData(pushChannelMsg));
    pushChannelMsg.buildSfData(sfData);
    return pushChannelMsg;
  }

}

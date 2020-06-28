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

import lombok.Data;

import java.util.Map;

/**
 * 额外扩展信息
 *
 * @version 1.0.0
 * @since 2020/06/28 17:33
 */
@Data
public class ExtraData {
  /** 用户属性信息 */
  private Map<String, Object> userProfile;
  /** 神策项目名称 */
  private String projectName;
  /** 消息来源信息(产品组件) */
  private MsgSource msgSource ;
  /**
   * 产品线来源相关的额外业务数据
   * 例如 神策智能运营产品线含如下字段：
   *    Integer planId - 计划 id，
   *    String planType - 计划类型(中文名称)，
   *    Integer planAudienceId - 受众 id，select all 的情况无值，
   *    Integer planStrategyId - 对照组/策略组，
   *    Integer strategyUnitId - 策略器 ID，
   *    Long enterPlanTime - 进入计划的时间
   */
  private Map<String, Object> sourceBizData;

}

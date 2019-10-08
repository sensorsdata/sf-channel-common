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

package com.sensorsdata.focus.channel.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于描述 @SfChannelConfig 的类里一个具体的配置项
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConfigField {

  /**
   * 配置显式名称，例如 “推送平台密钥 A”
   */
  String cname() default "";

  /**
   * 前端展示的对配置的描述，例如：“可以在推送平台配置界面取到，长度为 6 个字符，如 ABCDEF”
   */
  String desc() default "";

  /**
   * 字段展示到前端的默认值
   */
  String defaultValue() default "";

  /**
   * 前端是否使用密码输入框
   */
  boolean secret() default false;
}
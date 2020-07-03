package com.sensorsdata.focus.channel.msg;

public class ExtraData {

  /**
   * 计划 id
   */
  private Integer planId;

  /**
   * 计划类型(中文名称)
   */
  private String planType;

  /**
   * 受众 id，select all 的情况无值
   */
  private Integer planAudienceId;

  /**
   * 对照组/策略组
   */
  private Integer planStrategyId;

  /**
   * 策略器 ID
   */
  private Integer strategyUnitId;

  /**
   * 进入计划的时间
   */
  private Long enterPlanTime;

  public Integer getPlanId() {
    return planId;
  }

  public void setPlanId(Integer planId) {
    this.planId = planId;
  }

  public String getPlanType() {
    return planType;
  }

  public void setPlanType(String planType) {
    this.planType = planType;
  }

  public Integer getPlanAudienceId() {
    return planAudienceId;
  }

  public void setPlanAudienceId(Integer planAudienceId) {
    this.planAudienceId = planAudienceId;
  }

  public Integer getPlanStrategyId() {
    return planStrategyId;
  }

  public void setPlanStrategyId(Integer planStrategyId) {
    this.planStrategyId = planStrategyId;
  }

  public Integer getStrategyUnitId() {
    return strategyUnitId;
  }

  public void setStrategyUnitId(Integer strategyUnitId) {
    this.strategyUnitId = strategyUnitId;
  }

  public Long getEnterPlanTime() {
    return enterPlanTime;
  }

  public void setEnterPlanTime(Long enterPlanTime) {
    this.enterPlanTime = enterPlanTime;
  }
}

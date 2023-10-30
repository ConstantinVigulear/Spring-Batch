package com.vigulear.spring_batch.model;

/**
 * @author Constantin Vigulear
 */
@SuppressWarnings("unused")
public class OverseasTradeIndexModel {
  private String seriesReference;
  private Float period;
  private Float dataValue;
  private String status;
  private String units;
  private Integer magnitude;
  private String subject;
  private String group;
  private String seriesTitle1;
  private String seriesTitle2;
  private String seriesTitle3;
  private String seriesTitle4;
  private String seriesTitle5;

  public OverseasTradeIndexModel() {}

  public String getSeriesReference() {
    return seriesReference;
  }

  public OverseasTradeIndexModel setSeriesReference(String seriesReference) {
    this.seriesReference = seriesReference;return this;
  }

  public Float getPeriod() {
    return period;
  }

  public OverseasTradeIndexModel setPeriod(Float period) {
    this.period = period;return this;
  }

  public Float getDataValue() {
    return dataValue;
  }

  public OverseasTradeIndexModel setDataValue(Float dataValue) {
    this.dataValue = dataValue;return this;
  }

  public String getStatus() {
    return status;
  }

  public OverseasTradeIndexModel setStatus(String status) {
    this.status = status;return this;
  }

  public String getUnits() {
    return units;
  }

  public OverseasTradeIndexModel setUnits(String units) {
    this.units = units;return this;
  }

  public Integer getMagnitude() {
    return magnitude;
  }

  public OverseasTradeIndexModel setMagnitude(Integer magnitude) {
    this.magnitude = magnitude;return this;
  }

  public String getSubject() {
    return subject;
  }

  public OverseasTradeIndexModel setSubject(String subject) {
    this.subject = subject;return this;
  }

  public String getGroup() {
    return group;
  }

  public OverseasTradeIndexModel setGroup(String group) {
    this.group = group;return this;
  }

  public String getSeriesTitle1() {
    return seriesTitle1;
  }

  public OverseasTradeIndexModel setSeriesTitle1(String seriesTitle1) {
    this.seriesTitle1 = seriesTitle1;return this;
  }

  public String getSeriesTitle2() {
    return seriesTitle2;
  }

  public OverseasTradeIndexModel setSeriesTitle2(String seriesTitle2) {
    this.seriesTitle2 = seriesTitle2;return this;
  }

  public String getSeriesTitle3() {
    return seriesTitle3;
  }

  public OverseasTradeIndexModel setSeriesTitle3(String seriesTitle3) {
    this.seriesTitle3 = seriesTitle3;return this;
  }

  public String getSeriesTitle4() {
    return seriesTitle4;
  }

  public OverseasTradeIndexModel setSeriesTitle4(String seriesTitle4) {
    this.seriesTitle4 = seriesTitle4;
    return this;
  }

  public String getSeriesTitle5() {
    return seriesTitle5;
  }

  public OverseasTradeIndexModel setSeriesTitle5(String seriesTitle5) {
    this.seriesTitle5 = seriesTitle5;
    return this;
  }
}

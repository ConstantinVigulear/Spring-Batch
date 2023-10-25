package com.vigulear.spring_batch.entity;

import jakarta.persistence.*;

/**
 * @author Constantin Vigulear
 */
@Entity
@Table(name = "OVERSEAS_TRADE_INDEXES")
public class OverseasTradeIndexes {
  @Id
  @GeneratedValue
  @Column(name = "ID")
  private Long id;

  @Column(name = "SERIES_REFERENCE")
  private String seriesReference;

  @Column(name = "PERIOD")
  private Float period;

  @Column(name = "DATA_VALUE")
  private Float dataValue;

  @Column(name = "STATUS")
  private String status;

  @Column(name = "UNITS")
  private String units;

  @Column(name = "MAGNITUDE")
  private Integer magnitude;

  @Column(name = "SUBJECT")
  private String subject;

  @Column(name = "GROUP_VALUE")
  private String group;

  @Column(name = "SERIES_TITLE_1")
  private String seriesTitle1;

  @Column(name = "SERIES_TITLE_2")
  private String seriesTitle2;

  @Column(name = "SERIES_TITLE_3")
  private String seriesTitle3;

  @Column(name = "SERIES_TITLE_4")
  private String seriesTitle4;

  @Column(name = "SERIES_TITLE_5")
  private String seriesTitle5;

  public OverseasTradeIndexes() {}

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSeriesReference() {
    return seriesReference;
  }

  public void setSeriesReference(String seriesReference) {
    this.seriesReference = seriesReference;
  }

  public Float getPeriod() {
    return period;
  }

  public void setPeriod(Float period) {
    this.period = period;
  }

  public Float getDataValue() {
    return dataValue;
  }

  public void setDataValue(Float dataValue) {
    this.dataValue = dataValue;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }

  public Integer getMagnitude() {
    return magnitude;
  }

  public void setMagnitude(Integer magnitude) {
    this.magnitude = magnitude;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getSeriesTitle1() {
    return seriesTitle1;
  }

  public void setSeriesTitle1(String seriesTitle1) {
    this.seriesTitle1 = seriesTitle1;
  }

  public String getSeriesTitle2() {
    return seriesTitle2;
  }

  public void setSeriesTitle2(String seriesTitle2) {
    this.seriesTitle2 = seriesTitle2;
  }

  public String getSeriesTitle3() {
    return seriesTitle3;
  }

  public void setSeriesTitle3(String seriesTitle3) {
    this.seriesTitle3 = seriesTitle3;
  }

  public String getSeriesTitle4() {
    return seriesTitle4;
  }

  public void setSeriesTitle4(String seriesTitle4) {
    this.seriesTitle4 = seriesTitle4;
  }

  public String getSeriesTitle5() {
    return seriesTitle5;
  }

  public void setSeriesTitle5(String seriesTitle5) {
    this.seriesTitle5 = seriesTitle5;
  }
}
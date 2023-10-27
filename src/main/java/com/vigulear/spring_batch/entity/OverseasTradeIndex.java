package com.vigulear.spring_batch.entity;

import jakarta.persistence.*;

/**
 * @author Constantin Vigulear
 */
@Entity
@Table(name = "OVERSEAS_TRADE_INDEXES")
@SuppressWarnings("unused")
public class OverseasTradeIndex {
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

  @OneToOne(
      mappedBy = "overseasTradeIndex",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @PrimaryKeyJoinColumn
  private Series series;

  public OverseasTradeIndex() {
  }

  public Long getId() {
    return id;
  }

  @SuppressWarnings("unused")
  public OverseasTradeIndex setId(Long id) {
    this.id = id;
    return this;
  }

  public String getSeriesReference() {
    return seriesReference;
  }

  public OverseasTradeIndex setSeriesReference(String seriesReference) {
    this.seriesReference = seriesReference;
    return this;
  }

  public Float getPeriod() {
    return period;
  }

  public OverseasTradeIndex setPeriod(Float period) {
    this.period = period;
    return this;
  }

  public Float getDataValue() {
    return dataValue;
  }

  public OverseasTradeIndex setDataValue(Float dataValue) {
    this.dataValue = dataValue;
    return this;
  }

  public String getStatus() {
    return status;
  }

  public OverseasTradeIndex setStatus(String status) {
    this.status = status;
    return this;
  }

  public String getUnits() {
    return units;
  }

  public OverseasTradeIndex setUnits(String units) {
    this.units = units;
    return this;
  }

  public Integer getMagnitude() {
    return magnitude;
  }

  public OverseasTradeIndex setMagnitude(Integer magnitude) {
    this.magnitude = magnitude;
    return this;
  }

  public String getSubject() {
    return subject;
  }

  public OverseasTradeIndex setSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public String getGroup() {
    return group;
  }

  public OverseasTradeIndex setGroup(String group) {
    this.group = group;
    return this;
  }

  public Series getSeries() {
    return series;
  }

  @SuppressWarnings("UnusedReturnValue")
  public OverseasTradeIndex setSeries(Series series) {
    this.series = series;
    return this;
  }
}

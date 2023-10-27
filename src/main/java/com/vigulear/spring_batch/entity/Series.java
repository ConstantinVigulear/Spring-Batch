package com.vigulear.spring_batch.entity;

import jakarta.persistence.*;

/**
 * @author Constantin Vigulear
 */
@SuppressWarnings("unused")
@Entity
@Table(name = "SERIES")
public class Series {

  @Id
  @Column(name = "OVERSEAS_TRADE_INDEX_ID")
  private Long id;

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

  @OneToOne
  @MapsId
  @JoinColumn(name = "OVERSEAS_TRADE_INDEX_ID")
  private OverseasTradeIndex overseasTradeIndex;

  public Series() {
  }

  public Long getId() {
    return id;
  }

  public Series setId(Long id) {
    this.id = id  ;
    return this;
  }

  public String getSeriesTitle1() {
    return seriesTitle1;
  }

  public Series setSeriesTitle1(String seriesTitle1) {
    this.seriesTitle1 = seriesTitle1;
    return this;
  }

  public String getSeriesTitle2() {
    return seriesTitle2;
  }

  public Series setSeriesTitle2(String seriesTitle2) {
    this.seriesTitle2 = seriesTitle2;
    return this;
  }

  public String getSeriesTitle3() {
    return seriesTitle3;
  }

  public Series setSeriesTitle3(String seriesTitle3) {
    this.seriesTitle3 = seriesTitle3;
    return this;
  }

  public String getSeriesTitle4() {
    return seriesTitle4;
  }

  public Series setSeriesTitle4(String seriesTitle4) {
    this.seriesTitle4 = seriesTitle4;
    return this;
  }

  public String getSeriesTitle5() {
    return seriesTitle5;
  }

  public Series setSeriesTitle5(String seriesTitle5) {
    this.seriesTitle5 = seriesTitle5;
    return this;
  }

  public OverseasTradeIndex getOverseasTradeIndexes() {
    return overseasTradeIndex;
  }

  @SuppressWarnings("UnusedReturnValue")
  public Series setOverseasTradeIndexes(OverseasTradeIndex overseasTradeIndex) {
    this.overseasTradeIndex = overseasTradeIndex;
    return this;
  }
}

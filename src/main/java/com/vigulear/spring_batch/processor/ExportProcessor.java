package com.vigulear.spring_batch.processor;

import com.vigulear.spring_batch.dto.OverseasTradeIndexDTO;
import com.vigulear.spring_batch.entity.OverseasTradeIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Constantin Vigulear
 */
@Component
public class ExportProcessor implements ItemProcessor<OverseasTradeIndex, OverseasTradeIndexDTO> {
  private static final Logger LOG = LoggerFactory.getLogger(OverseasTradeIndex.class);
  private int recordNumber = 1;

  @Override
  public OverseasTradeIndexDTO process(@NonNull OverseasTradeIndex item) {

    OverseasTradeIndexDTO dto =
        new OverseasTradeIndexDTO()
            .setSeriesReference(item.getSeriesReference())
            .setPeriod(item.getPeriod())
            .setDataValue(item.getDataValue())
            .setStatus(item.getStatus())
            .setUnits(item.getUnits())
            .setMagnitude(item.getMagnitude())
            .setSubject(item.getSubject())
            .setGroup(item.getGroup())
            .setSeriesTitle1(item.getSeries().getSeriesTitle1())
            .setSeriesTitle2(item.getSeries().getSeriesTitle2())
            .setSeriesTitle3(item.getSeries().getSeriesTitle3())
            .setSeriesTitle4(item.getSeries().getSeriesTitle4())
            .setSeriesTitle5(item.getSeries().getSeriesTitle5());

    LOG.info("Processing record number {} ...", recordNumber);

    recordNumber++;
    return dto;
  }
}

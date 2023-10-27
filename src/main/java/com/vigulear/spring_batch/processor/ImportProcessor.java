package com.vigulear.spring_batch.processor;

import com.vigulear.spring_batch.dto.OverseasTradeIndexDTO;
import com.vigulear.spring_batch.entity.OverseasTradeIndex;
import com.vigulear.spring_batch.entity.Series;
import com.vigulear.spring_batch.validator.CSVValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author Constantin Vigulear
 */
@Component
public class ImportProcessor extends CSVValidator
    implements ItemProcessor<OverseasTradeIndexDTO, OverseasTradeIndex> {
  private static final Logger LOG = LoggerFactory.getLogger(OverseasTradeIndex.class);
  private int recordNumber = 1;

  @Override
  public OverseasTradeIndex process(@NonNull OverseasTradeIndexDTO item) {
    LOG.info("Processing record number {} ...", recordNumber);

    OverseasTradeIndex overseasTradeIndex = new OverseasTradeIndex();
    Series series = new Series();

    try {
      this.validate(item);

      overseasTradeIndex
          .setSeriesReference(item.getSeriesReference())
          .setPeriod(item.getPeriod())
          .setDataValue(item.getDataValue())
          .setStatus(item.getStatus())
          .setUnits(item.getUnits())
          .setMagnitude(item.getMagnitude())
          .setSubject(item.getSubject())
          .setGroup(item.getGroup())
          .setSeries(series);
      series
          .setSeriesTitle1(item.getSeriesTitle1())
          .setSeriesTitle2(item.getSeriesTitle2())
          .setSeriesTitle3(item.getSeriesTitle3())
          .setSeriesTitle4(item.getSeriesTitle4())
          .setSeriesTitle5(item.getSeriesTitle5())
          .setOverseasTradeIndexes(overseasTradeIndex);

    } catch (ValidationException exception) {
      LOG.warn("Skipping record number {} - {}!", recordNumber, exception.getMessage());
    }

    recordNumber++;

    return overseasTradeIndex;
  }
}

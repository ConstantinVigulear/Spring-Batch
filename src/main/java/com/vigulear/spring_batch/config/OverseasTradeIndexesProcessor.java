package com.vigulear.spring_batch.config;

import com.vigulear.spring_batch.entity.OverseasTradeIndexes;
import com.vigulear.spring_batch.validator.OverseasTradeIndexesValidator;
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
public class OverseasTradeIndexesProcessor extends OverseasTradeIndexesValidator
    implements ItemProcessor<OverseasTradeIndexes, OverseasTradeIndexes> {
  private static final Logger LOG = LoggerFactory.getLogger(OverseasTradeIndexes.class);
  private int recordNumber = 1;

  @Override
  public OverseasTradeIndexes process(@NonNull OverseasTradeIndexes item) {
    LOG.info("Processing record number {} ...", recordNumber);

    try {
      this.validate(item);
    } catch (ValidationException exception) {
      LOG.warn("Skipping record number {} - {}!", recordNumber, exception.getMessage());
    }

    recordNumber++;

    return item;
  }
}

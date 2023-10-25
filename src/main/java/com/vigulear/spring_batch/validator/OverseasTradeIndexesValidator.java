package com.vigulear.spring_batch.validator;

import com.vigulear.spring_batch.entity.OverseasTradeIndexes;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.lang.NonNull;

public class OverseasTradeIndexesValidator implements MyValidator<OverseasTradeIndexes> {

  @Override
  public void validate(@NonNull OverseasTradeIndexes value) throws ValidationException {
    if (value.getPeriod() != null && value.getPeriod() < 0)
      throw new ValidationException("OverseasTradeIndexes \"period\" cannot be below zero!");
  }
}

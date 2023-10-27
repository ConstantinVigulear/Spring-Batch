package com.vigulear.spring_batch.validator;

import com.vigulear.spring_batch.dto.OverseasTradeIndexDTO;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.lang.NonNull;

public class CSVValidator implements MyValidator<OverseasTradeIndexDTO> {

  @Override
  public void validate(@NonNull OverseasTradeIndexDTO value) throws ValidationException {
    if (value.getPeriod() != null && value.getPeriod() < 0)
      throw new ValidationException("OverseasTradeIndex \"period\" cannot be below zero!");
  }
}

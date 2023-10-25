package com.vigulear.spring_batch.validator;

import org.springframework.batch.item.validator.Validator;

public interface MyValidator<T> extends Validator<T> {}

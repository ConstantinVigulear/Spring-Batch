package com.vigulear.spring_batch.repository;

import com.vigulear.spring_batch.entity.OverseasTradeIndex;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Constantin Vigulear
 */
public interface OverseasTradeIndexRepository
    extends CrudRepository<OverseasTradeIndex, Long>,
        PagingAndSortingRepository<OverseasTradeIndex, Long> {}

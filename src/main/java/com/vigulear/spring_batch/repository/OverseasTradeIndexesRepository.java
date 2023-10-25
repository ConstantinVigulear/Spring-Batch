package com.vigulear.spring_batch.repository;

import com.vigulear.spring_batch.entity.OverseasTradeIndexes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Constantin Vigulear
 */
public interface OverseasTradeIndexesRepository extends CrudRepository<OverseasTradeIndexes, Long> {}

package com.vigulear.spring_batch.repository;

import com.vigulear.spring_batch.entity.Series;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Constantin Vigulear
 */
public interface SeriesRepository
    extends CrudRepository<Series, Long>,
        PagingAndSortingRepository<Series, Long> {}

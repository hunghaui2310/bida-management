package com.bida.management.repository;

import com.bida.management.domain.HistoryProduct;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoryProductRepository extends PagingAndSortingRepository<HistoryProduct, Long> {}

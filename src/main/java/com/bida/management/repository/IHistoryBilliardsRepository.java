package com.bida.management.repository;

import com.bida.management.domain.HistoryBilliards;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoryBilliardsRepository extends PagingAndSortingRepository<HistoryBilliards, Long> {}

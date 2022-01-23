package com.bida.management.repository;

import com.bida.management.domain.HistoryBilliards;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoryBilliardsRepository extends PagingAndSortingRepository<HistoryBilliards, Long> {
    Page<HistoryBilliards> findAllByBilliards_Id(Pageable pageable, Long billiardsId);
}

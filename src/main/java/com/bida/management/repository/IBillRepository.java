package com.bida.management.repository;

import com.bida.management.domain.Bill;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillRepository extends PagingAndSortingRepository<Bill, Long> {}

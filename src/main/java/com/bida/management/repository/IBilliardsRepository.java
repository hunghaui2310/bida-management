package com.bida.management.repository;

import com.bida.management.domain.Billiards;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBilliardsRepository extends PagingAndSortingRepository<Billiards, Long> {}

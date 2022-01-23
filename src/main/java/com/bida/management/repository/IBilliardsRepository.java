package com.bida.management.repository;

import com.bida.management.domain.Billiards;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBilliardsRepository extends PagingAndSortingRepository<Billiards, Long> {
    Page<Billiards> findAllByNameContainingIgnoreCaseAndStatus(Pageable pageable, String name, Integer status);

    Page<Billiards> findAllByNameContainingIgnoreCase(Pageable pageable, String name);

    List<Billiards> findAllByStatus(Integer status);
}

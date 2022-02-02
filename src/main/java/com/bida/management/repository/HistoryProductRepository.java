package com.bida.management.repository;

import com.bida.management.domain.HistoryProduct;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryProductRepository extends PagingAndSortingRepository<HistoryProduct, Long>, HistoryProductRepositoryCustom {
    List<HistoryProduct> findAllByStatusNot(Integer status);
}

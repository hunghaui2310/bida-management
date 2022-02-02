package com.bida.management.repository;

import com.bida.management.domain.HistoryProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryProductRepositoryCustom {
    Page<HistoryProduct> search(Pageable pageable, HistoryProduct historyProduct);
}

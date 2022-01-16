package com.bida.management.service;

import com.bida.management.domain.HistoryProduct;
import com.bida.management.repository.IHistoryProductRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistoryProductService implements IBaseService<HistoryProduct, Long> {

    private final IHistoryProductRepository historyProductRepository;

    public HistoryProductService(IHistoryProductRepository historyProductRepository) {
        this.historyProductRepository = historyProductRepository;
    }

    @Override
    public Page<HistoryProduct> findAll(Pageable pageable) {
        return historyProductRepository.findAll(pageable);
    }

    @Override
    public Optional<HistoryProduct> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public HistoryProduct save(HistoryProduct historyProduct) {
        return historyProductRepository.save(historyProduct);
    }

    @Override
    @Transactional
    public void remove(Long id) {}
}

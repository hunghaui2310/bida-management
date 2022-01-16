package com.bida.management.service;

import com.bida.management.domain.HistoryBilliards;
import com.bida.management.repository.IHistoryBilliardsRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistoryBilliardsService implements IBaseService<HistoryBilliards, Long> {

    private final IHistoryBilliardsRepository historyBilliardsRepository;

    public HistoryBilliardsService(IHistoryBilliardsRepository historyBilliardsRepository) {
        this.historyBilliardsRepository = historyBilliardsRepository;
    }

    @Override
    public Page<HistoryBilliards> findAll(Pageable pageable) {
        return historyBilliardsRepository.findAll(pageable);
    }

    @Override
    public Optional<HistoryBilliards> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public HistoryBilliards save(HistoryBilliards historyBilliards) {
        return null;
    }

    @Override
    @Transactional
    public void remove(Long id) {}
}

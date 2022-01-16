package com.bida.management.service;

import com.bida.management.domain.Billiards;
import com.bida.management.repository.IBilliardsRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BilliardsService implements IBaseService<Billiards, Long> {

    private final IBilliardsRepository billiardsRepository;

    public BilliardsService(IBilliardsRepository billiardsRepository) {
        this.billiardsRepository = billiardsRepository;
    }

    @Override
    public Page<Billiards> findAll(Pageable pageable) {
        return billiardsRepository.findAll(pageable);
    }

    @Override
    public Optional<Billiards> findById(Long id) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Billiards save(Billiards billiards) {
        return billiardsRepository.save(billiards);
    }

    @Override
    public void remove(Long id) {}
}

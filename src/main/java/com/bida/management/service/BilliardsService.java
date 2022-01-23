package com.bida.management.service;

import com.bida.management.config.Constants;
import com.bida.management.domain.Billiards;
import com.bida.management.repository.IBilliardsRepository;
import com.bida.management.util.AppConstant;
import java.util.List;
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

    public List<Billiards> findAllBilliardsActive() {
        return billiardsRepository.findAllByStatus(AppConstant.STATUS.ACTIVE);
    }

    @Override
    public Optional<Billiards> findById(Long id) {
        return billiardsRepository.findById(id);
    }

    @Transactional
    @Override
    public Billiards save(Billiards billiards) {
        return billiardsRepository.save(billiards);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        billiardsRepository.deleteById(id);
    }

    public Page<Billiards> search(Pageable pageable, Billiards billiards) {
        return billiards.getStatus() == Constants.STATUS.ALL
            ? billiardsRepository.findAllByNameContainingIgnoreCase(pageable, billiards.getName())
            : billiardsRepository.findAllByNameContainingIgnoreCaseAndStatus(pageable, billiards.getName(), billiards.getStatus());
    }
}

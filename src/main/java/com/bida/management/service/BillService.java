package com.bida.management.service;

import com.bida.management.domain.Bill;
import com.bida.management.repository.IBillRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillService implements IBaseService<Bill, Long> {

    private final IBillRepository billRepository;

    public BillService(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Page<Bill> findAll(Pageable pageable) {
        return billRepository.findAll(pageable);
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    @Transactional
    public void remove(Long id) {}
}

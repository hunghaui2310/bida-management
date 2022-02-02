package com.bida.management.service;

import com.bida.management.config.Constants;
import com.bida.management.domain.HistoryProduct;
import com.bida.management.domain.User;
import com.bida.management.repository.HistoryProductRepository;
import com.bida.management.repository.UserRepository;
import com.bida.management.security.SecurityUtils;
import io.undertow.util.BadRequestException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HistoryProductService implements IBaseService<HistoryProduct, Long> {

    private final HistoryProductRepository historyProductRepository;
    private final UserRepository userRepository;

    @Autowired
    public HistoryProductService(HistoryProductRepository historyProductRepository, UserRepository userRepository) {
        this.historyProductRepository = historyProductRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<HistoryProduct> findAll(Pageable pageable) {
        return historyProductRepository.findAll(pageable);
    }

    public List<HistoryProduct> findAll() {
        return historyProductRepository.findAllByStatusNot(Constants.STATUS.DELETED);
    }

    public Page<HistoryProduct> search(Pageable pageable, HistoryProduct historyProduct) {
        return historyProductRepository.search(pageable, historyProduct);
    }

    @Override
    public Optional<HistoryProduct> findById(Long id) {
        return historyProductRepository.findById(id);
    }

    @Override
    @Transactional
    public HistoryProduct save(HistoryProduct historyProduct) throws BadRequestException {
        Optional<User> user = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
        if (user.isEmpty()) {
            throw new BadRequestException("Employee not exist");
        }
        historyProduct.setUser(user.get());
        return historyProductRepository.save(historyProduct);
    }

    @Override
    @Transactional
    public void remove(Long id) {}

    @Transactional
    public void updateStatus(Long id, Integer status) throws BadRequestException {
        if (historyProductRepository.findById(id).isEmpty()) {
            throw new BadRequestException("Id is not exist");
        }
        HistoryProduct historyProduct = historyProductRepository.findById(id).get();
        historyProduct.setStatus(status);
        historyProductRepository.save(historyProduct);
    }
}

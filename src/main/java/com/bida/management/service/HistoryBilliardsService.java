package com.bida.management.service;

import com.bida.management.domain.Billiards;
import com.bida.management.domain.HistoryBilliards;
import com.bida.management.domain.User;
import com.bida.management.repository.IBilliardsRepository;
import com.bida.management.repository.IHistoryBilliardsRepository;
import com.bida.management.repository.UserRepository;
import com.bida.management.security.SecurityUtils;
import io.undertow.util.BadRequestException;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class HistoryBilliardsService implements IBaseService<HistoryBilliards, Long> {

    private final IHistoryBilliardsRepository historyBilliardsRepository;
    private final UserRepository userRepository;
    private final IBilliardsRepository billiardsRepository;

    public HistoryBilliardsService(
        IHistoryBilliardsRepository historyBilliardsRepository,
        UserRepository userRepository,
        IBilliardsRepository billiardsRepository
    ) {
        this.historyBilliardsRepository = historyBilliardsRepository;
        this.userRepository = userRepository;
        this.billiardsRepository = billiardsRepository;
    }

    @Override
    public Page<HistoryBilliards> findAll(Pageable pageable) {
        return historyBilliardsRepository.findAll(pageable);
    }

    public Page<HistoryBilliards> findAll(Pageable pageable, Long billiardsId) {
        return historyBilliardsRepository.findAllByBilliards_Id(pageable, billiardsId);
    }

    @Override
    public Optional<HistoryBilliards> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public HistoryBilliards save(HistoryBilliards historyBilliards) throws BadRequestException {
        Optional<User> user = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
        if (user.isEmpty()) {
            throw new BadRequestException("Employee not exist");
        }
        historyBilliards.setUser(user.get());
        historyBilliards.setBilliardsId(historyBilliards.getBilliards().getId());
        return historyBilliardsRepository.save(historyBilliards);
    }

    @Transactional
    public HistoryBilliards changeStatus(HistoryBilliards historyBilliards) throws BadRequestException {
        Optional<User> user = SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneWithAuthoritiesByLogin);
        if (user.isEmpty()) {
            throw new BadRequestException("Employee not exist");
        }
        historyBilliards.setUser(user.get());
        historyBilliards.setBilliardsId(historyBilliards.getBilliards().getId());
        boolean state = !historyBilliards.getBilliards().isState();
        historyBilliards.getBilliards().setState(state);
        Billiards billiards = billiardsRepository.save(historyBilliards.getBilliards());
        historyBilliards.setBilliards(billiards);
        return historyBilliardsRepository.save(historyBilliards);
    }

    @Override
    @Transactional
    public void remove(Long id) {}
}

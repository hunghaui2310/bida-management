package com.bida.management.service;

import com.bida.management.domain.Provider;
import com.bida.management.repository.IProviderRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProviderService implements IBaseService<Provider, Long> {

    private final IProviderRepository providerRepository;

    public ProviderService(IProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public Page<Provider> findAll(Pageable pageable) {
        return providerRepository.findAll(pageable);
    }

    @Override
    public Optional<Provider> findById(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    @Transactional
    public void remove(Long id) {}
}

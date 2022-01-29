package com.bida.management.service;

import com.bida.management.config.Constants;
import com.bida.management.domain.Provider;
import com.bida.management.repository.IProviderRepository;
import com.bida.management.web.rest.errors.DuplicateExceptionHandle;
import io.undertow.util.BadRequestException;
import java.util.Objects;
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

    public Page<Provider> search(Pageable pageable, String name, String phoneNumber, String address) {
        return providerRepository.findAllByNameContainingIgnoreCaseAndPhoneNumberContainingIgnoreCaseAndAddressContainingIgnoreCaseAndStatusNot(
            pageable,
            name,
            phoneNumber,
            address,
            Constants.STATUS.DELETED
        );
    }

    @Override
    public Optional<Provider> findById(Long id) {
        return providerRepository.findById(id);
    }

    @Override
    @Transactional
    public Provider save(Provider provider) throws DuplicateExceptionHandle {
        if (
            (
                Objects.isNull(provider.getId()) &&
                providerRepository.existsByPhoneNumberAndStatusNot(provider.getPhoneNumber(), Constants.STATUS.DELETED)
            ) ||
            (Objects.nonNull(provider.getId())) &&
            providerRepository.existsByPhoneNumberAndStatusNotAndIdNot(
                provider.getPhoneNumber(),
                Constants.STATUS.DELETED,
                provider.getId()
            )
        ) {
            throw new DuplicateExceptionHandle("phoneNumber");
        }
        return providerRepository.save(provider);
    }

    @Override
    @Transactional
    public void remove(Long id) {}

    @Transactional
    public void updateStatus(Long id) throws BadRequestException {
        if (providerRepository.findById(id).isEmpty()) {
            throw new BadRequestException("Id is not exist");
        }
        Provider provider = providerRepository.findById(id).get();
        provider.setStatus(Constants.STATUS.DELETED);
        providerRepository.save(provider);
    }
}

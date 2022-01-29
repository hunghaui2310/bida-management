package com.bida.management.repository;

import com.bida.management.domain.Provider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProviderRepository extends PagingAndSortingRepository<Provider, Long> {
    boolean existsByPhoneNumberAndStatusNot(String phoneNumber, Integer status);
    boolean existsByPhoneNumberAndStatusNotAndIdNot(String phoneNumber, Integer status, Long id);

    Page<Provider> findAllByNameContainingIgnoreCaseAndPhoneNumberContainingIgnoreCaseAndAddressContainingIgnoreCaseAndStatusNot(
        Pageable pageable,
        String name,
        String phoneNumber,
        String address,
        Integer status
    );
}

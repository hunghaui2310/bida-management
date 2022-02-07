package com.bida.management.repository;

import com.bida.management.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Page<Customer> findAllByNameContainingIgnoreCaseAndPhoneNumberContainingIgnoreCaseAndStatusNot(
        Pageable pageable,
        String phoneNumber,
        String name,
        Integer status
    );
}

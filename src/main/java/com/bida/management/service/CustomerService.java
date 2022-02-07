package com.bida.management.service;

import com.bida.management.config.Constants;
import com.bida.management.domain.Customer;
import com.bida.management.domain.HistoryProduct;
import com.bida.management.repository.ICustomerRepository;
import io.undertow.util.BadRequestException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService implements IBaseService<Customer, Long> {

    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Page<Customer> findAllByPhoneNumberOrName(Pageable pageable, String phoneNumber, String name) {
        return customerRepository.findAllByNameContainingIgnoreCaseAndPhoneNumberContainingIgnoreCaseAndStatusNot(
            pageable,
            phoneNumber,
            name,
            Constants.STATUS.DELETED
        );
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void remove(Long id) {}

    @Transactional
    public void updateStatus(Long id, Integer status) throws BadRequestException {
        if (customerRepository.findById(id).isEmpty()) {
            throw new BadRequestException("Id is not exist");
        }
        Customer customer = customerRepository.findById(id).get();
        customer.setStatus(status);
        customerRepository.save(customer);
    }
}

package com.bida.management.web.rest;

import com.bida.management.config.Constants;
import com.bida.management.domain.Customer;
import com.bida.management.security.AuthoritiesConstants;
import com.bida.management.service.CustomerService;
import io.undertow.util.BadRequestException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * {@code POST  /customer/save}  : Creates a new customer.
     *
     * @param customer the customer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customer,
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        log.debug("REST request to save Customer : {}", customer);
        Customer newCustomer = customerService.save(customer);
        return ResponseEntity.status(HttpStatus.OK).body(newCustomer);
    }

    /**
     * {@code GET /customer} : get all billiards with all the pageable
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all customer.
     */
    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Page<Customer>> getAllCustomers(
        Pageable pageable,
        @RequestParam(defaultValue = "", required = false) String phoneNumber,
        @RequestParam(defaultValue = "", required = false) String name
    ) {
        log.debug("REST request to get Customer with pageable");

        final Page<Customer> page = customerService.findAllByPhoneNumberOrName(pageable, phoneNumber, name);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * {@code GET /customer/id} : get customer detail by id
     *
     * @param id id of customer
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body detail customer
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        log.debug("REST request to get Customer detail, id = {}", id);

        return ResponseUtil.wrapOrNotFound(customerService.findById(id));
    }

    /**
     * {@code DELETE /customer/{id}} : delete customer by id
     *
     * @param id id to delete
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws BadRequestException {
        log.debug("REST request to delete Customer");

        customerService.updateStatus(id, Constants.STATUS.DELETED);
        return ResponseEntity.noContent().build();
    }
}

package com.bida.management.web.rest;

import com.bida.management.domain.Billiards;
import com.bida.management.domain.Provider;
import com.bida.management.security.AuthoritiesConstants;
import com.bida.management.service.ProviderService;
import com.bida.management.web.rest.errors.DuplicateExceptionHandle;
import com.bida.management.web.rest.vm.ApiResponse;
import io.undertow.util.BadRequestException;
import java.util.List;
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
@RequestMapping("/api/provider")
public class ProviderResource {

    private final Logger log = LoggerFactory.getLogger(ProviderResource.class);

    private final ProviderService providerService;

    public ProviderResource(ProviderService providerService) {
        this.providerService = providerService;
    }

    /**
     * {@code POST  /provider/save}  : Creates a new provider.
     *
     * @param provider the provider to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new provider,
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<?> createProvider(@Valid @RequestBody Provider provider) {
        log.debug("REST request to save Provider : {}", provider);
        try {
            Provider newProvider = providerService.save(provider);
            return ResponseEntity.status(HttpStatus.OK).body(newProvider);
        } catch (DuplicateExceptionHandle e) {
            return new ResponseEntity<>(new ApiResponse(false, "Phone number is existed", e.getField()), HttpStatus.CONFLICT);
        }
    }

    /**
     * {@code GET /provider} : get provider with paging
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body provider by size.
     */
    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Page<Provider>> getAllProviders(
        Pageable pageable,
        @RequestParam(defaultValue = "", required = false) String name,
        @RequestParam(defaultValue = "", required = false) String phoneNumber,
        @RequestParam(defaultValue = "", required = false) String address
    ) {
        log.debug("REST request to get all Provider");

        final Page<Provider> page = providerService.search(pageable, name, phoneNumber, address);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * {@code GET /provider/id} : get provider details by id
     *
     * @param id id of provider
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body detail provider.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Provider> getById(@PathVariable Long id) {
        log.debug("REST request to get Billiards detail, id = {}", id);

        return ResponseUtil.wrapOrNotFound(providerService.findById(id));
    }

    /**
     * {@code DELETE /provider/{id}} : delete provider by id
     *
     * @param id id to delete
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws BadRequestException {
        log.debug("REST request to delete Billiards");

        providerService.updateStatus(id);
        return ResponseEntity.noContent().build();
    }
}

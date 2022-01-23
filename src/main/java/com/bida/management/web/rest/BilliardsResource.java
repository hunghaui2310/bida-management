package com.bida.management.web.rest;

import com.bida.management.domain.Billiards;
import com.bida.management.security.AuthoritiesConstants;
import com.bida.management.service.BilliardsService;
import com.bida.management.service.dto.AdminUserDTO;
import java.util.List;
import java.util.Optional;
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
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api/billiards")
public class BilliardsResource {

    private final Logger log = LoggerFactory.getLogger(BilliardsResource.class);

    private final BilliardsService billiardsService;

    public BilliardsResource(BilliardsService billiardsService) {
        this.billiardsService = billiardsService;
    }

    /**
     * {@code POST  /billiards/save}  : Creates a new billiards.
     *
     * @param billiards the billiards to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billiards,
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Billiards> createBilliards(@Valid @RequestBody Billiards billiards) {
        log.debug("REST request to save Billiards : {}", billiards);
        Billiards newBilliards = billiardsService.save(billiards);
        return ResponseEntity.status(HttpStatus.OK).body(newBilliards);
    }

    /**
     * {@code GET /billiards} : get billiards with paging
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body is billiards.
     */
    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Page<Billiards>> pagingBilliards(
        Pageable pageable,
        @RequestParam(defaultValue = "", required = false) String name,
        @RequestParam(defaultValue = "-1", required = false) Integer status
    ) {
        log.debug("REST request to paging with Billiards");

        final Page<Billiards> page = billiardsService.search(pageable, new Billiards(name, status));
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * {@code GET /billiards/getAll} : get all billiards
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all billiards.
     */
    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Iterable<Billiards>> getAllBilliards() {
        log.debug("REST request to get all Billiards");

        final Iterable<Billiards> page = billiardsService.findAllBilliardsActive();
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    /**
     * {@code GET /billiards/id} : get billiards details by id
     *
     * @param id id of billiards
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body detail billiards.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Billiards> getById(@PathVariable Long id) {
        log.debug("REST request to get Billiards detail, id = {}", id);

        return ResponseUtil.wrapOrNotFound(billiardsService.findById(id));
    }

    /**
     * {@code DELETE /billiards/{id}} : delete billiards by id
     *
     * @param id id to delete
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteAllBilliards(@PathVariable Long id) {
        log.debug("REST request to delete Billiards");

        billiardsService.remove(id);
        return ResponseEntity.noContent().build();
    }
}

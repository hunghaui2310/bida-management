package com.bida.management.web.rest;

import com.bida.management.domain.Billiards;
import com.bida.management.security.AuthoritiesConstants;
import com.bida.management.service.BilliardsService;
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
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Billiards> createBilliards(@Valid @RequestBody Billiards billiards) {
        log.debug("REST request to save Billiards : {}", billiards);
        Billiards newBilliards = billiardsService.save(billiards);
        return ResponseEntity.status(HttpStatus.OK).body(newBilliards);
    }

    /**
     * {@code GET /billiards} : get all billiards with all the details
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all billiards.
     */
    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<Billiards>> getAllBilliards(Pageable pageable) {
        log.debug("REST request to get all Billiards");

        final Page<Billiards> page = billiardsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}

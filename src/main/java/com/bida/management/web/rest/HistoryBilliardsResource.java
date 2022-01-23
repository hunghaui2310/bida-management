package com.bida.management.web.rest;

import com.bida.management.domain.HistoryBilliards;
import com.bida.management.security.AuthoritiesConstants;
import com.bida.management.service.HistoryBilliardsService;
import io.undertow.util.BadRequestException;
import java.util.List;
import java.util.Objects;
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
@RequestMapping("/api/history-billiards")
public class HistoryBilliardsResource {

    private final Logger log = LoggerFactory.getLogger(HistoryBilliardsResource.class);

    private final HistoryBilliardsService historyBilliardsService;

    public HistoryBilliardsResource(HistoryBilliardsService historyBilliardsService) {
        this.historyBilliardsService = historyBilliardsService;
    }

    /**
     * {@code POST  /history-billiards/save}  : Creates a new history billiards.
     *
     * @param historyBilliards the history billiards to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new history billiards,
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<HistoryBilliards> createHistoryBilliards(@Valid @RequestBody HistoryBilliards historyBilliards)
        throws BadRequestException {
        log.debug("REST request to save History Billiards : {}", historyBilliards);
        HistoryBilliards newHistoryBilliards = historyBilliardsService.save(historyBilliards);
        return ResponseEntity.status(HttpStatus.OK).body(newHistoryBilliards);
    }

    /**
     * {@code POST  /history-billiards/change}  : Creates a new history billiards.
     *
     * @param historyBilliards the history billiards to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new history billiards,
     */
    @PostMapping("/change")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<HistoryBilliards> changeStatusHistoryBilliards(@Valid @RequestBody HistoryBilliards historyBilliards)
        throws BadRequestException {
        log.debug("REST request to change status History Billiards : {}", historyBilliards);
        HistoryBilliards newHistoryBilliards = historyBilliardsService.changeStatus(historyBilliards);
        return ResponseEntity.status(HttpStatus.OK).body(newHistoryBilliards);
    }

    /**
     * {@code GET /history-billiards} : get all history billiards with all the details
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all history billiards.
     */
    @GetMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<Page<HistoryBilliards>> getAllHistoryBilliards(
        Pageable pageable,
        @RequestParam(required = false) Long billiardsId
    ) {
        log.debug("REST request to get all History Billiards");

        final Page<HistoryBilliards> page = Objects.isNull(billiardsId)
            ? historyBilliardsService.findAll(pageable)
            : historyBilliardsService.findAll(pageable, billiardsId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }
}

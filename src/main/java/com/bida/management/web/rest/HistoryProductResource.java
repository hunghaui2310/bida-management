package com.bida.management.web.rest;

import com.bida.management.config.Constants;
import com.bida.management.domain.Billiards;
import com.bida.management.domain.HistoryProduct;
import com.bida.management.domain.Provider;
import com.bida.management.security.AuthoritiesConstants;
import com.bida.management.service.HistoryProductService;
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
@RequestMapping("/api/history-product")
public class HistoryProductResource {

    private final Logger log = LoggerFactory.getLogger(HistoryProductResource.class);

    private final HistoryProductService historyProductService;

    public HistoryProductResource(HistoryProductService historyProductService) {
        this.historyProductService = historyProductService;
    }

    /**
     * {@code POST  /history-product/save}  : Creates a new history product.
     *
     * @param historyProduct the history product to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new history product,
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<HistoryProduct> createHistoryProduct(@Valid @RequestBody HistoryProduct historyProduct)
        throws BadRequestException {
        log.debug("REST request to save History Product : {}", historyProduct);
        HistoryProduct newHistoryProduct = historyProductService.save(historyProduct);
        return ResponseEntity.status(HttpStatus.OK).body(newHistoryProduct);
    }

    /**
     * {@code GET /history-product} : get all history product with all the details
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all history product.
     */
    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    public ResponseEntity<List<HistoryProduct>> getAllHistoryProducts(Pageable pageable) {
        log.debug("REST request to get all History Product");

        final Page<HistoryProduct> page = historyProductService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * {@code POST /history-product} : get history product with paging
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body is history product.
     */
    @PostMapping
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Page<HistoryProduct>> pagingHistoryProduct(Pageable pageable, @RequestBody HistoryProduct historyProduct) {
        log.debug("REST request to paging with History Product");

        final Page<HistoryProduct> page = historyProductService.search(pageable, historyProduct);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page, headers, HttpStatus.OK);
    }

    /**
     * {@code GET /history-product/id} : get history product details by id
     *
     * @param id id of history product
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body detail history product.
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<HistoryProduct> getById(@PathVariable Long id) {
        log.debug("REST request to get History Product detail, id = {}", id);

        return ResponseUtil.wrapOrNotFound(historyProductService.findById(id));
    }

    /**
     * {@code DELETE /history-product/{id}} : delete hsitory product by id
     *
     * @param id id to delete
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) throws BadRequestException {
        log.debug("REST request to delete History Product");

        historyProductService.updateStatus(id, Constants.STATUS.DELETED);
        return ResponseEntity.noContent().build();
    }
}

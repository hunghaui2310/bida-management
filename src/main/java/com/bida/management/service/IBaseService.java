package com.bida.management.service;

import com.bida.management.web.rest.errors.DuplicateExceptionHandle;
import io.undertow.util.BadRequestException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBaseService<T, ID> {
    Page<T> findAll(Pageable pageable);

    Optional<T> findById(ID id);

    T save(T t) throws BadRequestException, DuplicateExceptionHandle;

    void remove(ID id);
}

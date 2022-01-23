package com.bida.management.service;

import io.undertow.util.BadRequestException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBaseService<T, ID> {
    Page<T> findAll(Pageable pageable);

    Optional<T> findById(ID id);

    T save(T t) throws BadRequestException;

    void remove(ID id);
}

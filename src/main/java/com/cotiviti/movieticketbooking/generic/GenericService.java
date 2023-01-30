package com.cotiviti.movieticketbooking.generic;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericService<T extends BaseEntity, ID extends Serializable> {
    T create(T newInstance);

    List<T> getActive();

    void softDelete(ID id);

    Page<T> getActivePagination(int pageNumber, int pageSize);
}

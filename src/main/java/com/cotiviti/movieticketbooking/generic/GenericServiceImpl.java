package com.cotiviti.movieticketbooking.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class GenericServiceImpl<T extends BaseEntity, ID extends Serializable> implements GenericService<T, ID> {

    private GenericRepository<T, ID> repository;

    public GenericServiceImpl(GenericRepository<T, ID> repository) {
        this.repository = repository;
    }


    @Override
    public T create(T newInstance) {
        return repository.save(newInstance);
    }

    @Override
    public List<T> getActive() {
        return repository.findByStatusIsTrue();
    }

    @Transactional
    @Override
    public void softDelete(ID id) {
        repository.softDelete(id);
    }

    @Override
    public Page<T> getActivePagination(int pageNumber, int pageSize) {
        return repository.findAll(PageRequest.of(pageNumber, pageSize));
    }
}

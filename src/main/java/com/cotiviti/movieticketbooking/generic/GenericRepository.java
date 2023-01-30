package com.cotiviti.movieticketbooking.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity, ID extends Serializable> extends JpaSpecificationExecutor<T>, JpaRepository<T, ID> {

    @Query("select e from #{#entityName} e where e.status = true")
    List<T> findByStatusIsTrue();

    @Modifying
    @Query("update #{#entityName} e set e.status = false where e.id = ?1")
    void softDelete(ID id);
}

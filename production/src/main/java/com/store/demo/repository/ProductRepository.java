package com.store.demo.repository;

import com.store.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends PagingAndSortingRepository<Product,Integer> {

    @Query(value = "SELECT * from products p Where p.name like %?1%",nativeQuery = true)
    public Page<Product> findAll(String keyword, Pageable pageable);

}

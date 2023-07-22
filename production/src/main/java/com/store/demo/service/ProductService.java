package com.store.demo.service;

import com.store.demo.model.Product;
import com.store.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> listAll(int pageNum , String sortField, String sortDir,String keyWord) {
        int pageSize = 4;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
       if(keyWord!=null){
           return productRepository.findAll(keyWord,pageable);
       }

        return productRepository.findAll(pageable);
    }

    public void save(Product product) {
       productRepository.save(product);
    }

    public Optional<Product> get(int id) {
        return productRepository.findById(id);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }
}

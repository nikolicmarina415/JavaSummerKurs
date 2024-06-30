package com.logate.summer.Services;

import com.logate.summer.Repositories.ProductRepository;
import com.logate.summer.entities.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Cacheable(value = "product", key = "#id")
    public Product findProductById(Integer id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Cacheable(value = "products", key = "'listProduct'")
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @CachePut(cacheNames = "product", key = "#result.id")
    public Product create(Product product) {
        clearCacheList();
        Product product1 = productRepository.save(product);
        findAll();
        return product1;
    }

    @CachePut(cacheNames = "product", key = "#result.id")
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @CacheEvict(cacheNames = "product", key = "#id")
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }


    @Cacheable(value = "productPage", key="'page'+#pageable.page+'size'+#pageable.size")
    public Page<Product> findAllPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @CacheEvict(cacheNames = "products", allEntries = true)
    public void clearCacheList() {

    }

}
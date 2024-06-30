package com.logate.summer.Repositories;

import com.logate.summer.SummerApplication;
import com.logate.summer.entities.Category;
import com.logate.summer.entities.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = SummerApplication.class)
@Slf4j
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void shouldInsert() {
        // 1. SELECT + UPDATE ako postoji ID
        // 2. INSERT ako ne postoji ID
        Category category = new Category();
        category.setName("Kategorija 1");
        category.setDescription("Kategorija 1 desc");
        Category category1 = categoryRepository.save(category);
        log.info("Nakon insert-a {}:", category1.getId());

        Category category2 = categoryRepository.findById(5).orElseThrow(EntityNotFoundException::new);
        log.info("Nakon izvlacenja iz baze: {}", category2.getId());
    }

    @Test
    void shouldUpdate() {
        Category category = categoryRepository.findById(4).orElseThrow(EntityNotFoundException::new);
        category.setName("Kategorija 4");
        Category category1 = categoryRepository.save(category);
        log.info("Iz pers. context: {}", category1);
    }

    @Test
    void shouldDelete() {
        Category category = categoryRepository.findById(4).orElseThrow(EntityNotFoundException::new);
        categoryRepository.delete(category);
//        categoryRepository.deleteById(4);
    }

    @Test
    void shouldInsertCategoryWithProducts() {
        Category category = new Category();
        category.setName("Kategorija 5");
        category.setDescription("Kateforija 5 desc");

        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(45.48);
        product1.setCreateDate(new Date());
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(80.00);
        product2.setCreateDate(new Date());
        product2.setCategory(category);

        category.setProductList(List.of(product1, product2));

        categoryRepository.save(category);
    }

    @Test
    void shouldDeleteCategoryWithProducts() {
        Category category = categoryRepository.getById(8);
        categoryRepository.delete(category);
    }

    @Test
    void shouldDeleteCategoryWithProductsOrphan() {
        Category category = categoryRepository.getReferenceById(9);
        categoryRepository.delete(category);
    }

    @Test
    @Transactional//zbog aktivne sesije
    void shouldUpdateCategoryWithNewProduct() {
        Category category = categoryRepository.findByIdCustom(3);

        Product product = new Product();
        product.setName("Product novi");
        product.setPrice(100.00);
        product.setCreateDate(new Date());

        //productList.add(product);
        //category.setProductList(productList);
        category.add(product);

        categoryRepository.save(category);

    }

}
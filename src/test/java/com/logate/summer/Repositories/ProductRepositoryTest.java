package com.logate.summer.Repositories;

import com.logate.summer.SummerApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = SummerApplication.class)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void shouldInsertIntoCustom() {
        productRepository.insertProductCustomMethod("test", "test shortDesc", "test longDesc", 100.00, new Date());
    }

    @Test
    void shouldUpdateCustom() {
        productRepository.updateProductCustomMethodById(17, "changed product name");
    }

    @Test
    void shouldDeleteCustom() {
        productRepository.deleteProductCustomMethodById(17);
    }

}
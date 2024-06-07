package com.logate.summer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SummerApplication.class)
class ShopTest {

    @Autowired
    Shop shop;

    @Test
    public void ispisShopDIProduct() {
        shop.needSProduct();
    }

}
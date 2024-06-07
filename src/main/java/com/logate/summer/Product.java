package com.logate.summer;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Optional;


public class Product {


//    @PreDestroy
//    @PostConstruct
    String productName;
    Double price;

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }

    public Product() {

    }

    public Product(String productName, Double price) {
        this.productName = productName;
        this.price = price;
    }

    public void doSomethin() {
        System.out.println(productName);
        System.out.println(price);
    }
}

package com.logate.summer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//1. Dekorator na class
//DI in Spring Boot
//1. constructor
//2. @Autowired
//3. setter
@Component(value = "prodavnica")
//@Scope(value = "prototype")
public class Shop {

    //2. @Autowired
    @Autowired
    Item item;

    @Autowired
    Product product;

    @Autowired
    @Qualifier("product2")
    Product p;

    //1. constructor
//    public Shop(Item item) {
//        this.item = item;
//    }


    //3.setter
//    public void setItem(Item item) {
//        this.item = item;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }

    public void needsItem() {
        item.doSomethin();
    }

    public void needSProduct() {
        product.doSomethin();
        p.doSomethin();
    }
}
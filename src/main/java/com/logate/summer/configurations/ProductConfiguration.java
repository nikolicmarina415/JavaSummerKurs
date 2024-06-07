package com.logate.summer.configurations;

import com.logate.summer.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfiguration {

    //@Bean(name = "p")
    @Bean
    public Product product() {
        Product product = new Product();
        return product;
    }

    @Bean
    public Product product2() {
        Product product = new Product("Neki product", 28.5);
        return product;
    }
}

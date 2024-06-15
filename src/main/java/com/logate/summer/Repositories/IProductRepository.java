package com.logate.summer.Repositories;
import com.logate.summer.entities.Product;
import com.logate.summer.projection.ProductCustomProjection;
import com.logate.summer.projection.ProductProjection;
import com.logate.summer.records.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    //JPA

    //select * from product where created_date >= ?1
    List<Product> findByCreatedDateGreaterThanEqual(LocalDate startDate);

    //select * from product where price > ?1
    List<Product> findByPriceGreaterThan(Double price);

   // select * from product join category on product.category_id = category.id where price between ?1 and ?2 and category.name = ?3
    List<Product> findByPriceBetweenAndCategoryName(Double minPrice, Double maxPrice, String categoryName);

    //JPQL

    //select * from product join category on product.category_id = category.id where price between ?1 and ?2 and category.name = ?3
    @Query("SELECT p FROM Product p JOIN p.category c WHERE p.price BETWEEN ?1 AND ?2 AND c.name = ?3")
    List<Product> findByPriceBetweenAndCategoryName1(Double minPrice, Double maxPrice, String categoryName);

    //select product.name, product.short_description, product.price from product
    // join category on product.category_id = category.id where price between ?1 and ?2
    // and category.name = ?3 (uz pomoc constructor projection/DTO)
    @Query("SELECT p FROM Product p JOIN p.category c " +
            "WHERE p.price BETWEEN :minPrice AND :maxPrice AND c.name = :categoryName")
    List<Product> findProductsByPriceRangeAndCategoryName2(Double minPrice, Double maxPrice, String categoryName);

    //select product.name, product.short_description, product.price from product join category
    // on product.category_id = category.id where price between ?1 and ?2 and category.name = ?3
    // (uz pomoc tuple projection)
    @Query("SELECT p.name AS name, p.shortDescription AS shortDescription, p.price AS price " +
            "FROM Product p JOIN p.category c " +
            "WHERE p.price BETWEEN ?1 AND ?2 AND c.name = ?3")
    List<ProductProjection> findProductsByPriceRangeAndCategoryName3(Double minPrice, Double maxPrice, String categoryName);

    //select product.name, product.short_description, product.price from product join category
    // on product.category_id = category.id where price between ?1 and ?2 and category.name = ?3
    // (uz pomoc custom projection)
    @Query("SELECT p.name AS name, p.shortDescription AS shortDescription, p.price AS price " +
            "FROM Product p JOIN p.category c " +
            "WHERE p.price BETWEEN ?1 AND ?2 AND c.name = ?3")
    List<ProductCustomProjection> findProductsByPriceRangeAndCategoryName4(Double minPrice, Double maxPrice, String categoryName);

    //select product.name, product.short_description, product.price from product join category
    // on product.category_id = category.id where price between ?1 and ?2 and category.name = ?3
    // (uz pomoc records)
    @Query("SELECT NEW com.example.projections.ProductRecordProjection(p.name, p.shortDescription, p.price) " +
            "FROM Product p JOIN p.category c " +
            "WHERE p.price BETWEEN ?1 AND ?2 AND c.name = ?3")
    List<ProductRecord> findProductsByPriceRangeAndCategoryName5(Double minPrice, Double maxPrice, String categoryName);
}

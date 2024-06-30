package com.logate.summer.Repositories;

import com.logate.summer.dto.ProductDTO;
import com.logate.summer.entities.Product;
import com.logate.summer.projection.ProductProjection;
import com.logate.summer.records.ProductRecord;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //JPA Method Query
    //select * from product where created_date >= ?1
    List<Product> findByCreateDateGreaterThanEqual(Date gteCreatedDate);

    //select * from product where price > ?1
    List<Product> findByPriceGreaterThan(Double gtPrice);

    //select * from product join category on product.category_id = category.id where price between ?1 and ?2 and category.name = ?3
    List<Product> findByPriceBetweenAndCategoryName(Double startPrice, Double endPrice, String name);

    //JPQL
    //select * from product join category on product.category_id = category.id where price between ?1 and ?2 and category.name = ?3
    @Query(value = """
            select product from Product product
            join fetch Category category
            where product.price between :startPrice and :endPrice
            and category.name = :categoryName
            """)
    List<Product> findByPriceScaleAndCategoryName(@Param("startPrice") Double startPrice, @Param("endPrice") Double endPrice,
                                                  @Param("categoryName") String name);

    //construct projection
    @Query(value = """
            select new com.logate.summer.dto.ProductDTO(product.name, product.shortDesc, product.price)
            from Product product
            join fetch Category category
            where product.price between :startPrice and :endPrice
            and category.name = :categoryName
            """)
    List<ProductDTO> findByPriceScaleAndCategoryNameDTO(@Param("startPrice") Double startPrice, @Param("endPrice") Double endPrice,
                                                        @Param("categoryName") String name);

    //tuple projection
    @Query(value = """
            select product.name name, product.shortDesc shortDesc, product.price price
            from Product product
            join fetch Category category
            where product.price between :startPrice and :endPrice
            and category.name = :categoryName
            """)
    List<Tuple> findByPriceScaleAndCategoryNameTuple(@Param("startPrice") Double startPrice, @Param("endPrice") Double endPrice,
                                                     @Param("categoryName") String name);

    //custom projection
    @Query(value = """
            select product.name name, product.shortDesc shortDesc, product.price price
            from Product product
            join fetch Category category
            where product.price between :startPrice and :endPrice
            and category.name = :categoryName
            """)
    List<ProductProjection> findByPriceScaleAndCategoryNameCustom(@Param("startPrice") Double startPrice, @Param("endPrice") Double endPrice,
                                                                  @Param("categoryName") String name);

    //custom projection
    @Query(value = """
            select new com.logate.summer.records.ProductRecord(product.name, product.shortDesc, product.price)
            from Product product
            join fetch Category category
            where product.price between :startPrice and :endPrice
            and category.name = :categoryName
            """)
    List<ProductRecord> findByPriceScaleAndCategoryNameRecords(@Param("startPrice") Double startPrice, @Param("endPrice") Double endPrice,
                                                               @Param("categoryName") String name);

    @Transactional
    @Modifying
    @Query(value = """
            insert into product (name, short_description, long_description, price, created_date)
            values (:name, :shortDesc, :longDesc, :price, :createdDate)
            """, nativeQuery = true)
    void insertProductCustomMethod(@Param("name") String name, @Param("shortDesc") String shortDesc,
                                   @Param("longDesc") String longDesc, @Param("price") Double price, @Param("createdDate") Date createdDate);

    @Transactional
    @Modifying
    @Query(value = """
            update Product product
            set name = :name
            where product.id = :id
            """)
    void updateProductCustomMethodById(@Param("id") Integer id, @Param("name") String name);


    @Transactional
    @Modifying
    @Query(value = """
            delete from Product product
            where product.id = :id
            """)
    void deleteProductCustomMethodById(@Param("id") Integer id);

}
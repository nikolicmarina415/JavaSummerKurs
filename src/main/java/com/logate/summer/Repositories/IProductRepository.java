package com.logate.summer.Repositories;
import com.logate.summer.dto.ProductDTO;
import com.logate.summer.entities.Product;
import com.logate.summer.projection.ProductCustomProjection;
import com.logate.summer.projection.ProductProjection;
import com.logate.summer.records.ProductRecord;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query(value = """
            select product from Product product
            join fetch Category category
            where product.price between :startPrice and :endPrice
            and category.name = :categoryName
            """)
    List<Product> findByPriceScaleAndCategoryName(@Param("startPrice") Double startPrice, @Param("endPrice") Double endPrice,
                                                  @Param("categoryName") String name);

    //select product.name, product.short_description, product.price from product
    // join category on product.category_id = category.id where price between ?1 and ?2
    // and category.name = ?3 (uz pomoc constructor projection/DTO)
    @Query(value = """
            select new com.logate.summer.dto.ProductDTO(product.name, product.shortDesc, product.price)
            from Product product
            join fetch Category category
            where product.price between :startPrice and :endPrice
            and category.name = :categoryName
            """)
    List<ProductDTO> findByPriceScaleAndCategoryNameDTO(@Param("startPrice") Integer startPrice, @Param("endPrice") Integer endPrice,
                                                        @Param("categoryName") String name);


    //select product.name, product.short_description, product.price from product join category
    // on product.category_id = category.id where price between ?1 and ?2 and category.name = ?3
    // (uz pomoc tuple projection)
    @Query(value = """
            select product.name name, product.shortDesc shortDesc, product.price price
            from Product product
            join fetch Category category
            where product.price between :startPrice and :endPrice
            and category.name = :categoryName
            """)
    List<Tuple> findByPriceScaleAndCategoryNameTuple(@Param("startPrice") Integer startPrice, @Param("endPrice") Integer endPrice,
                                                     @Param("categoryName") String name);

    //select product.name, product.short_description, product.price from product join category
    // on product.category_id = category.id where price between ?1 and ?2 and category.name = ?3
    // (uz pomoc custom projection)
    @Query(value = """
            select product.name name, product.shortDesc shortDesc, product.price price
            from Product product
            join fetch Category category
            where product.price between :startPrice and :endPrice
            and category.name = :categoryName
            """)
    List<ProductProjection> findByPriceScaleAndCategoryNameCustom(@Param("startPrice") Integer startPrice, @Param("endPrice") Integer endPrice,
                                                                  @Param("categoryName") String name);

    //select product.name, product.short_description, product.price from product join category
    // on product.category_id = category.id where price between ?1 and ?2 and category.name = ?3
    // (uz pomoc records)
    @Query(value = """
            select new com.logate.summer.records.ProductDTO(product.name, product.shortDesc, product.price)
            from Product product
            join fetch Category category
            where product.price between :startPrice and :endPrice
            and category.name = :categoryName
            """)
    List<ProductProjection> findByPriceScaleAndCategoryNameRecords(@Param("startPrice") Integer startPrice, @Param("endPrice") Integer endPrice,
                                                                   @Param("categoryName") String name);
}

package com.logate.summer.Repositories;

import com.logate.summer.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = """
            select category, product from Category category
            join fetch Product product on category.id = product.category.id
            where category.id = :id
            """)
    Category findByIdCustom(@Param("id") Integer id);


    @Query(value = """
            select category from Category category
            where name = :name
            """)
    List<Category> findAllBySomething(@Param("name") String name);
}
package com.logate.summer.Repositories;

import com.logate.summer.entities.Departament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartamentRepository extends JpaRepository<Departament, Integer>, JpaSpecificationExecutor<Departament> {

    @Query(value = """
            select departament from Departament departament
            join fetch User user on user.departament.id = departament.id
            """, countQuery = "select count(departament) from Departament departament")
    Page<Departament> findDepartmentWithUsersPaging(Pageable pageable);

    @Query(value = """
            select departament.id from Departament departament
            """)
    List<Integer> findDepartmentPaging(Pageable pageable);

    @Query(value = """
            select departament from Departament departament
            join fetch User user on user.departament.id = departament.id
            where departament.id in (:id)
            """)
    List<Departament> findDepartmentWithUsersPaging(@Param("id") List<Integer> stringList);
}
package com.logate.summer.Repositories;

import com.logate.summer.SummerApplication;
import com.logate.summer.entities.Departament;
import com.logate.summer.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
@SpringBootTest(classes = SummerApplication.class)
@Slf4j
class DepartamentRepositoryTest {

    @Autowired
    DepartamentRepository departamentRepository;

    @Test
    void shouldReturnDepartamentWithUsersPaging() {
        Pageable pageable = PageRequest.of(0, 1);
        Page<Departament> departamentPage = departamentRepository.findDepartmentWithUsersPaging(pageable);
        List<Departament> departamentList = departamentPage.getContent();
        for (Departament departament : departamentList) {
            log.info("Department je: {}", departament);
        }

    }

    //In memory paging
    @Test
    void shouldReturnDepartamentWithUsersPagingCorrect() {
        Pageable pageable = PageRequest.of(0, 1);
        List<Integer> integerList = departamentRepository.findDepartmentPaging(pageable);
        List<Departament> departamentList = departamentRepository.findDepartmentWithUsersPaging(integerList);
        for(Departament departament : departamentList) {
            log.info("Departament with users:{}", departament);
//            log.info("User za dept: {}", departament.getUserList().get(0));
        }
    }

}
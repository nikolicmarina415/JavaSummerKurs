package com.logate.summer.specification;

import com.logate.summer.entities.Departament;
import com.logate.summer.entities.User;
import com.logate.summer.filter.UserSeachSpec;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserSpecification implements Specification<User> {

    private final UserSeachSpec userSeachSpec;

//    public UserSpecification(UserSeachSpec userSeachSpec) {
//        this.userSeachSpec = userSeachSpec;
//    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();

        checkFirstname(predicateList, root, criteriaBuilder);
        checkLastname(predicateList, root, criteriaBuilder);
        checkDeptName(predicateList, root, criteriaBuilder);

//        return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        return criteriaBuilder.or(predicateList.toArray(new Predicate[predicateList.size()]));

    }

    private void checkDeptName(List<Predicate> predicateList, Root<User> root, CriteriaBuilder criteriaBuilder) {
        if(StringUtils.isNotBlank(userSeachSpec.getDepartamentName())) {
            Join<User, Departament> userDepartamentJoin = root.join("departament");
            Predicate predicate = criteriaBuilder.equal(userDepartamentJoin.get("name"), userSeachSpec.getDepartamentName());
            predicateList.add(predicate);
        }
    }

    //like '%?1%'
    private void checkLastname(List<Predicate> predicateList, Root<User> root, CriteriaBuilder criteriaBuilder) {
        if(StringUtils.isNotBlank(userSeachSpec.getLastName())) {
            Predicate predicate = criteriaBuilder.like(root.get("lastName"), "%" + userSeachSpec.getLastName() + "%");
            predicateList.add(predicate);
        }
    }

    private void checkFirstname(List<Predicate> predicateList, Root<User> root, CriteriaBuilder criteriaBuilder) {
        if(StringUtils.isNotBlank(userSeachSpec.getFirstName())) {
            Predicate predicate = criteriaBuilder.equal(root.get("firstName"),userSeachSpec.getFirstName());
            predicateList.add(predicate);
        }
    }

}
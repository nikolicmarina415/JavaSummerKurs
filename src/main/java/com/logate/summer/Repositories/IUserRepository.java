package com.logate.summer.Repositories;

import com.logate.summer.dto.UserFirstNameLastName;
import com.logate.summer.entities.User;
import com.logate.summer.projection.UserProjection;
import com.logate.summer.records.UserRecord;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    //1. JPA Method Query
    //2. JPQL - jakarta persistence query language


    // 1. JPA Method Query
    //select * from user where last_name = ?1 and age = ?2
    List<User> findByLastNameAndAge(String lastName, Integer age);

    //select * from user where  first_name = ?1 or last_name = ?2
    List<User> findByFirstNameOrLastName(String firstName, String lastName);

    //select * from user where username like '%?1'
    List<User> findByUsernameStartingWith(String partOfUserName);

    //select * from user where is_active = true
    List<User> findByIsActiveTrue();

    //select * form user join department on user.department_id = departament.id where user.username = ?1 departament.name like '?2%'
    List<User> findByUsernameAndDepartamentNameEndingWith(String username, String partOfDeptName);

    //2. JPQL

    //select * from user where last_name = ?1 and age = ?2
    @Query(value = "SELECT user FROM User as user WHERE user.lastName = ?1 and user.age = ?2") //nativeQuery = true
    List<User> findByLastNameAndAgeJPQL(String lastName, Integer age);

    //imenovani params
    @Query(value = "SELECT user FROM User as user WHERE user.lastName = :lastname and user.age = :age") //nativeQuery = true
    List<User> findByLastNameAndAgeJPQLNamedParam(@Param("age") Integer age, @Param("lastname") String lastName);

    //select * from user where username like '%?1'
//    @Query(value = "select user from User user where user.username like '%:username'")
    @Query(value = "select user from User user where user.username like concat('%',:username)")
    List<User> findByUsernameLike(@Param("username") String userName);

    //select * form user join department on user.department_id = departament.id where user.username = ?1 and departament.name like '?2%'
    //eager fetch type no issue
    @Query(value = "select user from User user " +
            "join Departament departament " +
            "where user.username = :username and departament.name like concat(:name, '%')")
    List<User> findByUsernameAndDepartmentNameJPQL(@Param("name") String deptName, @Param("username") String userName);

    //lazy fetch
    @Query(value = "select user from User user " +
            "join fetch Departament departament " +
            "where user.username = :username and departament.name like concat(:name, '%')")
    List<User> findByUsernameAndDepartmentNameJPQLLazy(@Param("name") String deptName, @Param("username") String userName);

    //select * from user where department_id = 1
    // 1. upit ako je lazy fetch @Query(value="select user from User user where departament.id = 1)
    // 2. upit sve podatke o dept @Query(value="select .... join fetch)

    //select user.password from user where id = ?1
    @Query(value = "select user.password from User user where user.id = ?1")
    String findPasswordById(Integer id);

    //select user.age from user where username = ?1
    @Query(value = "select user.age from User user where user.username = ?1")
    List<Integer> findAgeByUserName(String username);


    //select user.fist_name, user.last_name from user where is_active = true
    //construct projection
    @Query(value = "select new com.logate.summer.dto.UserFirstNameLastName(user.firstName, user.lastName) " +
            "from User user " +
            "where user.isActive = true")
    List<UserFirstNameLastName> findByFirstNameLastName();

    //tuple
    @Query(value = "select user.firstName firstName, user.lastName lastName " +
            "from User user where user.isActive = true")
    List<Tuple> findByFirstNameLastNameTuple();

    //custom projection
    @Query(value = "select user.firstName fistName, user.lastName lastName " +
            "from User user where user.isActive = true")
    List<UserProjection> findByFirstNameLastNameCustomTuple();

    //records
    @Query(value = "select new com.logate.summer.records.UserRecord(user.firstName, user.lastName) " +
            "from User user " +
            "where user.isActive = true")
    List<UserRecord> findByFirstNameLastNameCustomRecords();

}
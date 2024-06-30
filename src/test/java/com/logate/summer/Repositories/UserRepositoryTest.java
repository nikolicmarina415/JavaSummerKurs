package com.logate.summer.Repositories;

import com.logate.summer.SummerApplication;
import com.logate.summer.entities.Departament;
import com.logate.summer.entities.Role;
import com.logate.summer.entities.User;
import com.logate.summer.filter.UserSeachSpec;
import com.logate.summer.specification.UserSpecification;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SummerApplication.class)
@Slf4j
class UserRepositoryTest {

    //    private final static Logger LOGGER = LoggerFactory.getLogger(UserRepositoryTest.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    DepartamentRepository departamentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    void shouldFindByUserNameStartingWith() {
        List<User> userList = userRepository.findByUsernameStartingWith("mm");
        for (User user : userList) {
            log.info("User podaci su: {}", user);
            assertEquals("mmarkovic",user.getUsername());
        }

    }

    @Test
    @Transactional
    void shouldfindByFirstNameLastNameTuple() {
        List<Tuple> userList = userRepository.findByFirstNameLastNameTuple();
        for (Tuple tuple : userList) {
            String firstName = (String) tuple.get("firstName");
            log.info("First name je {}", firstName);
        }

    }

    @Test
    void shouldReturnAllUsersWithPaging() {

        Pageable pageable = PageRequest.of(2, 1);
        Page<User> userPage = userRepository.findAll(pageable);

//        userPage.getNumberOfElements(); //broj elemenata - count(*)
//        userPage.getTotalPages(); //ukupan broj stranica
//        userPage.getContent(); //lista korisnika
//        userPage.hasNext(); //boolean sledeca stranica
//        userPage.hasPrevious(); //boolean prethodna stranica

        log.info("Ukupan broj elementa je {}", userPage.getTotalElements());
        log.info("Broj stranica je {}", userPage.getTotalPages());
        log.info("Lista user-a: {}", userPage.getContent());
        log.info("Da li postoji sledece strana: {}", userPage.hasNext());

    }

    @Test
    void shouldReturnAllUsersWithSlice() {

        Pageable pageable = PageRequest.of(2, 1);
        Slice<User> userSlice = userRepository.findByUsernameSlice("sradenovic", pageable);

        userSlice.getNumberOfElements();
        userSlice.getContent(); //lista korisnika
        userSlice.hasNext(); //boolean sledeca stranica
        userSlice.hasPrevious(); //boolean prethodna stranica
    }

    @Test
    void shouldReturnSpecification() {
        UserSeachSpec userSeachSpec =  new UserSeachSpec();
        userSeachSpec.setFirstName("Marko");
        userSeachSpec.setLastName("Radenovic");
        UserSpecification userSpecification = new UserSpecification(userSeachSpec);
        List<User> userList = userRepository.findAll(userSpecification);
        for(User user : userList) {
            log.info("User objekat: {}", user);
        }
    }

    @Test
    void shouldAddNewUserWithRoles() {
        User user =  new User();
        user.setFirstName("FF1");
        user.setLastName("LL1");
        user.setUsername("usernamename");
        user.setPassword("test123");
        user.setDepartament(departamentRepository.findById(2).orElseThrow(EntityNotFoundException::new));

        Role role = new Role();
        role.setName("ROLE_NEW");
        role.setDescription("role new desc");

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        user.setRoleSet(roleSet);

        userRepository.save(user);
    }

    //for user update his roles
    @Test
    void updateUsersRoles(){
        User user = userRepository.findById(5).orElseThrow(EntityNotFoundException::new);
        Role role = roleRepository.findById(1).orElseThrow(EntityNotFoundException::new);

        user.addRole(role);
        userRepository.save(user);
    }

    @Test
    void updateUsersRolesWithChangedRoleDesc(){
        User user = userRepository.findById(5).orElseThrow(EntityNotFoundException::new);
        Role role = roleRepository.findById(1).orElseThrow(EntityNotFoundException::new);

        role.setDescription("admin role changed");

        user.addRole(role);
        userRepository.save(user);
    }
}
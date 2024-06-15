package com.logate.summer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // AUTO => MYSQL = AUTO_INCREMENT | ORACLE, POSTRESQL, MS SQL = SEQUNCE
    // IDENTITY = AUTO_INCREMENT
    // TABLE
    @Column(name = "id")
    Integer id;

    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;

    String username;
    String password;
    Integer age;
    @Column(name = "is_active")
    Boolean isActive = false;//Boolean.FALSE


    //fetch type @ManyToOne i @OneToOne EAGER, @OneToMany i @ManyToMany LAZY
    @ManyToOne(fetch = FetchType.LAZY)
//    @ManyToOne
    @JoinColumn(name = "departament_id")
//    @JsonIgnoreProperties
//     @JsonBackReference
            Departament departament;

    //    @ManyToMany
//    @JoinTable(name = "user_role",
//    joinColumns = @JoinColumn(name = "user_id"),
//    inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(mappedBy = "userSet")
    Set<Role> roleSet = new HashSet<>();
}
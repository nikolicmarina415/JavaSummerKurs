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
@ToString
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
    @ToString.Exclude
//    @JsonIgnoreProperties
//     @JsonBackReference
            Departament departament;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    @ManyToMany(mappedBy = "userSet")
    @ToString.Exclude
    Set<Role> roleSet = new HashSet<>();

    @OneToOne(mappedBy = "user")
    UserDetails userDetails;

    public void addRole(Role role) {
        if(role !=  null) {
            roleSet.add(role);
        }
    }

    public void removeRole(Role role) {
        if(role != null) {
            roleSet.remove(role);
        }
    }
}
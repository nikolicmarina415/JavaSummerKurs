package com.logate.summer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departament")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Departament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;
    String description;
    @Column(name = "is_active")
    Boolean isActive = true;

    //    @JsonIgnore
//    @JsonManagedReference
    @OneToMany(mappedBy = "departament")
//    @OneToMany
//    @JoinTable(name = "user", joinColumns = @JoinColumn(name = "departament_id"))
            List<User> userList = new ArrayList<>();
}
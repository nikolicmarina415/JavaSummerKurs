package com.logate.summer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    @Id
    @Column(name = "user_id")
    Integer id;

    String city;
    String address;

    @OneToOne
    @MapsId
    User user;
}
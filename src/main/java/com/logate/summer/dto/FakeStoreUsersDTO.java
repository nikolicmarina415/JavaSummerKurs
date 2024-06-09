package com.logate.summer.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FakeStoreUsersDTO {

    Integer id;
    String email;
    String username;
    String password;
    String name;
}

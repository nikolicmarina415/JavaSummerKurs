package com.logate.summer.dto.fakestore;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserFakeDTONoID {

    String email;
    String username;
    String password;
    Name name;
    Address address;
    String phone;
}
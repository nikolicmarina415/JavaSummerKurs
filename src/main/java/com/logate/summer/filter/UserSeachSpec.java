package com.logate.summer.filter;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSeachSpec {

    String firstName;
    String lastName;
    String departamentName;

}
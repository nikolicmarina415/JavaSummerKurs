package com.logate.summer.dto.fakestore;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    String city;
    String street;
    Integer number;
    String zipcode;
    Geolocation geolocation;

}
package com.logate.summer.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FakeStoreUsersNoID {

    String email;
    String username;
    String password;
    String name;
    String adress;
    String phone;

    @Getter
    @Setter
    private static class Name {
        String firstname;
        String lastname;

    }

    @Getter
    @Setter
    public static class Adress {
        String city;
        String street;
        Integer number;
        String zipcode;
        FakeStoreUsersDTO.Adress.Geolocation geolocation;

        @Getter
        @Setter
        public static class Geolocation {
            String latitude;
            String longitude;
        }

    }

}

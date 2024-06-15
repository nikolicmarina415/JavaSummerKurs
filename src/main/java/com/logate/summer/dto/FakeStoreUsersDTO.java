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
    Name name;
    Adress adress;
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
        Geolocation geolocation;

        @Getter
        @Setter
        public static class Geolocation {
            String latitude;
            String longitude;
        }

    }

}

package com.logate.summer.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Movie {
    Integer id;
    String title;
    String genre;
    Integer year;
    Double ticket;
    String description;
}

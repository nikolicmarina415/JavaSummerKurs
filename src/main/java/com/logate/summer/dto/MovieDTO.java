package com.logate.summer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovieDTO {

    private Integer id;
    private String title;
    private Integer year;
    private Double ticket;
    private String description;

}
package com.logate.summer.filter;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieFilter {
    private Integer fromYear;
    private Integer toYear;
    private String withTitle;
}
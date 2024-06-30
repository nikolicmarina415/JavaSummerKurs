package com.logate.summer.dto.category.command;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryCommandDTO {

    String name;
    String desc;
    Boolean isActive;

}
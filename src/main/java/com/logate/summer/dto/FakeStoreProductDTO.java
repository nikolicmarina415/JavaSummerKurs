package com.logate.summer.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FakeStoreProductDTO {

    Integer id;
    String title;
    Double price;
    String category;
    String description;
    String image;

}

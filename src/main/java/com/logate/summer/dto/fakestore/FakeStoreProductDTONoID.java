package com.logate.summer.dto.fakestore;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FakeStoreProductDTONoID {

    String title;
    Double price;
    String category;
    String description;
    String image;

}
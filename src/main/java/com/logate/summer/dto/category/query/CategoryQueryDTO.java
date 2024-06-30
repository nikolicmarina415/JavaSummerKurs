package com.logate.summer.dto.category.query;

import com.logate.summer.entities.Product;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryQueryDTO {

    String name;
    String desc;

}
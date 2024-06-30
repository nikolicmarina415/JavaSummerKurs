package com.logate.summer.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@DynamicUpdate
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "description")
    String description;
    @Column(name = "is_active")
    Boolean isActive = true;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) //{CascadeType.PERSIST, CascadeType.MERGE}
    @ToString.Exclude
    List<Product> productList = new ArrayList<>();

    public void add(Product product) {
        if(product !=  null) {
            this.productList.add(product);
        }
    }

    public void remove(Product product){
        if(product != null) {
            this.productList.remove(product);
        }
    }

}
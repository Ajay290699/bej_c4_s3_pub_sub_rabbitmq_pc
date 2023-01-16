package com.niit.Product.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    private int productId;
    private  String productName;
    private String productDesc;
    private int price;
    private boolean isViewed;


}

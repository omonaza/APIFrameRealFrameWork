package com.devxschool.summer.pojos.fooddelivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequest {
    private String description;
    private String imageUrl;
    private String price;
    private String name;
    private String foodType;
}

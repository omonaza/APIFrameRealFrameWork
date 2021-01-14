package com.devxschool.summer.pojos.fooddelivery;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodResponse {
    private List<FoodRequest> foodCached;
}

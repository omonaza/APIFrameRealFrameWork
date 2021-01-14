package com.devxschool.summer.pojos.fooddelivery;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegistrationResponse {
    private String status;
    private String errorMessage;
    private UserInfo userInfo;
}

package com.devxschool.summer.pojos.fooddelivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String username;
    private String password;
    private String fullName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
}

package com.thrivefuse.bas.dto;
import lombok.Data;

@Data
public class UserDetails {

    private  String firstName;

    private String lastName;

    private String middleName;

    private String primaryPhone;

    private String secondaryPhone;

    private String address;

    private String state;

    private String country;

    private String city;

    private String zip;

    private String email;

    private UserPanCardAndAdharDetails userPanAndAdharDetails;

    private UserLogin userLogin;

}
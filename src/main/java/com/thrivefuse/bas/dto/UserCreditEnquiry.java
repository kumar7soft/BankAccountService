package com.thrivefuse.bas.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCreditEnquiry {

    private String userId;

    private String panCardNumber;

    private LocalDateTime enquiredDateAndTime;
}

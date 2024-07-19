package com.ameda.kevin.stripe_integration.dto;/*
*
@author ameda
@project stripe-integration
*
*/

import lombok.Data;

@Data
public class StripeTokenDto {

    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String token;
    private String username;
    private boolean success;
    private String cvc;
}

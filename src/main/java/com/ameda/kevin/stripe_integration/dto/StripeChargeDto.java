package com.ameda.kevin.stripe_integration.dto;/*
*
@author ameda
@project stripe-integration
*
*/

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class StripeChargeDto {

    private String stripeToken;
    private String username;
    private Double amount;
    private Boolean success;
    private String message;
    private String chargeId;
    private Map<String, Object> addInfo = new HashMap<>();
}

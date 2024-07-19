package com.ameda.kevin.stripe_integration.service;/*
*
@author ameda
@project stripe-integration
*
*/

import com.ameda.kevin.stripe_integration.dto.StripeChargeDto;
import com.ameda.kevin.stripe_integration.dto.StripeTokenDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class StripeService {

    @Value("${stripe.api.key}")
    private String apiKey;


    @PostConstruct
    public void initializeKey(){
        Stripe.apiKey = apiKey;
    }

    public StripeTokenDto createToken(StripeTokenDto dto){
        try{
            Map<String,Object> card = new HashMap<>();
            card.put("number",dto.getCardNumber());
            card.put("exp_month",dto.getExpMonth());
            card.put("exp_year",dto.getExpYear());
            card.put("cvc",dto.getCvc());

            Map<String,Object> params = new HashMap<>();
            params.put("card",card);
            Token token = Token.create(params);
            if(token !=null && token.getId() !=null){
                dto.setSuccess(true);
                dto.setToken(token.getId());
            }
            return  dto;
        }catch (StripeException ex){
            log.error("(createCardToken) ",ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public StripeChargeDto charge(StripeChargeDto request){
        try{
            request.setSuccess(false);
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount",(int) (request.getAmount()* 100));
            chargeParams.put("currency","USD");
            chargeParams.put("description","pay id: "+request.getAddInfo().getOrDefault("ID_TAG",""));
            chargeParams.put("source",request.getStripeToken());
            Map<String,Object> metadata = new HashMap<>();
            metadata.put("id",request.getChargeId());
            metadata.putAll(request.getAddInfo());
            Charge charge  = Charge.create(chargeParams);
            request.setMessage(charge.getOutcome().getSellerMessage());

            if(charge.getPaid()){
                request.setChargeId(charge.getId());
                request.setSuccess(true);
            }
            return request;
        }catch (StripeException ex){
            log.error("(charge)",ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
}

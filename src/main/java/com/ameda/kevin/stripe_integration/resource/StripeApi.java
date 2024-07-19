package com.ameda.kevin.stripe_integration.resource;/*
*
@author ameda
@project stripe-integration
*
*/

import com.ameda.kevin.stripe_integration.dto.StripeChargeDto;
import com.ameda.kevin.stripe_integration.dto.StripeTokenDto;
import com.ameda.kevin.stripe_integration.service.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stripe")
@RequiredArgsConstructor
public class StripeApi {

    private final StripeService stripeService;

    @PostMapping("/card/token")
    @ResponseBody
    public StripeTokenDto createCardToken(@RequestBody StripeTokenDto tokenRequest){
        return stripeService.createToken(tokenRequest);
    }
    @PostMapping("/charge")
    @ResponseBody
    public StripeChargeDto charge(@RequestBody StripeChargeDto chargeRequest){
        return stripeService.charge(chargeRequest);
    }
}

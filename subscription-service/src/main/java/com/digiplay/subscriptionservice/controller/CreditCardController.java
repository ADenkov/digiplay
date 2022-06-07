package com.digiplay.subscriptionservice.controller;

import com.digiplay.subscriptionservice.exception.CreditCardException;
import com.digiplay.subscriptionservice.model.CreditCard;
import com.digiplay.subscriptionservice.model.CreditCardResponseDTO;
import com.digiplay.subscriptionservice.service.CreditCardService;
import com.digiplay.subscriptionservice.service.CreditCardServiceImpl;
import com.digiplay.subscriptionservice.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(CreditCardController.BASE_URL)
public class CreditCardController {

    public static final String BASE_URL = "/api/v1";

    @Autowired
    private CreditCardServiceImpl creditCardService;

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createSubscription(@PathVariable("userId") Long userId, @RequestBody CreditCard cd) {
        try {
            this.subscriptionService.createNewSubscription(userId, cd);
        } catch (CreditCardException e) {
            return new ResponseEntity<CreditCardResponseDTO>(new CreditCardResponseDTO(cd.getNumber(), false, e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<CreditCardResponseDTO>(new CreditCardResponseDTO(cd.getNumber(), true, "OK"),
                HttpStatus.OK);
    }

    @GetMapping("/isvalid/{userId}")
    public ResponseEntity<?> isSubscriptionValid(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(subscriptionService.isSubscriptionValid(userId), HttpStatus.OK);

    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<?> getSubscription(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(subscriptionService.getSubscription(userId), HttpStatus.OK);

    }

    @DeleteMapping("/cancel/{userId}")
    public ResponseEntity<?> cancelSubscription(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(subscriptionService.cancelSubscription(userId), HttpStatus.OK);

    }


}

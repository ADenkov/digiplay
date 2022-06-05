package com.digiplay.subscriptionservice.service;

import com.digiplay.subscriptionservice.exception.CreditCardException;
import com.digiplay.subscriptionservice.model.CreditCard;

public interface CreditCardService {
    /**
     * Validate credit card details.
     *
     * @param card
     * 			CreditCard, represents a credit card.
     *
     * @throws CreditCardException
     */
    void validate(final CreditCard card) throws CreditCardException;
}

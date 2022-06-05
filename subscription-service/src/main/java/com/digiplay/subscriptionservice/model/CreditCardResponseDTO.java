package com.digiplay.subscriptionservice.model;

import lombok.Data;

@Data
public class CreditCardResponseDTO {
    private String number;
    private boolean valid;
    private String message;

    public CreditCardResponseDTO() {
        super();
    }

    public CreditCardResponseDTO(final String number, final boolean valid, final String message) {
        super();
        this.number = number;
        this.valid = valid;
        this.message = message;
    }
}

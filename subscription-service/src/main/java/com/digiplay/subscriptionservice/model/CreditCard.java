package com.digiplay.subscriptionservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String number;
    private String expiration;


    public CreditCard() {
        super();
    }

    public CreditCard(String number, String expiration) {
        super();
        this.number = number;
        this.expiration = expiration;
    }

}

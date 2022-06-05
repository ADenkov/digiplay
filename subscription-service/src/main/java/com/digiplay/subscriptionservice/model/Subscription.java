package com.digiplay.subscriptionservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Double price;

    @OneToOne(cascade=CascadeType.ALL,orphanRemoval = true)
    private CreditCard creditCard;
    private Timestamp validFrom;
    private Timestamp validUntil;
//    private boolean isValid;


//    public Subscription(Double price, CreditCard creditCard, Timestamp validFrom, Timestamp validUntil) {
//        super();
//        this.price = price;
//        this.creditCard = creditCard;
//        this.validFrom = validFrom;
//        this.validUntil = validUntil;
//    }


}

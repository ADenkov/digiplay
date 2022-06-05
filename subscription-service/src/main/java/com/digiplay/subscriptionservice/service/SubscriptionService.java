package com.digiplay.subscriptionservice.service;

import com.digiplay.subscriptionservice.model.CreditCard;
import com.digiplay.subscriptionservice.model.Subscription;
import com.digiplay.subscriptionservice.repository.CreditCardRepository;
import com.digiplay.subscriptionservice.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Service
public class SubscriptionService {

    @Autowired
    private CreditCardServiceImpl creditCardService;

    @Autowired
    private SubscriptionRepository repository;


    public Subscription createNewSubscription(CreditCard creditCard) {
        creditCardService.validate(creditCard);

        LocalDateTime now = LocalDateTime.now();
        Timestamp validFrom = Timestamp.valueOf(now);
        Timestamp validUntil = calculateValidUntil();

        return repository.save(Subscription.builder()
                .price(10.99)
                .creditCard(creditCard)
                .validUntil(validUntil)
                .validFrom(validFrom)
                .build());
    }

    private Timestamp calculateValidUntil() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        Date d = c.getTime();

        return new Timestamp(d.getTime());
    }

    public boolean isSubscriptionValid(Long subscriptionId) {
        Subscription subscription = repository.findById(subscriptionId).orElse(null);
        Timestamp validUntil = subscription.getValidUntil();
        return validUntil.after(Timestamp.valueOf(LocalDateTime.now()));
    }

    public boolean cancelSubscription(Long id) throws EntityNotFoundException {
        try {
            Subscription subscription = repository.findById(id).orElse(null);
            repository.delete(subscription);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Subscription doesn't exist");
        }

        return true;
    }

    public Subscription getSubscription(Long id) {
        return repository.findById(id).orElse(null);
    }
}

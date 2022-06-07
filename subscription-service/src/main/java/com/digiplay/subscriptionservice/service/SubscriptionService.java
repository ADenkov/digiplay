package com.digiplay.subscriptionservice.service;

import com.digiplay.subscriptionservice.model.CreditCard;
import com.digiplay.subscriptionservice.model.Subscription;
import com.digiplay.subscriptionservice.repository.SubscriptionRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@Service
public class SubscriptionService {

    @Autowired
    private CreditCardServiceImpl creditCardService;

    @Autowired
    private SubscriptionRepository repository;


    @KafkaListener(topics = {"deleteSubscription"}, groupId = "deleteSubscription")
    public void consume(ConsumerRecord<String, Long> record) {
        Long userId = record.value();
        Subscription sub = repository.findByUserId(userId);
        repository.delete(sub);
    }


    public Subscription createNewSubscription(long id, CreditCard creditCard) {
        creditCardService.validate(creditCard);

        LocalDateTime now = LocalDateTime.now();
        Timestamp validFrom = Timestamp.valueOf(now);
        Timestamp validUntil = calculateValidUntil();

        return repository.save(Subscription.builder()
                .price(10.99)
                .validUntil(validUntil)
                .validFrom(validFrom)
                .userId(id)
                .build());
    }

    private Timestamp calculateValidUntil() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        Date d = c.getTime();

        return new Timestamp(d.getTime());
    }

    public boolean isSubscriptionValid(Long userId) {
        Subscription subscription = repository.findByUserId(userId);
        Timestamp validUntil = subscription.getValidUntil();
        return validUntil.after(Timestamp.valueOf(LocalDateTime.now()));
    }

    public boolean cancelSubscription(Long userId) throws EntityNotFoundException {
        try {
            Subscription subscription = repository.findByUserId(userId);
            repository.delete(subscription);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Subscription doesn't exist");
        }

        return true;
    }

    public Subscription getSubscription(Long userId) {
        return repository.findByUserId(userId);
    }
}

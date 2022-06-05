package com.digiplay.subscriptionservice.repository;

import com.digiplay.subscriptionservice.model.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription,Long> {
}

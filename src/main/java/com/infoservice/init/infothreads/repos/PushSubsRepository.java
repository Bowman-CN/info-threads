package com.infoservice.init.infothreads.repos;

import com.infoservice.init.infothreads.models.PushSubscriptions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PushSubsRepository extends MongoRepository<PushSubscriptions, String> {
    @Override
    public List<PushSubscriptions> findAll();

    @Override
    public PushSubscriptions insert(PushSubscriptions pushSubscriptions);

    public List<PushSubscriptions> findBykeys_auth(String authStr);
}

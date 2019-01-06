package com.infoservice.init.infothreads.repos;

import com.infoservice.init.infothreads.models.UserMetrics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<UserMetrics, String> {

    public UserMetrics findTopBy_id(String id);

    public UserMetrics findByEmail(String mail);

    @Query("{ $or : [ { email:?0 } , { mobile : ?1 } ] }")
    public UserMetrics findTopByMobileOrEmail(String email,String mobile);

    @Override
    public UserMetrics insert(UserMetrics s);
}

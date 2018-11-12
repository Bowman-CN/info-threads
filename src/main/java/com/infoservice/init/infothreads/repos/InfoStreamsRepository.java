package com.infoservice.init.infothreads.repos;

import com.infoservice.init.infothreads.models.InfoStreams;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;
import java.util.List;

public interface InfoStreamsRepository extends MongoRepository<InfoStreams, String>{
    @Override
    public List<InfoStreams> findAll();

    public Slice<InfoStreams> findAllByOrderByInfoTimestampDesc(Pageable pageable);
}

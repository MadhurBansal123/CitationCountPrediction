package com.citation.prediction.repository;

import com.citation.prediction.model.CitDate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CitDateRepo extends MongoRepository<CitDate, Long> {
    CitDate findByNodeId(String nodeId);
}

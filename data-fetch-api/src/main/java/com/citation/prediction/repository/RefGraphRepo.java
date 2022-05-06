package com.citation.prediction.repository;

import com.citation.prediction.model.ReferenceGraph;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefGraphRepo extends MongoRepository<ReferenceGraph, Integer> {
    ReferenceGraph findByFromNodeId(String fromNodeId);
}

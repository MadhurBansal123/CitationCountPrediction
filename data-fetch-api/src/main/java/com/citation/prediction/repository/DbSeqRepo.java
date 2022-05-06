package com.citation.prediction.repository;

import com.citation.prediction.model.DbSequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DbSeqRepo extends MongoRepository<DbSequence, Long> {
    DbSequence findById(String id);
}

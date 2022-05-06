package com.citation.prediction.repository;

import com.citation.prediction.model.PaperDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaperDetailRepo extends MongoRepository<PaperDetails, Long> {
    PaperDetails findByNodeId(String nodeId);

}

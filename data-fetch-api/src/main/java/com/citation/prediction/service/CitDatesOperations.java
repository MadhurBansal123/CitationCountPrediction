package com.citation.prediction.service;

import com.citation.prediction.config.MongoConfig;
import com.citation.prediction.model.CitDate;
import com.citation.prediction.repository.CitDateRepo;
import com.citation.prediction.util.SequenceIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.citation.prediction.model.CitDate.CREATION_DATE_SEQUENCE_NAME;

@Import(MongoConfig.class)
@Slf4j
@Service
public class CitDatesOperations {
    @Autowired
    SequenceIdGenerator sequenceIdGenerator;

    @Autowired
    private CitDateRepo citDateRepo;

    public boolean insertRecord(String nodeId, Date creationDate, Boolean isCrossListed) {
        CitDate record = citDateRepo.findByNodeId(nodeId);
        try {
            if (record != null) {
                log.info("Record for Node ID:" + nodeId + " already exists with date <" + record.getCreationDate()
                        + ">. Cannot insert new date <" + creationDate.toString() + ">.");
                return false;
            } else {
                record = new CitDate();
                record.setCreationDate(creationDate);
                record.setNodeId(nodeId);
                record.setIsCrossListed(isCrossListed);
                record.setId(sequenceIdGenerator.getNextSequenceId(CREATION_DATE_SEQUENCE_NAME));
                citDateRepo.save(record);
            }
            log.info("Record Saved Successfully for NodeId:" + nodeId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public List<CitDate> findAll() {
        return citDateRepo.findAll();
    }
}

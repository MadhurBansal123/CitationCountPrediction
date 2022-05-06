package com.citation.prediction.service;

import com.citation.prediction.config.MongoConfig;
import com.citation.prediction.model.ReferenceGraph;
import com.citation.prediction.repository.RefGraphRepo;
import com.citation.prediction.util.SequenceIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import static com.citation.prediction.model.ReferenceGraph.REF_GRAPH_SEQUENCE_NAME;

@Import(MongoConfig.class)
@Slf4j
@Service
public class GraphLinkOperations {

    @Autowired
    SequenceIdGenerator sequenceIdGenerator;

    @Autowired
    private RefGraphRepo refGraphRepo;

    public boolean insertGraphLink(String fromLink, String toLink) {
        ReferenceGraph record = refGraphRepo.findByFromNodeId(fromLink);

        try {
            if (record == null) {
                record = new ReferenceGraph();
                record.setId(sequenceIdGenerator.getNextSequenceId(REF_GRAPH_SEQUENCE_NAME));
                record.setFromNodeId(fromLink);
                HashSet<String> toNodeSet = new HashSet<>();
                toNodeSet.add(toLink);
                record.setToNodeId(toNodeSet);
                log.info("Creating new Record for FromNodeId:" + fromLink + "\n");

            } else {
                HashSet<String> toNodeSet = record.getToNodeId();
                toNodeSet.add(toLink);
                record.setToNodeId(toNodeSet);
                log.info("Adding toLink:" + toLink + " to collection of fromLink:" + fromLink);
            }
            refGraphRepo.save(record);
            return true;
        } catch (Exception e) {
            log.error("Entry for fromLink:" + fromLink + " to toLink:" + toLink + " failed.\n");
            log.error(e.getMessage());
            return false;
        }

    }

    public List<ReferenceGraph> findAll() {
        return refGraphRepo.findAll();
    }


}

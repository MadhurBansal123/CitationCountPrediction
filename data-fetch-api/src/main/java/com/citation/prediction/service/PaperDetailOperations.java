package com.citation.prediction.service;

import com.citation.prediction.config.MongoConfig;
import com.citation.prediction.model.PaperDetails;
import com.citation.prediction.repository.PaperDetailRepo;
import com.citation.prediction.util.SequenceIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;


import static com.citation.prediction.model.PaperDetails.PAPER_DETAIL_SEQUENCE_NAME;

@Import(MongoConfig.class)
@Slf4j
@Service
public class PaperDetailOperations {
    @Autowired
    SequenceIdGenerator sequenceIdGenerator;

    @Autowired
    private PaperDetailRepo paperDetailRepo;

    public boolean insertPaperDetail(String title, String[] authors, String nodeId, Integer year) {
        PaperDetails details = paperDetailRepo.findByNodeId(nodeId);

        try {
            if (details == null) {
                details = new PaperDetails();
                details.setId(sequenceIdGenerator.getNextSequenceId(PAPER_DETAIL_SEQUENCE_NAME));
                details.setNodeId(nodeId);
                details.setAuthors(authors);
                details.setTitle(title);
                details.setYear(year);

                paperDetailRepo.save(details);
                log.info("Paper Detail save for node: " + nodeId);
            } else {
                log.error("Paper for node: " + nodeId + " already exists.");
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
}

package com.citation.prediction.util;

import com.citation.prediction.model.DbSequence;
import com.citation.prediction.repository.DbSeqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceIdGenerator {

    @Autowired
    DbSeqRepo dbSeqRepo;

    public long getNextSequenceId(String sequenceName) {
        DbSequence dbs = dbSeqRepo.findById(sequenceName);

        if (dbs == null) {
            dbs = new DbSequence();
            dbs.setId(sequenceName);
            dbs.setSeq(1L);
        } else {
            dbs.setSeq(dbs.getSeq() + 1);
        }
        dbSeqRepo.save(dbs);
        return dbs.getSeq();
    }
}

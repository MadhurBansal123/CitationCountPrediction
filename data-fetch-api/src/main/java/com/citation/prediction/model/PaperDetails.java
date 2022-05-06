package com.citation.prediction.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "paper_details")
public class PaperDetails {
    @Transient
    public static final String PAPER_DETAIL_SEQUENCE_NAME = "paper_detail_sequence";

    @Id
    private Long id;

    @Indexed(unique = true)
    private String nodeId;

    private String title;

    private String[] authors;

    private Integer year;
}

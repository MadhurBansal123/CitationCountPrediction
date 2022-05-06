package com.citation.prediction.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "cit_date")
public class CitDate {
    @Transient
    public static final String CREATION_DATE_SEQUENCE_NAME = "creation_date_sequence";

    @Id
    private Long id;

    @Indexed(unique = true)
    private String nodeId;

    private Date creationDate;

    private Boolean isCrossListed;
}

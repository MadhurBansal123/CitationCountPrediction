package com.citation.prediction.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;

@Data
@Document(collection = "reference_graph")
public class ReferenceGraph {

    @Transient
    public static final String REF_GRAPH_SEQUENCE_NAME = "ref_graph_sequence";

    @Id
    private Long id;

    @Indexed(unique = true)
    private String fromNodeId;

    private HashSet<String> toNodeId;
}

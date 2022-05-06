package com.citation.prediction.controller;

import com.citation.prediction.model.ReferenceGraph;
import com.citation.prediction.service.GraphLinkOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graphLink")
public class RefGraphController {

    @Autowired
    GraphLinkOperations graphLinkOperations;

    @GetMapping("/getAllLinks")
    public ResponseEntity<List<ReferenceGraph>> findAllLinks() {
        return new ResponseEntity<>(graphLinkOperations.findAll(), HttpStatus.OK);
    }
}

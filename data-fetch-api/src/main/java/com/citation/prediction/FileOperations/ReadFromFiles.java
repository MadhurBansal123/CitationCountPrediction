package com.citation.prediction.FileOperations;

import com.citation.prediction.service.CitDatesOperations;
import com.citation.prediction.service.GraphLinkOperations;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Slf4j
@Service
public class ReadFromFiles {

    @Autowired
    GraphLinkOperations graphLinkOperations;

    @Autowired
    CitDatesOperations citDatesOperations;


    //@PostConstruct
    private void createGraphLinks() {
        File myFile = new File("C:\\Users\\madhu\\Desktop\\ME SS\\Research Practice\\files\\Cit-HepTh.txt");
        try {
            Scanner sc = new Scanner(myFile);
            String fromLink, toLink;
            while (sc.hasNextLine()) {
                String s = sc.nextLine().trim();
                if (s.startsWith("#"))
                    continue;
                fromLink = s.split("\t")[0];
                toLink   = s.split("\t")[1];
                if(fromLink.length() < 7) {
                    fromLink = StringUtils.leftPad(fromLink, 7, '0');
                }
                if(toLink.length() < 7) {
                    toLink = StringUtils.leftPad(toLink, 7, '0');
                }

                graphLinkOperations.insertGraphLink(fromLink, toLink);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //@PostConstruct
    private void createCitDates() {
        File myFile = new File("C:\\Users\\madhu\\Desktop\\ME SS\\Research Practice\\files\\Cit-HepTh-dates.txt");
        try {
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String s = sc.nextLine().trim();
                if (s.startsWith("#"))
                    continue;
                String[] line = s.split("\t");
                line[0] = line[0].trim();
                line[1] = line[1].trim();
                Date createdDate = new SimpleDateFormat("yyyy-MM-dd").parse(line[1]);
                String nodeId;
                boolean isCrossListed;
                if (line[0].length() > 7 && line[0].startsWith("11")) {
                    isCrossListed = true;
                    nodeId = line[0].substring(2);
                } else if (line[0].length() == 7) {
                    isCrossListed = false;
                    nodeId = line[0];
                } else {
                    //entry skipped
                    log.error("Entry Skipped for " + s);
                    continue;
                }

                citDatesOperations.insertRecord(nodeId, createdDate, isCrossListed);
            }
            sc.close();
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

}
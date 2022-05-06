package com.citation.prediction.FileOperations;

import com.citation.prediction.service.PaperDetailOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

@Slf4j
@Service
public class ReadFilesFromRootFolder {

    @Autowired
    PaperDetailOperations paperDetailOperations;

    /*@PostConstruct*/
    private void extractAndSaveAllPaperDetailsFromFiles() {
        int count = 0;
        String nodeId;
        for(int year = 1992; year <= 2003; year++) {
            try {
                String rootFolder = "C:\\Users\\madhu\\Desktop\\ME SS\\Research Practice\\files\\cit-HepTh-abstracts\\";
                File srcFolder = new File(rootFolder + year);
                String fileExt;
                for(File srcFile: Objects.requireNonNull(srcFolder.listFiles())) {
                    count++;
                    String fName = srcFile.getName().trim();

                    Scanner sc = new Scanner(srcFile);
                    fileExt = fName.substring(fName.lastIndexOf(".")+1);
                    boolean flag;
                    StringBuilder title = new StringBuilder();
                    StringBuilder authors = new StringBuilder();
                    if(fileExt.equals("abs")) {
                        while(sc.hasNextLine()) {
                            String s = sc.nextLine().trim();
                            if(s.startsWith("Title:")) {
                                while(!s.startsWith("Author")) {
                                    title.append(" ").append(s);
                                    s=sc.nextLine().trim();
                                }
                                if(s.startsWith("Author")) {
                                    while(!s.startsWith("Comments:") && !s.startsWith("Journal-ref:") && (!s.startsWith("\\\\"))) {
                                        authors.append(" ").append(s);
                                        s=sc.nextLine().trim();
                                    }
                                    break;
                                }
                            }
                        }
                        authors = new StringBuilder(authors.substring(authors.toString().indexOf(":")+1).trim());
                        title = new StringBuilder(title.substring(title.toString().indexOf(":")+1).trim());
                        nodeId = fName.substring(0, fName.lastIndexOf("."));

                        saveAllPaperDetails(title.toString(), authors.toString(), year, nodeId);
                        sc.close();
                    } else {
                        log.error("File extension not supported :: " + fName);
                    }
                }
            }
            catch(Exception e) {
                log.error(e.getMessage());
            }
        }
        System.out.println(count);
    }

    private void saveAllPaperDetails(String title, String author, int year, String nodeId) {
        String[] authors = getAuthorsFromString(author);

        System.out.println("nodeId: " + nodeId);
        System.out.println("Year: " + year);
        System.out.println("Authors: " + Arrays.toString(authors));
        System.out.println("Title: " + title);
        System.out.println("-----------------------------------");

        paperDetailOperations.insertPaperDetail(title, authors, nodeId, year);
    }

    private String[] getAuthorsFromString(String authorStr) {
        authorStr = authorStr.replaceAll(" and", ",");
        authorStr = authorStr.replaceAll("\\\\", "");
        authorStr = authorStr.replaceAll("\\{", "");
        authorStr = authorStr.replaceAll("}", "");
        authorStr = authorStr.replaceAll("\\*", "");
        authorStr = authorStr.replaceAll("\\(1\\)", "");
        authorStr = authorStr.replaceAll("\\(2\\)", "");
        authorStr = authorStr.replaceAll("\\(3\\)", "");
        authorStr = authorStr.replaceAll("\\(4\\)", "");
        authorStr = authorStr.replaceAll("\\(5\\)", "");
        String sub;
        int count = 0;
        while(authorStr.contains("(") && count <= 10) {
            int bracketStart = authorStr.indexOf("(");
            int bracketStop = authorStr.indexOf(")");

            sub = authorStr.substring(bracketStart, bracketStop+1);
            authorStr = authorStr.replaceAll(sub, "");
            authorStr = authorStr.replaceAll("\\(\\)", "");
            count++;
        }
        String[] authors = authorStr.split(",");
        authors = Arrays.stream(authors).map(String::trim).toArray(String[]::new);

        return authors;
    }
}

package com.example.demo;


import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {
    private final static Logger log = LoggerFactory.getLogger(CsvService.class);


    public List<String[]> getAirports(){
        Reader reader = openReader();
        List<String[]> airports = new ArrayList<>();
        try {
            airports = new CSVReaderBuilder(reader).build().readAll();
            closeReader(reader);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return airports.isEmpty()? null: airports;
    }

    Reader openReader(){
        try {
            return Files.newBufferedReader(Path.of(new File("src/main/resources/airports.csv").getAbsoluteFile().toURI()));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    void closeReader(Reader reader){
        try {
            reader.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}

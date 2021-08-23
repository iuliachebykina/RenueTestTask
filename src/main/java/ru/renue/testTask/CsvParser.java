package ru.renue.testTask;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CsvParser {

    @Value("${app.column}")
    private Integer column;
    private final CSVReader reader;

    private final static Logger log = LoggerFactory.getLogger(CsvParser.class);


    public CsvParser(CSVReader reader) {
        this.reader = reader;
    }


    public Collection<List<String>> getAirports(String substring, Integer column) throws CsvValidationException, IOException {
        if(column != null)
            this.column = column;
        this.column--;
        return getAirports(substring);
    }

    Collection<List<String>> getAirports(String substring) throws IOException, CsvValidationException {
        if(column < 0 || column > 13){
            log.error("Такого столбца не существует");
            return null;
        }
        if(substring == null){
            log.error("Строка не передана");
            return null;
        }
        TreeMap<String, List<String>> airports = new TreeMap<>();
        String[] line;
        while ((line = reader.readNext()) != null) {
           if( line[column].toLowerCase(Locale.ROOT).startsWith(substring)){
               airports.put(line[column], List.of(line));
           }
        }
        reader.close();
        return airports.values();
    }
}

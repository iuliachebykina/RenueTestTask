package ru.renue.testTask.service;


import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CsvParserService {

    @Value("${app.column}")
    private Integer column;
    private final CSVReader reader;

    private final static Logger log = LoggerFactory.getLogger(CsvParserService.class);


    public CsvParserService(CSVReader reader) {
        this.reader = reader;
    }


    public List<String[]> getAirports(String substring, Integer column) {
        if(column != null)
            this.column = column;
        this.column--;
        if(this.column < 0 || this.column > 13){
            log.error("Такого столбца не существует");
            return null;
        }
        if(substring == null){
            log.error("Строка не передана");
            return null;
        }
        return getAirports(substring);
    }

    List<String[]> getAirports(String substring) {
        List<String[]> airports = new ArrayList<>();
        String[] line;
        try{
            while ((line = reader.readNext()) != null) {
                if( line[column].toLowerCase(Locale.ROOT).startsWith(substring)){
                    airports.add(line);
                }
            }
            reader.close();
        } catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
        airports.sort(Comparator.comparing(a -> a[column]));
        return airports;
    }
}

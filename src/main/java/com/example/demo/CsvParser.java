package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CsvParser {

    private Integer column;
    private final List<String[]> airports;
    private final static Logger log = LoggerFactory.getLogger(CsvParser.class);

    public CsvParser(CsvService csvService, ConfigProperty configProperty) {
        this.column = configProperty.getColumn()-1;
        this.airports = csvService.getAirports();
    }

    public void getData(String substring, Integer column){
        if(column != null)
            this.column = column;
        getData(substring);
    }

    void getData(String substring){
        if(column < 0 || column > 13){
            log.error("Такого столбца не существует");
            return;
        }
        List<String[]> res = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (String[] airport:
             airports) {
            var target = airport[column].toLowerCase(Locale.ROOT);
            if(target.startsWith(substring)){
                res.add(airport);
            }
        }

        res.sort(Comparator.comparing(t -> t[column]));
        int end = (int) (System.currentTimeMillis() - start);
        res.forEach(t-> System.out.println(String.join(", ", t)));
        System.out.println("Количество найденных строк: " + res.size());
        System.out.println("Время, затраченное на поиск: " + end +" мс.");
    }
}

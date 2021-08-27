package ru.renue.testTask.service;


import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CsvParserService {



    public List<String[]> getAirports(List<String[]> data, String substring, Integer column) {
        List<String[]> airports = new ArrayList<>();

        int index= data.size()/2+1;
        while (index < data.size() && index >= 0){
            if(data.get(index)[column].substring(0, substring.length()).compareTo(substring) < 0){
                index += index/2+1;
            } else if(data.get(index)[column].substring(0, substring.length()).compareTo(substring) > 0){
                index -= index/2+1;
            } else {
                break;
            }
        }
        if(index < 0 || index >= data.size()){
            return airports;
        }
        while (index-1 >= 0 && data.get(index-1)[column].startsWith(substring)){
            index--;
        }

        while (index < data.size() && data.get(index)[column].startsWith(substring)){
            airports.add(data.get(index));
            index++;
        }
        return airports;

    }
}

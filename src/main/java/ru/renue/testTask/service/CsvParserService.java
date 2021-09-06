package ru.renue.testTask.service;


import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CsvParserService {



    public List<String[]> getAirports(List<String[]> data, String substring, Integer column) {
        return data.stream().filter(a-> a[column].startsWith(substring)).collect(Collectors.toList());
    }
}

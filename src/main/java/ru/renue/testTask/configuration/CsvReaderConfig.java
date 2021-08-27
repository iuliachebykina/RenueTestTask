package ru.renue.testTask.configuration;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.List;
import java.util.Objects;

@Configuration
public class CsvReaderConfig {
    public CSVReader reader() throws IOException {
        InputStream in = getClass().getResourceAsStream("/airports.csv");
        InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(in));
        return new CSVReader(reader);
    }

    @Bean
    public List<String[]> getData() throws IOException, CsvException {
        return reader().readAll();

    }
}

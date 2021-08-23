package ru.renue.testTask;

import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Configuration
public class CsvReaderConfig {

    @Bean
    public CSVReader reader() throws IOException {
        InputStream in = getClass().getResourceAsStream("/airports.csv");
        InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(in));
        return new CSVReader(reader);
    }
}
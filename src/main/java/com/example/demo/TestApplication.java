package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {

	private final CsvParser csvParser;

	public TestApplication(CsvParser csvParser) {
		this.csvParser = csvParser;
	}

	public static void main(String[] args) {
		var app = new SpringApplication(TestApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Введите строку: ");
		var substring = new Scanner(System.in).next().toLowerCase(Locale.ROOT);
		Integer column = null;
		if(args.length > 0){
			column = Integer.valueOf(args[0]);
		}
		csvParser.getData(substring, column);
	}

}

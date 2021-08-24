package ru.renue.testTask;

import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.renue.testTask.service.CsvParserService;


import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class RenueTestApplication implements CommandLineRunner {

	private final CsvParserService csvParser;
	private final static Logger log = LoggerFactory.getLogger(RenueTestApplication.class);


	public RenueTestApplication(CsvParserService csvParser) {
		this.csvParser = csvParser;
	}

	public static void main(String[] args) {
		var app = new SpringApplication(RenueTestApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws CsvValidationException, IOException {
		Integer column = null;
		if (args.length > 0) {
			try {
				column = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				log.error("Неправильный формат номера столбца");
				return;
			}

		}
		System.out.print("Введите строку: ");
		var in = new Scanner(System.in);
		String substring = null;
		if(in.hasNext()){
			substring = in.next().toLowerCase(Locale.ROOT);
		}
		in.close();
		var start = System.currentTimeMillis();
		var airports = csvParser.getAirports(substring, column);
		var time =  System.currentTimeMillis() - start;
		if(airports == null) {
			log.error("Не удалось осуществить поиск");
			return;
		}
		airports.forEach(a-> System.out.println(String.join(", ", a.subList(1, a.size()))));
		System.out.println("Количество найденных строк: " + airports.size());
		System.out.println("Время, затраченное на поиск: " + time +" мс.");
	}

}

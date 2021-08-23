package ru.renue.testTask;

import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

@SpringBootApplication
public class RenueTestApplication implements CommandLineRunner {

	private final CsvParser csvParser;
	private final static Logger log = LoggerFactory.getLogger(RenueTestApplication.class);


	public RenueTestApplication(CsvParser csvParser) {
		this.csvParser = csvParser;
	}

	public static void main(String[] args) {
		var app = new SpringApplication(RenueTestApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws CsvValidationException, IOException {
		System.out.print("Введите строку: ");
		var in = new BufferedReader(new InputStreamReader(System.in));
		String substring;
		substring = in.readLine().toLowerCase(Locale.ROOT);
		Integer column = null;
		if (args.length > 0) {
			try {
				column = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				log.error("Неправильный формат номера столбца");
				return;
			}

		}
		long start = System.currentTimeMillis();
		var airports = csvParser.getAirports(substring, column);
		if(airports == null)
			return;
		int end = (int) (System.currentTimeMillis() - start);
		airports.forEach(a-> System.out.println(String.join(", ", a.subList(1, a.size()-1))));
		System.out.println("Количество найденных строк: " + airports.size());
		System.out.println("Время, затраченное на поиск: " + end +" мс.");
	}

}

package com.reporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reporting.service.ReportingService;

@SpringBootApplication
public class ReportingProjectApplication implements CommandLineRunner {
	/* CommandLineRunner interface is implemented for initial db operations for the CSV records */
	
	@Autowired
	private ReportingService reportingService;

	public static void main(String[] args) {
		SpringApplication.run(ReportingProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		reportingService.readRecords(); // read the records in CSV files and write into H2 DB
	}
}

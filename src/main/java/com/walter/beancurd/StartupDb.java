package com.walter.beancurd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@Component
public class StartupDb implements CommandLineRunner {
	@Autowired
	@Qualifier("db2DataSource")
	private DataSource dataSource;

	@Override
	public void run(String... args) throws Exception {
		log.info("database start.....");
		showConnection();
	}


	private void showConnection() throws Exception{
		log.info(dataSource.toString());
		Connection connection = dataSource.getConnection();
		log.info(dataSource.toString());
		connection.close();
	}
}

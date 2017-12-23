package com.kasakad.fileio.kasakaidfileio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
@EntityScan(basePackageClasses = {KasakaidfileioApplication.class, Jsr310JpaConverters.class})
public class KasakaidfileioApplication {

	public static void main(String[] args) {
		SpringApplication.run(KasakaidfileioApplication.class, args);
	}
}

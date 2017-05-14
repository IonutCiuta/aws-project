package com.aws.privatesector.PrivateSector;

import com.aws.privatesector.PrivateSector.entities.ElectionResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Field;
import java.util.Arrays;

@SpringBootApplication
public class PrivateSectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrivateSectorApplication.class, args);

		ElectionResult er = new ElectionResult();

	}
}

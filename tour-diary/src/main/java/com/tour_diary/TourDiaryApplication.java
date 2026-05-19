package com.tour_diary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TourDiaryApplication {

	public static void main(String[] args) {
		loadLocalEnv();
		SpringApplication.run(TourDiaryApplication.class, args);
	}

	private static void loadLocalEnv() {
		Path envPath = Path.of(".env");
		if (!Files.isRegularFile(envPath)) {
			return;
		}

		try {
			for (String line : Files.readAllLines(envPath)) {
				String trimmed = line.trim();
				if (trimmed.isEmpty() || trimmed.startsWith("#") || !trimmed.contains("=")) {
					continue;
				}

				int separator = trimmed.indexOf('=');
				String key = trimmed.substring(0, separator).trim();
				String value = trimmed.substring(separator + 1).trim();
				if (!key.isEmpty() && System.getProperty(key) == null && System.getenv(key) == null) {
					System.setProperty(key, stripWrappingQuotes(value));
				}
			}
		} catch (IOException ignored) {
			// The app can still run with normal environment variables.
		}
	}

	private static String stripWrappingQuotes(String value) {
		if (value.length() >= 2) {
			char first = value.charAt(0);
			char last = value.charAt(value.length() - 1);
			if ((first == '"' && last == '"') || (first == '\'' && last == '\'')) {
				return value.substring(1, value.length() - 1);
			}
		}
		return value;
	}
}

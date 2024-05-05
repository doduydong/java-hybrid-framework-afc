package utilities;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.github.javafaker.Faker;

public class DataFaker {
	private Faker faker;

	public DataFaker(String locale) {
		switch (locale.toLowerCase()) {
		case "us":
			faker = new Faker(Locale.US);
			break;
		case "uk":
			faker = new Faker(Locale.UK);
			break;
		case "japan":
			faker = new Faker(Locale.JAPAN);
			break;
		case "korea":
			faker = new Faker(Locale.KOREA);
			break;
		case "china":
			faker = new Faker(Locale.CHINA);
			break;
		default:
			throw new RuntimeException("'" + locale + "' locale is invalid.");
		}
	}

	public String generateFirstName() {
		return faker.address().firstName();
	}

	public String generateLastName() {
		return faker.address().lastName();
	}

	public String generateEmailAddress() {
		return faker.internet().emailAddress();
	}

	public String generatePassword() {
		return faker.internet().password();
	}

	public String generateCompany() {
		return faker.company().name();
	}

	private String dateOfBirth;

	private void generateDateOfBirth() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MMMM/yyyy");
		dateOfBirth = dateFormat.format(faker.date().birthday());
	}

	public String getDobDay() {
		if (dateOfBirth == null) {
			generateDateOfBirth();
		}
		String[] dmy = dateOfBirth.split("/");
		if (dmy[0].startsWith("0")) {
			return dmy[0].substring(1);
		} else {
			return dmy[0];
		}
	}

	public String getDobMonth() {
		if (dateOfBirth == null) {
			generateDateOfBirth();
		}
		String[] dmy = dateOfBirth.split("/");
		return dmy[1];
	}

	public String getDobYear() {
		if (dateOfBirth == null) {
			generateDateOfBirth();
		}
		String[] dmy = dateOfBirth.split("/");
		return dmy[2];
	}

}

package afc.nopcommerce.data;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class DataJSONManager {

	public static DataJSONManager getDataJSON() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.NOPCOMMERCE_DATA_JSON), DataJSONManager.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static class UserInfo {
		@JsonProperty("firstName")
		private String firstName;

		@JsonProperty("lastName")
		private String lastName;

		@JsonProperty("emailUserName")
		private String emailUserName;

		@JsonProperty("emailDomain")
		private String emailDomain;

		@JsonProperty("password")
		private String password;

		@JsonProperty("company")
		private String company;

		@JsonProperty("dobDate")
		private String dobDay;

		@JsonProperty("dobMonth")
		private String dobMonth;

		@JsonProperty("dobYear")
		private String dobYear;
	}

	@JsonProperty("UserInfo")
	private UserInfo userInfo;

	public String getFirstName() {
		return userInfo.firstName;
	}

	public String getLastName() {
		return userInfo.lastName;
	}

	public String getEmailUserName() {
		return userInfo.emailUserName;
	}

	public String getEmailDomain() {
		return userInfo.emailDomain;
	}

	public String getPassword() {
		return userInfo.password;
	}

	public String getCompany() {
		return userInfo.company;
	}

	public String getDobDay() {
		return userInfo.dobDay;
	}

	public String getDobMonth() {
		return userInfo.dobMonth;
	}

	public String getDobYear() {
		return userInfo.dobYear;
	}

}

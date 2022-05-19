package com.ehizman.Java_ORC;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class JavaOrcApplication {

	private final static String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
	private final static String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

	public static void main(String[] args) {
		SpringApplication.run(JavaOrcApplication.class, args);
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(
						new PhoneNumber("whatsapp:+2348028887080"),
						new PhoneNumber("whatsapp:+14155238886"),
						"Hello there!\nWelcome to Likee!! by Ehizman\nPlease send in your location, then send a picture of the item that you want to track")
				.create();

	}
}

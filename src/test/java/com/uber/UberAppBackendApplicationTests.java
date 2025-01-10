package com.uber;

import com.uber.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UberAppBackendApplicationTests {

	@Autowired
	private EmailSenderService emailSenderService;

	@Test
	void contextLoads() {
	}

	// Testing mail service
	@Test
	void testingEmailService(){
		emailSenderService.sendEmail("rushikarle45@gmail.com","This is Reminder for come tomarow early","Hi Rushikesk how are you....");
	}

	@Test
	void testingMultipleEmails(){
		String[] emails = {
				"harshalwadne1978@gmail.com",
				"rushikarle45@gmail.com",
		};
		emailSenderService.sendEmail(emails,"Hello Harshal Wande","hello harshal I am sending email from spring-boot application please read this email and call back me please");
	}

}

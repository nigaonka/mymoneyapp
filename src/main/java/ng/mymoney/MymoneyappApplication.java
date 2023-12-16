package ng.mymoney;

import ng.mymoney.controller.KafkaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MymoneyappApplication {

	private static final Logger logger = LoggerFactory.getLogger(MymoneyappApplication.class);
	public static void main(String[] args) {
		logger.info("Initializing application ");
		SpringApplication.run(MymoneyappApplication.class, args);
	}

}

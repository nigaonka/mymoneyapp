package ng.mymoney;

import com.google.common.base.Strings;
import com.netflix.config.sources.URLConfigurationSource;
import lombok.extern.slf4j.Slf4j;
import ng.mymoney.controller.KafkaController;
import ng.mymoney.util.DynConfigCommonUtils;
import ng.mymoney.util.DynamicVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MymoneyappApplication {

	private static final String PROPERTIES_FILE = "PROPERTIES_FILE";
	public static void main(String[] args) {
	try {
		System.out.println(".....Initializing application ......");
		log.info("Initializing application ");

		if (!Strings.isNullOrEmpty(System.getenv(PROPERTIES_FILE))) {
			System.setProperty(URLConfigurationSource.CONFIG_URL, String.format("file://%s",
					System.getenv(PROPERTIES_FILE)));
		}
		System.out.println("Property file loaded :  " + DynConfigCommonUtils.getGroupId());
		log.info("Properties file loaded ");
		SpringApplication.run(MymoneyappApplication.class, args);
		log.info(" ======= Application initialized ====== ");
		System.out.println(" ======= Application initialized ====== ");

	}catch (Exception exception)
	{
		exception.printStackTrace();
		log.error(exception.getMessage());
	}
	}

}

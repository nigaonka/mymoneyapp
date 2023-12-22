package ng.mymoney.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("application.properties")
public class ApplicationConfiguration {

    @Autowired
    private Environment env;

    public String getProperty() {
        System.out.println("ApplicationConfiguration >> getProperty");

        return env.getProperty("logging.file");
    }

}
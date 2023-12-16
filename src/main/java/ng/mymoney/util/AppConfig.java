package ng.mymoney.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class AppConfig {

    private String TOPIC_NAME;
    private String KAFKA_BROKER;

    public String getTOPIC_NAME() {
        return TOPIC_NAME;
    }

    public void setTOPIC_NAME(String TOPIC_NAME) {
        this.TOPIC_NAME = TOPIC_NAME;
    }

    private String KAFKA_GROUP_ID;



}

package ng.mymoney.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public enum DynamicVariables {


    KAFKA_ENDPOINT("kafka_broker"),
    TOPIC_NAME("topic_name"),

    KAFKA_GROUPID("group_id"),

    KSTREAM_APP_ID("kstream_app_id"),

    CONSUMER_GRP_ID("consumer_grp_id");


    private String value;

    DynamicVariables(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}



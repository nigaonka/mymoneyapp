package ng.mymoney.util;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynConfigCommonUtils {

    public static void initializeDynConfig() {
        DynamicStringProperty kafkaEndpointProp = DynamicPropertyFactory.getInstance()
                .getStringProperty(AppConstants.KAFKA_ENDPOINT.getValue(), "localhost:90");

        DynamicStringProperty topicName = DynamicPropertyFactory.getInstance().getStringProperty(AppConstants.TOPIC_NAME.getValue(), "usertopic");

        DynamicStringProperty groupId = DynamicPropertyFactory.getInstance().getStringProperty(AppConstants.KAFKA_GROUPID.getValue(), "kafka_sandbox");
    }

    public static String getKafkaEndpoint(){
        return DynamicPropertyFactory.getInstance().getStringProperty(AppConstants.KAFKA_ENDPOINT.getValue(), "localhost:9092").getValue();
    }

    public static String getTopicName(){
        return DynamicPropertyFactory.getInstance().getStringProperty(AppConstants.TOPIC_NAME.getValue(), "usertopic").getValue();
    }

    public static String getGroupId(){
        return DynamicPropertyFactory.getInstance().getStringProperty(AppConstants.KAFKA_GROUPID.getValue(), "kafka_sandbox").getValue();
    }
}



package ng.mymoney.util;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DynConfigCommonUtils {

    public static void initializeDynConfig() {
        DynamicStringProperty kafkaEndpointProp = DynamicPropertyFactory.getInstance()
                .getStringProperty(DynamicVariables.KAFKA_ENDPOINT.getValue(), "localhost:9092");

        DynamicStringProperty topicName = DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.TOPIC_NAME.getValue(), "usertopic");

        DynamicStringProperty groupId = DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.KAFKA_GROUPID.getValue(), "kafka_sandbox");
        DynamicStringProperty consumer_grp = DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.CONSUMER_GRP_ID.getValue(), "moneyapp_group");
        DynamicStringProperty kstream_app_id = DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.KSTREAM_APP_ID.getValue(), "moneyapp_kstream_id");


    }

    public static String getKafkaEndpoint() {
        return DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.KAFKA_ENDPOINT.getValue(), "localhost:9092").getValue();
    }

    public static String getTopicName() {
        return DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.TOPIC_NAME.getValue(), "usertopic").getValue();
    }

    public static String getGroupId() {
        return DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.KAFKA_GROUPID.getValue(), "kafka_sandbox").getValue();
    }

    public static String getConsumerGroupId() {
        return DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.CONSUMER_GRP_ID.getValue(), "moneyapp_group").getValue();
    }

    public static String getStreamAppId() {
        return DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.KSTREAM_APP_ID.getValue(), "moneyapp_kstream_id").getValue();
    }

}



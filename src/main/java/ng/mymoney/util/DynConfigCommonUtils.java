package ng.mymoney.util;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DynConfigCommonUtils {

    public static void initializeDynConfig() {

        DynamicStringProperty kafkaEndpointProp = DynamicPropertyFactory.getInstance()
                .getStringProperty(DynamicVariables.KAFKA_ENDPOINT.getValue(), "kafka:29092");
        DynamicStringProperty accTopicName = DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.ACC_TOPIC_NAME.getValue(), "accounttopic");
        DynamicStringProperty txnTopicName = DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.TXN_TOPIC_NAME.getValue(), "txntopic");

        DynamicStringProperty groupId = DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.KAFKA_GROUPID.getValue(), "kafka_sandbox");
        DynamicStringProperty consumer_grp = DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.CONSUMER_GRP_ID.getValue(), "moneyapp_group");
        DynamicStringProperty kstream_app_id = DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.KSTREAM_APP_ID.getValue(), "moneyapp_kstream_id");

    }

    public static String getKafkaEndpoint() {
        return DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.KAFKA_ENDPOINT.getValue(), "kafka:29092").getValue();
    }

    public static String getAccTopicName() {
        return DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.ACC_TOPIC_NAME.getValue(), "accounttopic").getValue();
    }

    public static String getTxnTopicName() {
        return DynamicPropertyFactory.getInstance().getStringProperty(DynamicVariables.TXN_TOPIC_NAME.getValue(), "txntopic").getValue();
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



package ng.mymoney.kafka.process;

import lombok.extern.slf4j.Slf4j;
import ng.mymoney.util.DynConfigCommonUtils;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.CooperativeStickyAssignor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.processor.ProcessorSupplier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

@Configuration
@Slf4j
public class KafkaStreamConfiguration {

    public static final String DEFAULT_OFFSET_EARLIEST = "earliest";
    private static final String REPLICATION_FACTOR = "ReplicationFactor";
    private static final String DEFAULT_REPLICATION_FACTOR = "1";

    @Bean
    public KafkaAdmin kafkaAdmin() {

        Map<String, Object> configs = new HashMap<>();
        String brokerEnv = System.getenv("SPRING_KAFKA_BOOTSTRAPSERVERS");
        //configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,  brokerEnv);
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, DynConfigCommonUtils.getKafkaEndpoint());
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic inputKafkaTopic() {
        return new NewTopic("usertopic", 1, (short) 1);
    }

    @Bean
    @Qualifier("streamsConfig")
    public Properties streamsConfig(Environment env) {

        try {
            //  String brokerEnv = System.getenv("SPRING_KAFKA_BOOTSTRAPSERVERS");
            Properties props = new Properties();
            props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, DynConfigCommonUtils.getKafkaEndpoint());
            props.put(StreamsConfig.APPLICATION_ID_CONFIG, DynConfigCommonUtils.getStreamAppId());
            props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 1);
            props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
            props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass().getName());
            props.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG,
                    LogAndContinueExceptionHandler.class.getName());
            props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG,
                    env.getProperty(REPLICATION_FACTOR, DEFAULT_REPLICATION_FACTOR));

            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, DEFAULT_OFFSET_EARLIEST);
            props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 1000);
            props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 600000);
            props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 300000);
            props.put(ProducerConfig.RETRIES_CONFIG, 1);
            props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 5);
            props.put(ConsumerConfig.GROUP_ID_CONFIG, DynConfigCommonUtils.getConsumerGroupId());

            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
            //Increasing to 20 MB for each record in error state store

            props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

            //read kafka stream properties from dynamicProperties
            props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
            props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, CooperativeStickyAssignor.class.getName());
            props.put(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, ("SchedulerCoordinator-" + UUID.randomUUID()));
            log.info("KafkaStream Pros are set {} ", props.toString());
            return props;
        }catch (Exception exception)
        {
            log.error(exception.getMessage());
            return null;
        }

    }


    @Bean(destroyMethod = "stop")
    @DependsOn({"inputKafkaTopic"})
    public AccountStreamProcessor kafkaStreamProcessor(
            @Qualifier("streamsConfig") Properties streamsProperties,
            @Qualifier("processorSupplier") ProcessorSupplier<String, byte[]> processorSupplier) {
        var streamProcessor = new AccountStreamProcessor(streamsProperties, processorSupplier);
        System.out.println("Starting stream processor ");
        streamProcessor.initializeProcessor();
        return streamProcessor;

    }

    @Bean
    public ProcessorSupplier<String, byte[]> processorSupplier() {
        return () -> new TxnStreamProcessor();
    }

}

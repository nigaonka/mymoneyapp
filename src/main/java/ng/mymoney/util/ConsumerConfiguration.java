package ng.mymoney.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.hibernate.cache.CacheException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class ConsumerConfiguration {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigurations());
    }
    @Bean
    public Map<String, Object> consumerConfigurations() {
        try {
            Map<String, Object> configurations = new HashMap<>();

            log.info("Setting kafka properties: Topic name: {}, Endpoint: {}", DynConfigCommonUtils.getAccTopicName(), DynConfigCommonUtils.getKafkaEndpoint());

            configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, DynConfigCommonUtils.getKafkaEndpoint());
            configurations.put(ConsumerConfig.GROUP_ID_CONFIG, DynConfigCommonUtils.getGroupId());
            configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            return configurations;
        }catch (Exception exception){
            exception.printStackTrace();
            log.error(exception.getMessage());
            return null;
        }

    }
    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}

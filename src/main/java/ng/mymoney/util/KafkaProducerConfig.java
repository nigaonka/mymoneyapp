package ng.mymoney.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@Slf4j
public class KafkaProducerConfig implements ApplicationListener<ContextRefreshedEvent> {
    private ApplicationContext applicationContext;


    public KafkaProducerConfig (ApplicationContext applicationContext){
        this.applicationContext=applicationContext;
    }
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigurations());
    }
    @Bean
    public Map<String, Object> producerConfigurations() {
        try {
            Map<String, Object> configurations = new HashMap<>();
            configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker:29092");

            configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            return configurations;
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }

    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate(producerFactory());
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Creating producer topic");
        createTopicProducer();
    }

    public void destroyTopicProducer(){
        log.info("Destroying producer");
        var registry = (DefaultSingletonBeanRegistry) applicationContext.getAutowireCapableBeanFactory();
        registry.destroySingleton("KafkaProducer");

    }
    public void createTopicProducer(){
        destroyTopicProducer();
        log.info("Creating producer to send log message...");
        var producer = createProducer();
        var registry = (DefaultSingletonBeanRegistry) applicationContext.getAutowireCapableBeanFactory();
        registry.registerSingleton("KafkaProducer", producer);

    }

    @Bean
    @Qualifier("KafkaProducer")
    public KafkaProducer<String, byte[]> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 60000);  // set to smaller/quicker value for tests
        props.put(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "1");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, DynConfigCommonUtils.getKafkaEndpoint());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.ByteArraySerializer");
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        return new KafkaProducer<>(props);
    }

}


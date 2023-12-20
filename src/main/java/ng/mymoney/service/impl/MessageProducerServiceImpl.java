package ng.mymoney.service.impl;

import lombok.extern.slf4j.Slf4j;
import ng.mymoney.model.AccountTxn;
import ng.mymoney.service.MessagingService;
import ng.mymoney.util.DynConfigCommonUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageProducerServiceImpl implements MessagingService {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducerServiceImpl.class);
    private KafkaProducer producer;
    private ApplicationContext context;

    @Autowired
    public void setProducer(KafkaProducer<String, byte[]> producer) {
        this.producer = producer;
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    /*
    Processing the message sent by controller
     */
    @Override
    public void publishMessageToKafka(String key, AccountTxn accountTxn) {
        if (null != key) {
            this.producer = context.getBean("KafkaProducer", KafkaProducer.class);
            var producerRecord = new ProducerRecord<>(DynConfigCommonUtils.getTopicName(), key, accountTxn.toJson().getBytes());

            try {
                log.info("Pushing message to topic {}", DynConfigCommonUtils.getTopicName());
                producer.send(producerRecord);
            } catch (Exception e) {
                log.error("Exception in sending message to kafka " + e.getMessage());
            }
        }

    }


}
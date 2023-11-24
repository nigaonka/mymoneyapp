package ng.mymoney.service.impl;

import lombok.extern.slf4j.Slf4j;
import ng.mymoney.service.MessagingService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class MessagingServiceImpl implements MessagingService {

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


    @Override
    public void pushMessageToKafka(String txnId, String message) {

        if (null != message) {
            this.producer = context.getBean("KafkaProducer", KafkaProducer.class);
            var producerRecord = new ProducerRecord<>("mytopic", txnId, message.getBytes());

            try {
            producer.send(producerRecord);
            }catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("Got exception in sending message to kafka");
            }
        }

    }


}

package ng.mymoney.controller;

import lombok.extern.slf4j.Slf4j;
import ng.mymoney.service.MessagingService;
import ng.mymoney.service.MyTopicConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.*;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {
    private KafkaTemplate<String, String> template;
    private MyTopicConsumer myTopicConsumer;

    private MessagingService messagingService;

    @Autowired
    public void setMessagingService(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @Autowired
    public void setTemplate(KafkaTemplate kafkaTemplate){
        this.template = kafkaTemplate;
    }

    @Autowired
    public void setMyTopicConsumer(MyTopicConsumer myTopicConsumer){
        this.myTopicConsumer = myTopicConsumer;

    }

    @GetMapping("/produce")
    public String produce(@RequestParam String message) {

        try {
            System.out.println("============  Producing message ==========");
            template.send("mytopic", message);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return "Message sent successfully ";

    }

    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam String message) {

        System.out.println("Sending message to kafka  ");

        String bootstrapServers = "localhost:2181";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create a producer record
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("mytopic", "hello world");

        try {
            // send data - asynchronous
            producer.send(producerRecord);

            // flush data - synchronous
            producer.flush();

            // flush and close producer
            producer.close();
        }catch (Exception exception)
        {
            System.out.println("Got Exception in sending message ... " + exception.getMessage());
            exception.printStackTrace();
        }
        return "Message set successfully ";

    }

    @GetMapping("/getMessage")
    public List<String> getMessages() {
        System.out.println(" ====== Consuming the message ======= ");
        return myTopicConsumer.getMessages();
    }

    @GetMapping ("/pushMessage")
    public String pushMessage(@RequestParam String message){
        log.info("Cntroller: pushMessage " + message);
        System.out.println("Cntroller: pushMessage " + message);
        String uniqueID = UUID.randomUUID().toString();
        try{
            messagingService.pushMessageToKafka(uniqueID,message);
        }catch (Exception e)
        {
            e.printStackTrace();
            return "Error in sending message to kafka";
        }
        return "Message sent to kafka";
    }


}

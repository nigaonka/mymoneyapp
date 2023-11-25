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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {
    private KafkaTemplate<String, String> template;
    private MyTopicConsumer myTopicConsumer;

    private MessagingService messagingService;

    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

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

    /*
    Sending kafka message through KafkaTemplate class
     */
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

    @GetMapping("/getMessage")
    public List<String> getMessages() {
        System.out.println(" ====== Consuming the message ======= ");
        return myTopicConsumer.getMessages();
    }

    @GetMapping ("/pushMessage")
    public String pushMessage(@RequestParam String message){

        log.info("Info: Cntroller: pushMessage " + message);
        log.debug(" Debug: Cntroller: pushMessage " + message);
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

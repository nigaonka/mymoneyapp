package ng.mymoney.controller;

import lombok.extern.slf4j.Slf4j;
import ng.mymoney.model.AccountTxn;
import ng.mymoney.service.MessagingService;
import ng.mymoney.service.MyTopicConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
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


    @GetMapping("/getMessage")
    public List<String> getMessages() {
        log.info(" ====== Consuming the message ======= ");
        return myTopicConsumer.getMessages();
    }

    @PostMapping ("/publishMessage")
    public String publishMessage(@RequestBody AccountTxn accountTxn){

        log.info("Controller: publishMessage " );
        String uniqueID = UUID.randomUUID().toString();
        log.info("Payload Txn Id {},  Account Id {}, Txn Type {}, Amount {} ", accountTxn.getTxnId(), accountTxn.getAccountId(), accountTxn.getTxnType(), accountTxn.getAmount());

        try{
            if (accountTxn.getTxnId() > 0 && accountTxn.getAccountId() > 0 && null != accountTxn.getTxnType() && accountTxn.getAmount() > 0) {
                log.info("Publishing message");
                messagingService.publishMessageToKafka(accountTxn.getTxnId()+"", accountTxn);
            }
        }catch (Exception e)
        {
            log.error(e.getMessage());
            return "Error in sending message to kafka";
        }
        return "Message sent to kafka";
    }
}

package ng.mymoney.controller;

import lombok.extern.slf4j.Slf4j;
import ng.mymoney.model.AccountTxn;
import ng.mymoney.service.MessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {
    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
    private KafkaTemplate<String, String> template;
    private MessagingService messagingService;

    @Autowired
    public void setMessagingService(MessagingService messagingService) {
        this.messagingService = messagingService;
    }

    @Autowired
    public void setTemplate(KafkaTemplate kafkaTemplate) {
        this.template = kafkaTemplate;
    }

    /*
    This method is invoked to publish messages to kafka topic
     */
    @PostMapping("/publishMessage")
    public String publishMessage(@RequestBody AccountTxn accountTxn) {

        String uniqueID = UUID.randomUUID().toString();
        try {
            if (accountTxn.getTxnId() > 0 && accountTxn.getAccountNumber() > 0 && null != accountTxn.getTxnType() && accountTxn.getAmount() > 0) {
                System.out.println("Publishing message ");
                log.info("Publishing message to Service layer");
                log.info("Txn Id {},  Account Id {}, Txn Type {}, Amount {} ", accountTxn.getTxnId(), accountTxn.getAccountNumber(), accountTxn.getTxnType(), accountTxn.getAmount());
                //messagingService.publishMessageToKafka(uniqueID + "", accountTxn);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Error in sending message to kafka";
        }
        return "Message sent to kafka";
    }
}

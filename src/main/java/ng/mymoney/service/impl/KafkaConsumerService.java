package ng.mymoney.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/*
This class implements simple kafka consumer operations.
 */

@Slf4j
@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);


    @KafkaListener(groupId = "myapp_group", topics = "usertopic")
    public void consumeMessage(String message) {
        consume(message);
    }


    public void consume(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            log.info("Message received {} ", message);

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }


}


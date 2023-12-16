package ng.mymoney.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ng.mymoney.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);


    @KafkaListener(groupId = "myapp_group", topics = "usertopic")
    public void consumeMessage(String in) {
       log.info("Reading latest message ");
        consume(in);
    }


    public void consume(String in) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.debug("Message : " + in);

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }


}

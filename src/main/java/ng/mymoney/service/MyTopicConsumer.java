package ng.mymoney.service;

import ng.mymoney.MymoneyappApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MyTopicConsumer {
    private final List<String> messages = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(MyTopicConsumer.class);

    @KafkaListener(topics = "usertopic", groupId = "myapp_group")
    public void listen(String message) {
        synchronized (messages) {
            log.info(message);
        }
    }
    public List<String> getMessages() {
        return messages;
    }


}

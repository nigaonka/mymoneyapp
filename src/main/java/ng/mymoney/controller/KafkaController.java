package ng.mymoney.controller;

import ng.mymoney.service.MyTopicConsumer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

import java.util.List;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private KafkaTemplate<String, String> template;
    private MyTopicConsumer myTopicConsumer;

    public KafkaController(KafkaTemplate<String, String> template, MyTopicConsumer myTopicConsumer) {

        this.template = template;
        this.myTopicConsumer = myTopicConsumer;
    }

    @GetMapping("/produce")
    public String produce(@RequestParam String message) {

        try {
            System.out.println("============  Producing message ==========");

             template.send("quickstart-events", message);


        }catch (Exception e)
        {

            e.printStackTrace();
        }
        return "Message sent successfully ";

    }
    @GetMapping("/sendMessage")
    public String sendMessage(@RequestParam String message){

        System.out.println("Sending message to kafka  ");

        String bootstrapServers = "127.0.0.1:9092";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create a producer record
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("quickstart-events", "hello world");

        // send data - asynchronous
        producer.send(producerRecord);

        // flush data - synchronous
        producer.flush();

        // flush and close producer
        producer.close();

return "Message set successfully ";

    }
    @GetMapping("/getMessage")
    public List<String> getMessages() {
        System.out.println(" ====== Consuming the message ======= ");
        return myTopicConsumer.getMessages();
    }

}

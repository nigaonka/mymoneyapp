package ng.mymoney.kafka.process;


import static ng.mymoney.util.Constants.INPUT_TOPIC_SOURCE;
import static ng.mymoney.util.Constants.NODE_PROCESSOR;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import ng.mymoney.util.DynConfigCommonUtils;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.ProcessorSupplier;

import java.util.Properties;


@Slf4j
public class AccountStreamProcessor {

    private Properties streamingProperties;

    private String inputTopic;
    private String outputTopic;

    private KafkaStreams streams;


    private ProcessorSupplier<String, byte[]> processorSupplier;

    public AccountStreamProcessor(Properties streamingProperties,
                                  ProcessorSupplier<String, byte[]> processorSupplier) {
        this.streamingProperties = streamingProperties;
        this.inputTopic = DynConfigCommonUtils.getAccTopicName();
        this.processorSupplier = processorSupplier;
    }


    public void initializeProcessor() {

        try {
            Topology topology = new Topology();

            topology.addSource(INPUT_TOPIC_SOURCE, inputTopic)
                    .addProcessor(NODE_PROCESSOR, processorSupplier, INPUT_TOPIC_SOURCE);
            streams = new KafkaStreams(topology, streamingProperties);
            handleClosingException();
            streams.start();
            log.info("AccountStreamProcessor started...");
        }catch (Exception exception){
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
    }

    private void handleClosingException() {
        streams.setUncaughtExceptionHandler((Thread t, Throwable e) -> {
            log.error(String.format("Thread %s has encountered an error: ", t.getName()), e);
        });
    }

    public KafkaStreams.State getStreamState() {
        return streams.state();
    }

    @PreDestroy
    public void stop() {
        if (streams != null) {
            try {
                streams.close();
                streams.cleanUp();
                log.info("Closing the streams ");
            } catch (Exception e) {
                log.error("Failed to close Kafka Stream Thread :: {}", e);
            }

        }
    }


}

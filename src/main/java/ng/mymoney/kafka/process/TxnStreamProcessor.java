package ng.mymoney.kafka.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ng.mymoney.model.AccountTxn;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.Punctuator;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TxnStreamProcessor implements Processor<String, byte[]>, Punctuator {

    private ProcessorContext context = null;
    private ObjectMapper objectMapper;


    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public void init(ProcessorContext context) {
        log.info(" Init Setting the context  ");
        this.context = context;
    }

    @Override
    public void process(String key, byte[] value) {
        try {

            String strVal = new String(value);
            log.info("Message consumed by kstream: key {}, Value {}", key, strVal);
            objectMapper = new ObjectMapper();
            var messageDto = objectMapper.readValue(value, AccountTxn.class);
            log.info("Message consumed and processed: {}, {}, {},  {}", messageDto.getTxnId(), messageDto.getAccountId(), messageDto.getAmount(), messageDto.getTxnType());
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }


    }

    @Override
    public void close() {

    }

    @Override
    public void punctuate(long timestamp) {

    }
}

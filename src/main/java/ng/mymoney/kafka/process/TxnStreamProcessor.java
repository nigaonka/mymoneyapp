package ng.mymoney.kafka.process;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.Punctuator;

@Slf4j
public class TxnStreamProcessor implements Processor<String, byte[]>, Punctuator {

  private  ProcessorContext context = null;

    @Override
    public void init(ProcessorContext context) {
        log.info(" Init Setting the context  ");
        this.context=context;
    }

    @Override
    public void process(String key, byte[] value) {
      try {

        log.info("Message consumed by kstream: key {}, Value {}", key, value.toString());


      }catch (Exception exception)
      {
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

package ng.mymoney.service.impl;

import lombok.extern.slf4j.Slf4j;
import ng.mymoney.model.AccountTxn;
import ng.mymoney.model.BankDetails;
import ng.mymoney.model.Customer;
import ng.mymoney.model.CustomerAccounts;
import ng.mymoney.service.MessagingService;
import ng.mymoney.util.ApplicationConfiguration;
import ng.mymoney.util.DynConfigCommonUtils;
import ng.mymoney.util.DynamicVariables;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class MessageProducerServiceImpl implements MessagingService {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducerServiceImpl.class);
    private KafkaProducer producer;
    private ApplicationContext context;

    private ApplicationConfiguration configuration;

    @Autowired
    public void setProducer(KafkaProducer<String, byte[]> producer) {
        this.producer = producer;
    }

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setAppConfiguration(ApplicationConfiguration configuration) {
        this.configuration = configuration;
    }


    /*
    Processing the message sent by controller
     */
    @Override
    public void publishMessageToKafka(Object object) {
        if (null != object) {
            try {
                String topicName = DynConfigCommonUtils.getAccTopicName();
                this.producer = context.getBean("KafkaProducer", KafkaProducer.class);
                if (object.getClass().equals(AccountTxn.class))
                    topicName = DynConfigCommonUtils.getTxnTopicName();

                var producerRecord = new ProducerRecord<>(topicName, getMessageKey(object), object.toString().getBytes());


                log.info("Pushing message to {}:{} " + DynConfigCommonUtils.getKafkaEndpoint(), topicName);
                producer.send(producerRecord);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Exception in sending message to kafka " + e.getMessage());
            }
        }

    }


    private String getMessageKey(Object object) {

        if (object.getClass().equals(Customer.class)) {
            Customer customer = (Customer) object;
            return "cust_" + customer.getCustomerId();
        } else if (object.getClass().equals(BankDetails.class)) {
            BankDetails bankDetails = (BankDetails) object;
            return "bank_" + bankDetails.getBankId();
        } else if (object.getClass().equals(AccountTxn.class)) {
            AccountTxn accountTxn = (AccountTxn) object;
            return "txn_" + accountTxn.getAccountNumber();

        } else if (object.getClass().equals(CustomerAccounts.class)) {
            CustomerAccounts customerAccounts = (CustomerAccounts) object;
            return "acc_" + customerAccounts.getCustomerId();

        } else {
            return UUID.randomUUID().toString();
        }

    }

}

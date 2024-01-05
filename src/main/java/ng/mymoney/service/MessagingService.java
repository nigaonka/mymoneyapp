package ng.mymoney.service;

import ng.mymoney.model.AccountTxn;

public interface MessagingService {

    public void publishMessageToKafka(Object object);

}

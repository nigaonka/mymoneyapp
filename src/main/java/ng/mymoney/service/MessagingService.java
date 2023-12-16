package ng.mymoney.service;

import ng.mymoney.model.AccountTxn;

public interface MessagingService {

    public void publishMessageToKafka(String key, AccountTxn accountTxn);

}

package ng.mymoney.service;

public interface MessagingService {

    public void pushMessageToKafka(String txnId, String message );
}

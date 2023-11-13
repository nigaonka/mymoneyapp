package ng.mymoney.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;


public class CustomerTxn {

    int txnId;
    int userId;
    int accountId;
    String txnType;
    long amount;

    public CustomerTxn(int txnId, int userId, int accountId, String txnType, long amount) {
        this.txnId = txnId;
        this.userId = userId;
        this.accountId = accountId;
        this.txnType = txnType;
        this.amount = amount;
    }

    private CustomerTxn(){

    }

    public int getTxnId() {
        return txnId;
    }

    public void setTxnId(int txnId) {
        this.txnId = txnId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}

package ng.mymoney.model;


import jakarta.persistence.*;
import org.json.JSONObject;


@Entity
@Table(name = "account_txn")
public class AccountTxn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int txnId;
    int accountId;
    String txnType;
    double amount;

    private AccountTxn(){

    }

    public AccountTxn(int txnId, int userId, int accountId, String txnType, long amount) {
        this.txnId = txnId;
        this.accountId = accountId;
        this.txnType = txnType;
        this.amount = amount;
    }

    public int getTxnId() {
        return txnId;
    }

    public void setTxnId(int txnId) {
        this.txnId = txnId;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toJson() {
        JSONObject obj = new JSONObject();
        obj.put("txnId", txnId);
        obj.put("accountId", accountId);
        obj.put("txnType" , txnType);
        obj.put("amount", amount);
        return obj.toString();
    }
}

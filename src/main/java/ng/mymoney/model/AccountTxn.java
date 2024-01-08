package ng.mymoney.model;


import jakarta.persistence.*;
import org.json.JSONObject;


@Entity
@Table(name = "account_txn")
public class AccountTxn {

    @Column(name = "txn_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int txnId;

    @Column(name = "accountNumber")
    int accountNumber;
    @Column (name = "txnType")
    String txnType;

    @Column(name = "amount")
    double amount;

    private AccountTxn(){

    }

    public AccountTxn(int txnId, int userId, int accountNumber, String txnType, long amount) {
        this.txnId = txnId;

        this.txnType = txnType;
        this.amount = amount;
    }

    public int getTxnId() {
        return txnId;
    }

    public void setTxnId(int txnId) {
        this.txnId = txnId;
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


    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String toJson() {
        JSONObject obj = new JSONObject();
        obj.put("txnId", txnId);
        obj.put("accountId", accountNumber);
        obj.put("txnType" , txnType);
        obj.put("amount", amount);
        return obj.toString();
    }
}

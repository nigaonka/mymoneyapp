package ng.mymoney.model;


import jakarta.persistence.*;

@Entity
@Table (name = "customer_account")
public class CustomerAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    int id;
    int accountNumber;
    int bankId;
    int customerId;
    String accountType;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    private CustomerAccounts() {

    }
    public int getId() {
        return id;
    }

    public CustomerAccounts(int id, int accountNumber, int bankId,int customerId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.bankId = bankId;
        this.customerId=customerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

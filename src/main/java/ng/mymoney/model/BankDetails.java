package ng.mymoney.model;

import jakarta.persistence.*;

@Entity
@Table (name = "bankdetails")
public class BankDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
   private int bankId;
    private String bankName;
    private String bankLocation;

    /**
     * Default private constructor
     */
    private BankDetails(){

    }
    public BankDetails(int bankId, String bankName, String bankLocation) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankLocation = bankLocation;
    }



    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLocation() {
        return bankLocation;
    }

    public void setBankLocation(String bankLocation) {
        this.bankLocation = bankLocation;
    }
}

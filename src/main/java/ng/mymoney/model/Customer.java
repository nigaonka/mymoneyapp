package ng.mymoney.model;

import jakarta.persistence.*;

@Entity
@Table (name = "customer_info")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="customerId")
    private int customerId;

    @Column(name="customerFName")
    private String customerFirstName;

    @Column(name = "customerLName")
    private String customerLastName;

    /**
     * Default customer
     */
    private Customer(){

    }

    public Customer(int customerId, String customerFirstName, String customerLastName) {

        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }



    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

}

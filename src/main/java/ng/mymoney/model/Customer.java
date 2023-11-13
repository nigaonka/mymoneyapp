package ng.mymoney.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "customer_info")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int customerId;
    private String customerFirstName;
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

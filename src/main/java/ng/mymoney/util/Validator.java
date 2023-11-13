package ng.mymoney.util;

import ng.mymoney.model.BankDetails;
import ng.mymoney.model.Customer;

import java.util.List;

public class Validator {

    public boolean isCustomerExists(List<Customer> customerList, Customer customer){

        for (int i = 0; i < customerList.size(); i++) {
            Customer customer1 = customerList.get(i);
            if (customer1.getCustomerFirstName().equalsIgnoreCase(customer.getCustomerFirstName())){

                return true;
            }

        }
        return false;
    }

    public boolean isBankExists(List<BankDetails> bankDetailsList, BankDetails bankDetails){
        for (int i = 0; i < bankDetailsList.size(); i++) {
            if(bankDetails.getBankName().equalsIgnoreCase(bankDetailsList.get(i).getBankName())){
                return true;
            }
        }
        return false;
    }


}

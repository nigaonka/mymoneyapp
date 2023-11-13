package ng.mymoney.service;

import ng.mymoney.model.AccountTxn;
import ng.mymoney.model.BankDetails;
import ng.mymoney.model.Customer;
import ng.mymoney.model.CustomerAccounts;

import java.util.List;

public interface MyMoneyService {

    public String createBank(BankDetails bankDetails);
    public String createCustomer(Customer customer);
    public String createCustomerAccounts(CustomerAccounts customerAccounts);
    public List<BankDetails> getAllBanks();
    public List<Customer> getAllCustomers();
    public List<CustomerAccounts> getAllAccounts();
    public BankDetails findBank(String bankName);
    public Customer findCustomer(String custName);
    public List<AccountTxn> findAllTxn();
    public String createTxn(AccountTxn accountTxn);

}

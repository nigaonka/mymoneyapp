package ng.mymoney.service.impl;

import ng.mymoney.MymoneyappApplication;
import ng.mymoney.model.AccountTxn;
import ng.mymoney.model.BankDetails;
import ng.mymoney.model.Customer;
import ng.mymoney.model.CustomerAccounts;
import ng.mymoney.repository.AccountTxnRepo;
import ng.mymoney.repository.BankDetailsRepo;
import ng.mymoney.repository.CustomerAccountsRepo;
import ng.mymoney.repository.CustomerRepo;
import ng.mymoney.service.MyMoneyService;
import ng.mymoney.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyMoneyServiceImpl implements MyMoneyService {

    BankDetailsRepo bankDetailsRepo;
    CustomerRepo customerRepo;
    CustomerAccountsRepo customerAccountsRepo;
    AccountTxnRepo accountTxnRepo;

    private static final Logger log = LoggerFactory.getLogger(MyMoneyServiceImpl.class);
    public MyMoneyServiceImpl(BankDetailsRepo bankDetailsRepo, CustomerRepo customerRepo, CustomerAccountsRepo custRepo, AccountTxnRepo txnRepo) {
        this.bankDetailsRepo = bankDetailsRepo;
        this.customerRepo = customerRepo;
        this.customerAccountsRepo = custRepo;
        this.accountTxnRepo = txnRepo;
    }

    @Override
    public String createBank(BankDetails bankDetails) {
        Validator validator = new Validator();
        if (validator.isBankExists(this.getAllBanks(), bankDetails)) {
            return "Error: " + bankDetails.getBankName() + " already exist ";
        } else {
            bankDetailsRepo.save(bankDetails);
            return bankDetails.getBankName() + " created successfully";
        }
    }

    @Override
    public String createCustomer(Customer customer) {
        Validator validator = new Validator();
        if (validator.isCustomerExists(this.getAllCustomers(), customer)) {
            return "Customer already exists";
        } else {
            customerRepo.save(customer);
            return customer.getCustomerFirstName() + " created successfully";
        }
    }


    @Override
    public List<BankDetails> getAllBanks() {
        log.info("Returning all banks");
        return bankDetailsRepo.findAll();
    }

    @Override
    public List<Customer> getAllCustomers() {

        log.info("Returning all customers");
        return customerRepo.findAll();
    }

    @Override
    public BankDetails findBank(String bankName) {
        List<BankDetails> bankList = bankDetailsRepo.findAll();
        BankDetails bankDetails = null;
        for (int i = 0; i < bankList.size(); i++) {
            bankDetails = bankList.get(i);
            if (bankDetails.getBankName().equalsIgnoreCase(bankName)) {
                return bankDetails;
            }
        }
        return null;
    }

    @Override
    public Customer findCustomer(String custName) {
        List<Customer> customerList = customerRepo.findAll();
        Customer customer = null;
        for (int i = 0; i < customerList.size(); i++) {
            customer = customerList.get(i);
            if (customer.getCustomerFirstName().equalsIgnoreCase(custName))
                return customer;
        }
        return null;
    }

    @Override
    public String createCustomerAccounts(CustomerAccounts customerAccounts) {
        log.info("Creating customer accounts");
            customerAccountsRepo.save(customerAccounts);
            return "Customer account number " + customerAccounts.getAccountNumber() + " created successfully";
    }

    @Override
    public List<CustomerAccounts> getAllAccounts() {

        return customerAccountsRepo.findAll();
    }

    @Override
    public List<AccountTxn> findAllTxn() {
        log.info("Retrieving all transactions");
        return accountTxnRepo.findAll();

    }

    @Override
    public String createTxn(AccountTxn accountTxn) {
        log.info("Creating account transactions ");
        accountTxnRepo.save(accountTxn);
        return "Transaction created successfully " + accountTxn.getTxnId();
    }
}

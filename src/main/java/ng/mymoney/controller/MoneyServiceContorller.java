package ng.mymoney.controller;

import ng.mymoney.model.AccountTxn;
import ng.mymoney.model.BankDetails;
import ng.mymoney.model.Customer;
import ng.mymoney.model.CustomerAccounts;
import ng.mymoney.service.MyMoneyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mymoney")
public class MoneyServiceContorller {

    private MyMoneyService myMoneyService;

    public void setMyMoneyService(MyMoneyService myMoneyService) {
        this.myMoneyService = myMoneyService;
    }

    @GetMapping("/showCustomers")
    public List<Customer> getAllCustomers()
    {
        System.out.println("Getting all customers");
        return myMoneyService.getAllCustomers();
    }

    @GetMapping("/showBanks")
    public List<BankDetails> getAllBanks(){
        System.out.println("Getting all banks from system");
        return myMoneyService.getAllBanks();

    }

    @PostMapping ("/createCustomer")
    public String createCustomers(@RequestBody Customer customer){
       return myMoneyService.createCustomer(customer);
    }

    @PostMapping("/createBank")
    public String createBank(@RequestBody BankDetails bankDetails){

        myMoneyService.createBank(bankDetails);
        return "Bank details created successfully";
    }

    @RequestMapping ("/createAccount")
    public String createCustomerAccount(@RequestBody CustomerAccounts customerAccounts)  {
        System.out.println("Account No " + customerAccounts.getAccountNumber() + " BankId " + customerAccounts.getBankId() +" cust id " + customerAccounts.getCustomerId());
        if(customerAccounts.getAccountNumber() <=0 || customerAccounts.getCustomerId()<=0 || customerAccounts.getBankId()<=0 ) {
            return "Error: Invalid input ";
        }else {
            return myMoneyService.createCustomerAccounts(customerAccounts);

        }
    }

    @RequestMapping ("/getAllAccounts")
    public List<CustomerAccounts> getAllAccounts(){
        System.out.println("Retrieving all accounts ");
        return myMoneyService.getAllAccounts();
    }

    @RequestMapping ("/getAllTxn")
    public List<AccountTxn> getAllTxn(){
        return myMoneyService.findAllTxn();
    }

    @RequestMapping ("/createTxn")
    public String createTxn(@RequestBody AccountTxn accountTxn){
        System.out.println("Creating transaction ");
        return myMoneyService.createTxn(accountTxn);

    }

}

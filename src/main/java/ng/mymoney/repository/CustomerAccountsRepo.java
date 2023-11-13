package ng.mymoney.repository;

import ng.mymoney.model.CustomerAccounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountsRepo extends JpaRepository<CustomerAccounts, Integer> {

}

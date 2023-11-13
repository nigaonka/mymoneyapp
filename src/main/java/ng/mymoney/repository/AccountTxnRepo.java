package ng.mymoney.repository;

import ng.mymoney.model.AccountTxn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTxnRepo extends JpaRepository<AccountTxn, Integer> {
}

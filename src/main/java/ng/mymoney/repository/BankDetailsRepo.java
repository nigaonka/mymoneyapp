package ng.mymoney.repository;

import ng.mymoney.model.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailsRepo extends JpaRepository<BankDetails, Integer> {
}

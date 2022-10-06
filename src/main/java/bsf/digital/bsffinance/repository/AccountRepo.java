package bsf.digital.bsffinance.repository;

import bsf.digital.bsffinance.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account, String> {

    Account findAccountByAccountNumber(String accountNumber);
}

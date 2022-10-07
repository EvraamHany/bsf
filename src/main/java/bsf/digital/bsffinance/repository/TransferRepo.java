package bsf.digital.bsffinance.repository;

import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransferRepo extends JpaRepository<Transfer, Long> {
}

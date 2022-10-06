package bsf.digital.bsffinance.service;

import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.repository.AccountRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account getAccountByAccountNumber(String accountNumber){
        return accountRepo.findAccountByAccountNumber(accountNumber);
    }
}

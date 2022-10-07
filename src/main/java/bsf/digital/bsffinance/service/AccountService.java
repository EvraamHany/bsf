package bsf.digital.bsffinance.service;

import bsf.digital.bsffinance.exceptions.AccountNotExist;
import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.repository.AccountRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account getAccountByAccountNumber(String accountNumber) throws AccountNotExist {
        Account account = accountRepo.findAccountByAccountNumber(accountNumber);
        if(account==null){
            throw new AccountNotExist(String.format("%S %S %S", "Account",accountNumber,"is not Exist"));
        }else
        return account;
    }
}

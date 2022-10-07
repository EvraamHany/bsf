package bsf.digital.bsffinance.service;

import bsf.digital.bsffinance.exceptions.AccountNotExist;
import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.model.Transfer;
import bsf.digital.bsffinance.repository.AccountRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final AccountRepo accountRepo;

    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account getAccountByAccountNumber(String accountNumber) throws AccountNotExist {
        Account account = checkIfAccountExist(accountNumber);
        return account;
    }

    public void updateDebitAccount(Transfer transfer, Account debitAccount) {
        BigDecimal amountToIncrease = (debitAccount.getBalance()).add(transfer.getAmount());
        debitAccount.setBalance(amountToIncrease);
        accountRepo.save(debitAccount);
    }
    public void updateCreditAccount(Transfer transfer, Account creditAccount) {
        BigDecimal amountToDeduct = (creditAccount.getBalance()).subtract(transfer.getAmount());
        creditAccount.setBalance(amountToDeduct);
        accountRepo.save(creditAccount);
    }
    public Account checkIfAccountExist(String accountNumber) throws AccountNotExist {
        Account account = accountRepo.findAccountByAccountNumber(accountNumber);
        if (account == null) {
            throw new AccountNotExist(String.format("%S %S %S", "Account",accountNumber,"is not Exist"));
        }
        else return account;
    }
}

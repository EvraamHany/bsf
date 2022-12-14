package bsf.digital.bsffinance.service;

import bsf.digital.bsffinance.exceptions.AccountNotExist;
import bsf.digital.bsffinance.exceptions.NotValidTransaction;
import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.model.Transfer;
import bsf.digital.bsffinance.model.TransferResponseEntity;
import bsf.digital.bsffinance.repository.TransferRepo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransferService {
    private final TransferRepo transferRepo;
    private final AccountService accountService;

    public TransferService(TransferRepo transferRepo, AccountService accountService) {
        this.transferRepo = transferRepo;
        this.accountService = accountService;
    }

    @Transactional(isolation= Isolation.SERIALIZABLE)
//    @Async
    public TransferResponseEntity applyTransfer(Transfer transfer) throws AccountNotExist, NotValidTransaction {
        Account creditAccount = accountService.checkIfAccountExist(transfer.getCreditAccount());
        Account debitAccount = accountService.checkIfAccountExist(transfer.getDebitAccount());
        isTheSameAccount(creditAccount,debitAccount);
        hasValidBalance(creditAccount, transfer.getAmount());
        accountService.updateDebitAccount(transfer, debitAccount);
        accountService.updateCreditAccount(transfer, creditAccount);
        transferRepo.save(transfer);
        return new TransferResponseEntity().creditAccount(creditAccount).debitAccount(debitAccount).amount(transfer.getAmount());
    }

    private void isTheSameAccount(Account creditAccount, Account debitAccount) throws NotValidTransaction {
        if (creditAccount.equals(debitAccount)){
            throw new NotValidTransaction("credit and debit account are the same one");
        }
    }


    public void hasValidBalance(Account creditAccount, BigDecimal transferAmount) throws NotValidTransaction {
        if (creditAccount.getBalance().compareTo(transferAmount) < 0) {
            throw new NotValidTransaction("credit account don't has the required amount");
        }
    }
}

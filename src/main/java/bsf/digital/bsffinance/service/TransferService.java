package bsf.digital.bsffinance.service;

import bsf.digital.bsffinance.exceptions.AccountNotExist;
import bsf.digital.bsffinance.exceptions.NotValidTransaction;
import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.model.Transfer;
import bsf.digital.bsffinance.model.TransferResponseEntity;
import bsf.digital.bsffinance.repository.AccountRepo;
import bsf.digital.bsffinance.repository.TransferRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {
    private final AccountRepo accountRepo;
    private final TransferRepo transferRepo;

    public TransferService(AccountRepo accountRepo, TransferRepo transferRepo) {
        this.accountRepo = accountRepo;
        this.transferRepo = transferRepo;
    }

    @Transactional
    public TransferResponseEntity applyTransfer(Transfer transfer) throws AccountNotExist, NotValidTransaction {

            Account creditAccount = checkIfAccountExist(transfer.getCreditAccount());
            Account debitAccount = checkIfAccountExist(transfer.getDebitAccount());
            hasValidBalance(creditAccount, transfer.getAmount());
            updateDebitAccount(transfer, debitAccount);
            updateCreditAccount(transfer, creditAccount);
            transferRepo.save(transfer);
            return new TransferResponseEntity().creditAccount(creditAccount).debitAccount(debitAccount).amount(transfer.getAmount());
    }

    private void updateCreditAccount(Transfer transfer, Account creditAccount) {
        Float amountToDeduct = creditAccount.getBalance() - transfer.getAmount();
        creditAccount.setBalance(amountToDeduct);
        accountRepo.save(creditAccount);
    }

    private void updateDebitAccount(Transfer transfer, Account debitAccount) {
        Float amountToIncrease = debitAccount.getBalance() + transfer.getAmount();
        debitAccount.setBalance(amountToIncrease);
        accountRepo.save(debitAccount);
    }
    private void hasValidBalance(Account creditAccount, Float transferAmount) throws NotValidTransaction {
        if (transferAmount > creditAccount.getBalance()) {
            throw new NotValidTransaction("credit account don't has the required amount");
        }

    }

    private Account checkIfAccountExist(String accountNumber) throws AccountNotExist {
        Account account = accountRepo.findAccountByAccountNumber(accountNumber);
        if (account == null) {
            throw new AccountNotExist(String.format("%S %S %S", "Account",accountNumber,"is not Exist"));
        }
        else return account;
    }
}

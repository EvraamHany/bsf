package bsf.digital.bsffinance.service;

import bsf.digital.bsffinance.TestUtils;
import bsf.digital.bsffinance.exceptions.AccountNotExist;
import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.repository.AccountRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    AccountRepo accountRepo;
    @InjectMocks
    AccountService accountService;

    @Test
    public void getAccountDataTest() throws AccountNotExist {
        when(accountRepo.findAccountByAccountNumber(Mockito.any())).thenReturn(TestUtils.getAccountForTest());
        accountService.getAccountByAccountNumber(Mockito.any());
        verify(accountRepo, times(1)).findAccountByAccountNumber(Mockito.any());

    }
    @Test
    public void accountIsExistTest() throws AccountNotExist {
        when(accountRepo.findAccountByAccountNumber(Mockito.any())).thenReturn(TestUtils.getAccountForTest());
        Account account = accountService.checkIfAccountExist("123456");
        assertEquals(account.getAccountNumber(),TestUtils.getAccountForTest().getAccountNumber());

    }


    @Test(expected = AccountNotExist.class)
    public void accountIsNotExistTest() throws AccountNotExist {
        when(accountRepo.findAccountByAccountNumber(Mockito.any())).thenReturn(null);
        accountService.checkIfAccountExist(Mockito.any());
    }

    @Test
    public void updateCreditAccountTest()  {
        BigDecimal newAmount =accountService.updateCreditAccount(TestUtils.getTransferForTest(),TestUtils.getAccountForTest());
        assertEquals(newAmount,TestUtils.getAccountForTest().getBalance().subtract(TestUtils.getTransferForTest().getAmount()));
    }

    @Test
    public void updateDebitAccountTest()  {
        BigDecimal newAmount =accountService.updateDebitAccount(TestUtils.getTransferForTest(),TestUtils.getAccountForTest());
        assertEquals(newAmount,TestUtils.getAccountForTest().getBalance().add(TestUtils.getTransferForTest().getAmount()));
    }
}

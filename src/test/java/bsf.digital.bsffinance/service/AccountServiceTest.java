package bsf.digital.bsffinance.service;

import bsf.digital.bsffinance.TestUtils;
import bsf.digital.bsffinance.exceptions.AccountNotExist;
import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.repository.AccountRepo;
import bsf.digital.bsffinance.service.AccountService;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    AccountRepo accountRepo;
    @InjectMocks
    AccountService accountService;

    @Test
    public void getAccountDataTest() throws AccountNotExist {
        accountService.getAccountByAccountNumber(Mockito.any());
        verify(accountRepo, times(1)).findAccountByAccountNumber(Mockito.any());

    }
    @Test
    public void accountIsExistTest() throws AccountNotExist {
        when(accountRepo.findAccountByAccountNumber(Mockito.any())).thenReturn(TestUtils.getAccountForTest());
        Account account = accountService.getAccountByAccountNumber("123456");
        assertEquals(account.getAccountNumber(),TestUtils.getAccountForTest().getAccountNumber());

    }


    @Test(expected = AccountNotExist.class)
    public void accountIsNotExistTest() throws AccountNotExist {
        when(accountRepo.findAccountByAccountNumber(Mockito.any())).thenReturn(null);
        accountService.getAccountByAccountNumber(Mockito.any());
    }
}

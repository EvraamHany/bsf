package bsf.digital.bsffinance.service;

import bsf.digital.bsffinance.TestUtils;
import bsf.digital.bsffinance.exceptions.NotValidTransaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;


@RunWith(MockitoJUnitRunner.class)
public class TransferServiceTest {


    @InjectMocks
    TransferService transferService;


    @Test
    public void verifyIfAccountHasBalanceTest() throws NotValidTransaction {
        transferService.hasValidBalance(TestUtils.getAccountForTest(),new BigDecimal("12"));
    }

    @Test(expected = NotValidTransaction.class)
    public void verifyIfAccountHasNotBalanceTest() throws NotValidTransaction {
        transferService.hasValidBalance(TestUtils.getAccountForTest(),new BigDecimal("100"));
    }

}

package bsf.digital.bsffinance.service;

import bsf.digital.bsffinance.TestUtils;
import bsf.digital.bsffinance.exceptions.AccountNotExist;
import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.repository.AccountRepo;
import bsf.digital.bsffinance.repository.TransferRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransferServiceTest {
    @Mock
    AccountRepo accountRepo;
    @Mock
    TransferRepo transferRepo;
    @InjectMocks
    TransferService transferService;

    @Test
    public void applyTransferTest() throws AccountNotExist {


    }
    @Test
    public void accountIsExistTest() throws AccountNotExist {

    }


    @Test(expected = AccountNotExist.class)
    public void accountIsNotExistTest() throws AccountNotExist {

    }
}

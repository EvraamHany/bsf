package bsf.digital.bsffinance.controller;

import bsf.digital.bsffinance.TestUtils;
import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.model.Transfer;
import bsf.digital.bsffinance.repository.AccountRepo;
import bsf.digital.bsffinance.repository.TransferRepo;
import bsf.digital.bsffinance.service.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransferControllerTest {

    @Autowired
    TransferService transferService;
    @Autowired
    TransferRepo transferRepo;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void transferSuccessTest() throws Exception {
        //given
        Account creditAccount = accountRepo.save(TestUtils.getCreditAccountForTest());
        Account debitAccount = accountRepo.save(TestUtils.getDebitAccountForTest());
        String requestObject = "{\"creditAccount\":\"123456\",\"debitAccount\":\"678965\",\"amount\":50.00}";
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8080/api/transfer")
                .content(requestObject)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        Transfer actualTransfer = transferRepo.findAll().get(0);
        assertEquals(actualTransfer.getAmount(), new BigDecimal("50.00"));
        assertEquals(actualTransfer.getCreditAccount(), "123456");
        assertEquals(actualTransfer.getDebitAccount(), "678965");
        assertEquals(accountRepo.findAccountByAccountNumber("123456").getBalance(), creditAccount.getBalance().subtract(new BigDecimal("50.00")));
        assertEquals(accountRepo.findAccountByAccountNumber("678965").getBalance(), debitAccount.getBalance().add(new BigDecimal("50.00")));
    }

    @Test
    public void transferFailedIllegalParameterTest() throws Exception {
        //given
        String requestObject = "{\"creditAccount\":\"1234\",\"debitAccount\":\"345\",\"amount\":DDDD}}";
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8080/api/transfer")
                .content(requestObject)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void transferFailed_TransferOnSameAccountTest() throws Exception {
        //givem
        accountRepo.save(TestUtils.getAccountForTest());
        String requestObject = "{\"creditAccount\":\"123456\",\"debitAccount\":\"123456\",\"amount\":5.00}";
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8080/api/transfer")
                .content(requestObject)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void transferFaild_AmountIsGreaterThanBalanceTest() throws Exception {
        //given
        accountRepo.save(TestUtils.getCreditAccountForTest());
        accountRepo.save(TestUtils.getDebitAccountForTest());
        String requestObject = "{\"creditAccount\":\"123456\",\"debitAccount\":\"678965\",\"amount\":200.00}";
        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8080/api/transfer")
                .content(requestObject)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
}

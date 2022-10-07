package bsf.digital.bsffinance.controller;

import bsf.digital.bsffinance.TestUtils;
import bsf.digital.bsffinance.repository.AccountRepo;
import bsf.digital.bsffinance.repository.TransferRepo;
import bsf.digital.bsffinance.service.AccountService;
import bsf.digital.bsffinance.service.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(TransferController.class)
public class TransferControllerTest {

    @MockBean
    TransferService transferService;
    @MockBean
    TransferRepo transferRepo;
    @Autowired
    private MockMvc mockMvc;



    @Test
    public void transferSuccessTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8080/api/transfer")
                .content("{\"creditAccount\":\"1234\",\"debitAccount\":\"345\",\"amount\":20.99}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void transferFailedIllegalParameterTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("http://localhost:8080/api/transfer")
                .content("{\"creditAccount\":\"1234\",\"debitAccount\":\"345\",\"amount\":DDDD}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
}

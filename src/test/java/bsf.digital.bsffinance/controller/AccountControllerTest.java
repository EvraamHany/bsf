package bsf.digital.bsffinance.controller;

import bsf.digital.bsffinance.TestUtils;
import bsf.digital.bsffinance.exceptions.AccountNotExist;
import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.repository.AccountRepo;
import bsf.digital.bsffinance.service.AccountService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.swing.text.Utilities;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    private MockMvc mockMvc;



    @Test
    public void AccountSuccessTest() throws Exception {
        // Given
        accountRepo.save(TestUtils.getAccountForTest());

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8080/api/account/123456")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.getContentAsString());
        assertSame(TestUtils.getJsonAccount().get("id"),json.get("id"));
    }

    @Test
    public void AccountNotExistTest() throws Exception {
        // Given
        accountRepo.save(TestUtils.getAccountForTest());

        //when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("http://localhost:8080/api/account/12333")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}

package bsf.digital.bsffinance;

import bsf.digital.bsffinance.model.Account;

import java.math.BigDecimal;

public class TestUtils {
    public static Account getAccountForTest(){
        Account account = new Account();
        account.setAccountNumber("123456");
        account.setBalance(new BigDecimal("12.23"));
        account.setName("customer");
        return account;
    }
}

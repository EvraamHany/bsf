package bsf.digital.bsffinance;

import bsf.digital.bsffinance.model.Account;

public class TestUtils {
    public static Account getAccountForTest(){
        Account account = new Account();
        account.setAccountNumber("123456");
        account.setBalance(12.23F);
        account.setName("customer");
        return account;
    }
}

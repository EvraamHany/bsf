package bsf.digital.bsffinance;

import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.model.Transfer;
import net.minidev.json.JSONObject;

import java.math.BigDecimal;

public class TestUtils {
    public static Account getAccountForTest(){
        Account account = new Account();
        account.setAccountNumber("123456");
        account.setBalance(new BigDecimal("12.23"));
        account.setName("customer");
        return account;
    }

    public static Transfer getTransferForTest(){
        Transfer transfer= new Transfer();
        transfer.setAmount(new BigDecimal("5.00"));
        transfer.setCreditAccount("123456");
        transfer.setDebitAccount("678965");
        transfer.setTransferNumber(12L);
        return transfer;
    }
    public static JSONObject getJsonAccount() {

        JSONObject account = new JSONObject();
        account.put("id", null);
        account.put("accountNumber", "123456");
        account.put("name", "customer");
        account.put("balance", new BigDecimal("12.23"));

        return account;
    }
}

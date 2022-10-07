package bsf.digital.bsffinance.controller;

import bsf.digital.bsffinance.exceptions.AccountNotExist;
import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/account/{accountnumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountnumber) {
        try {
            Account account = accountService.getAccountByAccountNumber(accountnumber);
            if (account==null)
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND);
            return ResponseEntity.status(200).body(account);
        } catch (AccountNotExist exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

}

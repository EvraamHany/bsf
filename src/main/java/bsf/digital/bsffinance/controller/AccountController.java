package bsf.digital.bsffinance.controller;

import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.service.AccountService;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {
@Autowired
	AccountService accountService;
	@GetMapping("/account/{accountnumber}")
	public ResponseEntity<Account> getUser(@PathVariable String accountnumber) {
		Account account = accountService.getAccountByAccountNumber(accountnumber);
		return ResponseEntity.status(200).body(account);
	}

}

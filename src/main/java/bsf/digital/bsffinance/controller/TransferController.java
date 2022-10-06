package bsf.digital.bsffinance.controller;

import bsf.digital.bsffinance.exceptions.AccountNotExist;
import bsf.digital.bsffinance.exceptions.NotValidTransaction;
import bsf.digital.bsffinance.model.Account;
import bsf.digital.bsffinance.model.Transfer;
import bsf.digital.bsffinance.model.TransferResponseEntity;
import bsf.digital.bsffinance.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransferController {
    @Autowired
    TransferService transferService;


    @PostMapping("/transfer")
    public ResponseEntity<TransferResponseEntity> applyTransfer(@RequestBody Transfer transfer)  {
        try {
            TransferResponseEntity affectedAccounts = transferService.applyTransfer(transfer);
            return ResponseEntity.status(200).body(affectedAccounts);
        } catch (NotValidTransaction exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }catch (AccountNotExist exception) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

}

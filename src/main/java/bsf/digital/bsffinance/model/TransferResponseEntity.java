package bsf.digital.bsffinance.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferResponseEntity {
    @JsonProperty("creditAccount")
    private Account creditAccount;
    @JsonProperty("debitAccount")
    private Account debitAccount;
    @JsonProperty("amount")
    private Long amount;

    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public TransferResponseEntity debitAccount(Account debitAccount){
        this.debitAccount=debitAccount;
        return this;
    }
    public TransferResponseEntity creditAccount(Account creditAccount){
        this.creditAccount=creditAccount;
        return this;
    }
    public TransferResponseEntity amount(Long amount){
        this.amount =amount;
        return this;
    }
}

package bsf.digital.bsffinance.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TransferResponseEntity {
    @JsonProperty("creditAccount")
    private Account creditAccount;
    @JsonProperty("debitAccount")
    private Account debitAccount;
    @JsonProperty("amount")
    private Float amount;

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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
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
    public TransferResponseEntity amount(Float amount){
        this.amount =amount;
        return this;
    }
}

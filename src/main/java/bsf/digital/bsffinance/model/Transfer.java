package bsf.digital.bsffinance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "transfer")
public class Transfer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    Long transferNumber;
    @Column(name = "debitaccount")
    String debitAccount;
    @Column(name = "creditaccount")
    String creditAccount;
    @Column(name = "amount")
    BigDecimal amount;

    public Long getTransferNumber() {
        return transferNumber;
    }

    public void setTransferNumber(Long transferNumber) {
        this.transferNumber = transferNumber;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

package me.dio.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Account number is mandatory")
    @Size(min = 4, max = 20, message = "Account number must be between 4 and 20 characters")
    @Column(name = "number", unique = true, nullable = false, length = 20)
    private String number;

    @NotBlank(message = "Agency is mandatory")
    @Size(min = 3, max = 10, message = "Agency must be between 3 and 10 characters")
    @Column(name = "agency", nullable = false, length = 10)
    private String agency;

    @NotNull(message = "Balance cannot be null")
    @Digits(integer = 13, fraction = 2, message = "Balance must have up to 13 integer digits and 2 decimal places")
    @Column(name = "balance", precision = 13, scale = 2, nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @NotNull(message = "Limit cannot be null")
    @Digits(integer = 13, fraction = 2, message = "Limit must have up to 13 integer digits and 2 decimal places")
    @Column(name = "additional_limit", precision = 13, scale = 2, nullable = false)
    private BigDecimal limit = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        BigDecimal totalAvailable = balance.add(limit);
        if (amount.compareTo(totalAvailable) > 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance = this.balance.subtract(amount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", agency='" + agency + '\'' +
                ", balance=" + balance +
                ", limit=" + limit +
                '}';
    }
}
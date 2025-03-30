package me.dio.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity(name = "tb_card")
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_card_number", columnNames = {"number"})
        },
        indexes = {
                @Index(name = "idx_card_active", columnList = "active")
        }
)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Card number is mandatory")
    @Size(min = 13, max = 19, message = "Card number must be between 13 and 19 digits")
    @Pattern(regexp = "^[0-9]+$", message = "Card number must contain only digits")
    @Column(name = "number", unique = true, nullable = false, length = 19)
    private String number;

    @NotNull(message = "Limit cannot be null")
    @Digits(integer = 13, fraction = 2, message = "Limit must have up to 13 integer digits and 2 decimal places")
    @Column(name = "available_limit", precision = 13, scale = 2, nullable = false)
    private BigDecimal limit = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20, nullable = false)
    private CardType type = CardType.CREDIT;

    @Column(name = "expiration_date", length = 7, nullable = false)
    private String expirationDate;

    @Column(name = "cvv", length = 4, nullable = false)
    private String cvv;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Version
    @Column(name = "version")
    private Long version;

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

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getVersion() {
        return version;
    }

    protected void setVersion(Long version) {
        this.version = version;
    }

    public void increaseLimit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Increase amount must be positive");
        }
        this.limit = this.limit.add(amount);
    }

    public void decreaseLimit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Decrease amount must be positive");
        }
        if (amount.compareTo(this.limit) > 0) {
            throw new IllegalArgumentException("Insufficient available limit");
        }
        this.limit = this.limit.subtract(amount);
    }

    public enum CardType {
        CREDIT, DEBIT, MULTIPLE
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='****" + number.substring(number.length() - 4) + '\'' +
                ", type=" + type +
                ", limit=" + limit +
                ", active=" + active +
                '}';
    }
}
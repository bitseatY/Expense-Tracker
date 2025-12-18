package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @NotNull
    private BigDecimal balance;
    public User(){}

    public User(BigDecimal balance){
        this.balance=balance;
    }


    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    public BigDecimal getBalance() {
        return balance;
    }
}

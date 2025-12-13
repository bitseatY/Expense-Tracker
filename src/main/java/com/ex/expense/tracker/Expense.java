package com.ex.expense.tracker;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private LocalDateTime instant;
    private  long catId;
    private  String des;           //https://github.com/bitseatY/Expense-Tracker.git
    private BigDecimal amount;
    public Expense(long catId ,String des,BigDecimal amount){
        this.amount=amount;
        this.des=des;
        this.catId=catId;
        instant=LocalDateTime.now();
    }

    public long getId() {
        return id;
    }
    public LocalDateTime getInstant(){
        return  instant;
    }

    public long getCatId() {
        return catId;
    }

    public String getDes() {
        return des;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

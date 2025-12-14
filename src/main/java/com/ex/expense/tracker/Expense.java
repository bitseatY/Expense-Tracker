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
    private  String des;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name="cat_id")
    private  Category category;

    public Expense(Category category, String des, BigDecimal amount){
        this.amount=amount;
        this.des=des;
        this.category=category;
        instant=LocalDateTime.now();
    }

    public long getId() {
        return id;
    }
    public LocalDateTime getInstant(){
        return  instant;
    }

    public Category getCategory() {
        return category;
    }

    public String getDes() {
        return des;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

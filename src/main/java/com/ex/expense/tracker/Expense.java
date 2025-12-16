package com.ex.expense.tracker;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private LocalDate date;
    private  String des;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name="cat_id")
    private  Category category;

    public Expense(){}

    public Expense(Category category, String des, BigDecimal amount){
        this.amount=amount;
        this.des=des;
        this.category=category;
        date =LocalDate.now();
    }

    public long getId() {
        return id;
    }
    public LocalDate getDate(){
        return date;
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDes(String des) {
        this.des = des;
    }

}

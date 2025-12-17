package com.ex.expense.tracker;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="recurring_expenses")
public class RecurringExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;
    private String des;

    private BigDecimal amount;
    private String frequency;
    private LocalDate startDate;
    private LocalDate nextRunDate;
    private String status;
    public RecurringExpense(){}
    public RecurringExpense(Category category,BigDecimal amount,String frequency,String des){
        this.category=category;
        this.frequency=frequency;
        startDate=LocalDate.now();
        this.amount=amount;
        this.des=des;
        status="active";
        if(frequency.equalsIgnoreCase("weekly"))
              nextRunDate=startDate.plusWeeks(1);
        if(frequency.equalsIgnoreCase("monthly"))
            nextRunDate=startDate.plusMonths(1);
        if(frequency.equalsIgnoreCase("yearly"))
            nextRunDate=startDate.plusYears(1);
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getNextRunDate() {
        return nextRunDate;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setNextRunDate() {
        if(frequency.equalsIgnoreCase("weekly"))
            nextRunDate=LocalDate.now().plusWeeks(1);
        else if(frequency.equalsIgnoreCase("monthly"))
            nextRunDate=LocalDate.now().plusMonths(1);
        else if(frequency.equalsIgnoreCase("yearly"))
            nextRunDate=LocalDate.now().plusYears(1);
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
}

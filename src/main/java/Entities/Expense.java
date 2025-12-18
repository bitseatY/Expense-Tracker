package Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @NotNull
    private LocalDate date;
    @NotBlank
    private  String des;
    @NotNull
    @Min(1)
    private BigDecimal amount;
    @ManyToOne(fetch =FetchType.LAZY )
    @JoinColumn(name="cat_id")
    @NotNull
    private Category category;

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

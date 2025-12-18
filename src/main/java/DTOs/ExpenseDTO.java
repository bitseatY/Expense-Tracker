package DTOs;

import Entities.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDTO {
    private  long id;
    private LocalDate date;
    private  String des;
    private BigDecimal amount;
    private Category category;


    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public long getId() {
        return id;
    }

}

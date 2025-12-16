package com.ex.expense.tracker;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tracker")
public class Controller {
    private ExService exService;
    public Controller(ExService exService){
        this.exService = exService;
    }
    @PostMapping("/add/{cId}/{des}/{amount}")
    public Expense saveExpense(@PathVariable("cId") int cId, @PathVariable("des") String des,
                               @PathVariable("amount")BigDecimal amount){
          return  exService.saveExpense(cId,des,amount);

    }
    @GetMapping("/getLog")
    public Page<Expense> getLog(@RequestParam int page,@RequestParam int size){
         return exService.getLog(page,size);
    }
    @GetMapping("/getSummary/{start}/{end}")
    public List<SummaryDTO> getSummary(@PathVariable("start")LocalDate start, @PathVariable("end") LocalDate end){

        return exService.getSummary(start,end);
    }
    @PatchMapping("/edit/{id}")

    public  Expense edit(@PathVariable("id") int id,@RequestBody Expense updatedExpense){
         Expense expense=exService.findById(id);
         if(updatedExpense.getCategory()!=null)
                expense.setCategory(updatedExpense.getCategory());
         if(updatedExpense.getAmount()!=null)
             expense.setAmount(updatedExpense.getAmount());
         if(updatedExpense.getDes()!=null)
             expense.setDes(updatedExpense.getDes());
         if(updatedExpense.getDate()!=null)
             expense.setDate(updatedExpense.getDate());
         return  exService.saveExpense(expense);
    }





}

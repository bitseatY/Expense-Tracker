package com.ex.expense.tracker;

import org.springframework.data.domain.Page;
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

    @PostMapping("/adduser/{balance}")
    public String  createUser(@PathVariable("balance") BigDecimal balance){
          return      exService.saveUser(balance);
    }

    @PostMapping("/add/{cId}/{des}/{amount}")
    public String saveExpense(@PathVariable("cId") int cId, @PathVariable("des") String des,
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

    public  String edit(@PathVariable("id") int id,@RequestBody Expense updatedExpense){
          return exService.edit(id,updatedExpense);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteExpense(@PathVariable("id") long id){
        return exService.delete(id);
    }
    @PostMapping("saveRecurring")
    public String saveRecurringExpense(@RequestParam int cat_id,@RequestParam BigDecimal amount,@RequestParam String des,@RequestParam String frequency){
       return  exService.saveRecurringExpense(cat_id,amount,des,frequency);
    }





}

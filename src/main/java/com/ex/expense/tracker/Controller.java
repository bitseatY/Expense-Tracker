package com.ex.expense.tracker;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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



}

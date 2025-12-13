package com.ex.expense.tracker;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tracker")
public class Controller {
    private ExService exService;
    public Controller(ExService exService){
        this.exService = exService;
    }
    @PostMapping("/save")
    public Expense saveExpense(@RequestBody Expense expense){
          return  exService.saveExpense(expense);

    }



}

package com.ex.expense.tracker;

import org.springframework.stereotype.Service;

@Service
public class ExService {
     private ExRepository exRepository;
     public ExService(ExRepository exRepository){
          this.exRepository=exRepository;
     }
     public Expense saveExpense(Expense expense){
          return exRepository.save(expense);
     }


}

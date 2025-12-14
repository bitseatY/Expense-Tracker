package com.ex.expense.tracker;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExService {
     private ExRepository exRepository;
     private CategoryRepo categoryRepo;
     public ExService(ExRepository exRepository,CategoryRepo categoryRepo){
          this.exRepository=exRepository;
          this.categoryRepo=categoryRepo;
     }
     public Expense saveExpense(int cId, String des, BigDecimal amount){
          Category category=categoryRepo.findById(cId).orElseThrow(()->new CategoryNotFoundException("category not found"));
          return exRepository.save(new Expense(category,des,amount));
     }


}

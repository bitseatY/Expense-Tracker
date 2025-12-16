package com.ex.expense.tracker;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

     public Page<Expense> getLog(int page, int size){
          return exRepository.findAll(PageRequest.of(page,size));
     }
     public List<SummaryDTO> getSummary(LocalDate start,LocalDate end){
              List<SummaryDTO> summaryExpenses=exRepository.findExpensesToSummarize(start,end);
              BigDecimal total=summaryExpenses.stream().map(SummaryDTO::getTotal).reduce(BigDecimal.ZERO,BigDecimal::add);
              for(SummaryDTO summaryDTO:summaryExpenses){
                   summaryDTO.setPercentage(summaryDTO.getTotal().divide(total,2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))+"%");

              }
         return summaryExpenses;

     }



}

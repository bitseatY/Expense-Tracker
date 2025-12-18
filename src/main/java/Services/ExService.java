package Services;


import DTOs.ExpenseDTO;
import DTOs.SummaryDTO;
import Entities.Category;
import Entities.Expense;
import Entities.RecurringExpense;
import Entities.User;
import Exceptions.ResourceNotFoundException;
import Repositories.CategoryRepo;
import Repositories.ExRepository;
import Repositories.RecurringExpensesRepo;
import Repositories.UserRepo;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExService {
     private ExRepository exRepository;
     private CategoryRepo categoryRepo;
     private RecurringExpensesRepo recurringExpensesRepo;
     private UserRepo userRepo;
     private static  final int userId=1;
     public ExService(ExRepository exRepository, CategoryRepo categoryRepo, RecurringExpensesRepo recurringExpensesRepo, UserRepo userRepo){
          this.exRepository=exRepository;
          this.categoryRepo=categoryRepo;
          this.recurringExpensesRepo=recurringExpensesRepo;
          this.userRepo=userRepo;
     }
     public String saveUser(@Valid User user){
           userRepo.save(user);
           return String.format("user successfully created.\n Initial Balance=%.2f",user.getBalance());
     }

     public Expense findById(long id){
          return exRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("expense not found"));
     }
     public String saveExpense(Expense expense){
          exRepository.save(expense);
          updateBalance(expense);
          return  String.format("Expense successfully recorded.\n Remaining Balance= %.2f ",getUser().getBalance());
     }

     public String saveExpense(int cId, String des, BigDecimal amount){
          Category category=categoryRepo.findById(cId).orElseThrow(()->new ResourceNotFoundException("category not found"));
          Expense expense=new Expense(category,des,amount);
          return  saveExpense(expense);
     }
     public User getUser(){
          return userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found."));
     }
     public void updateBalance(Expense expense){
          User user=getUser();
          user.setBalance(user.getBalance().subtract(expense.getAmount()));
          userRepo.save(user);
     }

     public String saveRecurringExpense(int cat_id, BigDecimal amount,String des,String frequency){
          Category category=categoryRepo.findById(cat_id).orElseThrow(()->new ResourceNotFoundException("category not found."));
          RecurringExpense recurringExpense=new RecurringExpense(category,amount,frequency,des);
          recurringExpensesRepo.save(recurringExpense);
          return String.format("Automatic Expense Recording is set.\n%.2f ETB will be deducted from your current balance %s",amount,frequency);
     }
     public  void addExpenseFromRecurring(RecurringExpense recurringExpense){
          Expense expense=new Expense(recurringExpense.getCategory(),recurringExpense.getDes(),recurringExpense.getAmount());
          saveExpense(expense);
     }

     public String delete(long id){
          Expense expense=exRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("expense with id "+id+" not found"));
          User user=getUser();
          user.setBalance(getUser().getBalance().add(expense.getAmount()));
          userRepo.save(user);
          exRepository.deleteById(id);
          return String.format("Expense successfully deleted. \n Current Balance= %.2f ETB",getUser().getBalance());
     }
     public  String edit(int id, @Valid ExpenseDTO updatedExpense){
          Expense expense=findById(id);
          if(updatedExpense.getCategory()!=null)
               expense.setCategory(updatedExpense.getCategory());
          if(updatedExpense.getAmount()!=null&&updatedExpense.getAmount().compareTo(BigDecimal.ONE)>0){
               BigDecimal newBalance=getUser().getBalance().add(expense.getAmount());
               User user=getUser();
               user.setBalance(newBalance);
               userRepo.save(user);
               expense.setAmount(updatedExpense.getAmount());
          }
          if(updatedExpense.getDes()!=null&&!updatedExpense.getDes().isBlank())
               expense.setDes(updatedExpense.getDes());
          if(updatedExpense.getDate()!=null)
               expense.setDate(updatedExpense.getDate());
          return   saveExpense(expense);
     }
     public Page<Expense> getLog(int page, int size){
          return exRepository.findAll(PageRequest.of(page,size));
     }
     public List<SummaryDTO> getSummary(LocalDate start, LocalDate end){
              List<SummaryDTO> summaryExpenses=exRepository.findExpensesToSummarize(start,end);
              BigDecimal total=summaryExpenses.stream().map(SummaryDTO::getTotal).reduce(BigDecimal.ZERO,BigDecimal::add);
              for(SummaryDTO summaryDTO:summaryExpenses){
                   summaryDTO.setPercentage(summaryDTO.getTotal().divide(total,2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100))+"%");

              }
         return summaryExpenses;

     }






}

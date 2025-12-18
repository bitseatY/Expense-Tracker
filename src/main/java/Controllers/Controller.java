package Controllers;

import DTOs.ExpenseDTO;
import DTOs.SummaryDTO;
import Entities.Expense;
import Entities.User;
import Services.ExService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping("/tracker")
public class Controller {
    private ExService exService;
    public Controller(ExService exService){
        this.exService = exService;
    }

    @PostMapping("/adduser")
    public String  createUser(@RequestBody @Valid User user){
          return      exService.saveUser(user);
    }

    @PostMapping("/add/{cId}/{des}/{amount}")
    public String saveExpense(@PathVariable("cId") @NotNull int cId, @PathVariable("des") @NotBlank String des,
                               @PathVariable("amount") @NotNull BigDecimal amount){
          return  exService.saveExpense(cId,des,amount);

    }

    @GetMapping("/getLog")
    public Page<Expense> getLog(@RequestParam @NotNull int page, @RequestParam @NotNull int size){
         return exService.getLog(page,size);
    }
    @GetMapping("/getSummary/{start}/{end}")
    public List<SummaryDTO> getSummary(@PathVariable("start") @NotNull LocalDate start, @PathVariable("end")@NotNull LocalDate end){
        
        return exService.getSummary(start,end);
    }

    @PatchMapping("/edit/{id}")

    public  String edit(@PathVariable("id")  @NotNull int id,@RequestBody @Valid ExpenseDTO updatedExpense){
          return exService.edit(id,updatedExpense);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteExpense(@PathVariable("id")@NotNull long id){
        return exService.delete(id);
    }
    @PostMapping("saveRecurring")
    public String saveRecurringExpense(@RequestParam @NotNull int cat_id,@RequestParam @NotNull BigDecimal amount,
                                        @RequestParam @NotBlank String des,@RequestParam @NotBlank String frequency){
       return  exService.saveRecurringExpense(cat_id,amount,des,frequency);
    }





}

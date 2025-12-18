package Schedulers;

import Entities.RecurringExpense;
import Repositories.RecurringExpensesRepo;
import Services.ExService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@EnableScheduling
@Component
public class RecurringExpenseScheduler {
    private RecurringExpensesRepo recurringExpensesRepo;
    private ExService exService;
    public RecurringExpenseScheduler(RecurringExpensesRepo recurringExpensesRepo, ExService exService){
        this.recurringExpensesRepo=recurringExpensesRepo;
        this.exService=exService;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    @Transactional
    public void process(){
        List<RecurringExpense> dueExpenses=recurringExpensesRepo.findByNextRunDate(LocalDate.now());
        for(RecurringExpense r:dueExpenses){
            if(r.getStatus().equalsIgnoreCase("active")){
                exService.addExpenseFromRecurring(r);
                r.setNextRunDate();
            }
        }


    }

}

package DTOs;

import java.math.BigDecimal;

public class SummaryDTO {
    private String category;
    private BigDecimal total;
    private  String percentage;
    public SummaryDTO(String category, BigDecimal total){

        this.total=total;
        this.category=category;
    }
    public String getCategory(){
        return category;

    }


    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getPercentage() {
        return percentage;
    }

    public BigDecimal getTotal() {
        return total;
    }


}

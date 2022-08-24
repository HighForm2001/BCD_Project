package bcd.data;

import java.io.Serializable;

public class OutstandingFees implements Serializable {
    private double outstandingAmount;
    private double accumulatedPaid;
    private double totalAmount;
    private String dueDate;

    public double getOutstandingAmount() {
        return outstandingAmount;
    }

    public double getAccumulatedPaid() {
        return accumulatedPaid;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getDueDate() {
        return dueDate;
    }
    public OutstandingFees(){
        this.accumulatedPaid = 9999;
        this.totalAmount = 9999;
        this.outstandingAmount = totalAmount - accumulatedPaid;
        this.dueDate = "12/99/9999";
    }


    public OutstandingFees(double accumulatedPaid, double totalAmount, String dueDate) {
        this.accumulatedPaid = accumulatedPaid;
        this.totalAmount = totalAmount;
        this.outstandingAmount = totalAmount - accumulatedPaid;
        this.dueDate = dueDate;
    }
    @Override
    public String toString(){
        if(outstandingAmount!=0){
            return "This student has outstanding fee of RM" + outstandingAmount + ". Due date is: " + dueDate +"\n";
        }
        return "This student has fully paid the tuition fee. Total amount is RM "  + totalAmount +"\n";
    }
}

package bcd.data;

public class OutstandingFees {
    private double outstandingAmount;
    private double accumatedPaidAmount;
    private double totalAmount;
    private String StudentID;
    private String dueDate;

    public double getOutstandingAmount() {
        return outstandingAmount;
    }

    public double getAccumatedPaidAmount() {
        return accumatedPaidAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStudentID() {
        return StudentID;
    }

    public String getDueDate() {
        return dueDate;
    }

    public OutstandingFees(double outstandingAmount, double accumatedPaidAmount, double totalAmount, String studentID, String dueDate) {
        this.outstandingAmount = outstandingAmount;
        this.accumatedPaidAmount = accumatedPaidAmount;
        this.totalAmount = totalAmount;
        StudentID = studentID;
        this.dueDate = dueDate;
    }
}

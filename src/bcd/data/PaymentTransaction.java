package bcd.data;

public class PaymentTransaction {
    private String transactionID;
    private String date;
    private double amount;
    private String receiptNumber;
    private String studentID;

    public PaymentTransaction(String transactionID, String date, double amount, String receiptNumber, String studentID) {
        this.transactionID = transactionID;
        this.date = date;
        this.amount = amount;
        this.receiptNumber = receiptNumber;
        this.studentID = studentID;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public String getStudentID() {
        return studentID;
    }
}

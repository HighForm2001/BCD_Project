package bcd.data;

import java.io.Serializable;

public class PaymentTransaction implements Serializable {
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
    public PaymentTransaction(){
        this.transactionID = "1";
        this.date = "2/22/22";
        this.amount = 223;
        this.receiptNumber = "223";
        this.studentID = "1";
    }
    public PaymentTransaction(String transactionID, String studentID){
        this.transactionID = transactionID;
        this.date = "2/22/22";
        this.amount = 223;
        this.receiptNumber = "223";
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

    @Override
    public String toString() {
        return "\n======================\nPaymentTransaction\n======================\n" +
                "transactionID = '" + transactionID + '\'' +
                "\ndate = '" + date + '\'' +
                "\namount = RM " + amount +
                "\nreceiptNumber = '" + receiptNumber + '\''+
                "\nstudentID = '" + studentID + '\'' +
                '\n';
    }
}

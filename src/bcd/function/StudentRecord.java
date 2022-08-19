package bcd.function;

import bcd.data.*;
import org.javatuples.Quintet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentRecord implements Serializable {
    public static final int SIZE = 10;
    private String merkleRoot = "0";

    public StudentRecord() {
        recordList = new ArrayList<>(SIZE);
    }

    public String getMerkleRoot(){return merkleRoot;}
    public void setMerkleRoot(List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>> recordList){
        MerkleTree mt = MerkleTree.getInstance(recordList);
        mt.build();
        this.merkleRoot = mt.getRoot();
    }
    private List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>> recordList;

    public List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>> getRecordList() {
        return recordList;
    }
    public void add(Quintet toAdd){
        recordList.add(toAdd);
    }
    @Override
    public String toString(){
        return "Record [recordList = " + recordList + "]";
    }
}

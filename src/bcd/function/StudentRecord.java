package bcd.function;

import bcd.data.*;
import org.javatuples.Quintet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentRecord implements Serializable {
    public static final int SIZE = 10;
    private String merkleRoot = "0";
    private List<String> hashes = new ArrayList<>();
    public StudentRecord() {
        recordList = new ArrayList<>(SIZE);
    }

    public String getMerkleRoot(){return merkleRoot;}
    public void setMerkleRoot(List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>> recordList){
        MerkleTree mt = MerkleTree.getInstance(recordList);
        mt.build();
        this.merkleRoot = mt.getRoot();
        this.hashes = mt.getHashes();
    }
    private List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>> recordList;
    public List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>> getRecordList() {
        return recordList;
    }
    public boolean add(Quintet toAdd){
        if(recordList.size() != SIZE) {
            recordList.add(toAdd);
            setMerkleRoot(recordList);
            return true;
        }
        return false;
    }
    public List<String> getHashes(){return hashes;}
    @Override
    public String toString(){
        return "Record [recordList = " + recordList + ", merkleroot = " + merkleRoot + "]";
    }
}

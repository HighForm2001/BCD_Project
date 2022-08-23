package bcd.function;

import bcd.config.GeneralOperation;
import bcd.data.*;
import org.javatuples.Quintet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MerkleTree {

    private List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>> recordList;
    private String root = "0";
    private List<String> hashes = new LinkedList<>();
    public String getRoot(){return root;}
    public List<String> getHashes(){return hashes;}
    private MerkleTree(List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>> recordList){
        this.recordList = recordList;
    }
    private static MerkleTree instance;
    public static MerkleTree getInstance(List recordList){
        if (instance == null)
            return new MerkleTree(recordList);
        else
            return instance;
    }

    public void build(){
        List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>>tempList = new ArrayList<>();
        for(Quintet q: this.recordList)
            tempList.add(q);
        List<String> hashes = genRecordHashList_quintet(tempList);
        while( hashes.size() != 1)
            hashes = genRecordHashList_string(hashes);
        this.root = hashes.get(0);
        this.hashes = hashes;
    }
    private List<String> genRecordHashList_quintet(List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>> recordList){
        List<String> hashList = new ArrayList<>();
        int i = 0;
        while(i < recordList.size()){
            Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction> left = recordList.get(i); //这边还要改
            i ++;
            Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction> right = null;
            String hashing;
            if(i != recordList.size()) {
                right = recordList.get(i);
                hashing = Hashing.hash(left.add(right).toString().getBytes(),GeneralOperation.getSha_Algo());
            }else
                hashing = Hashing.hash(left.toString().getBytes(),GeneralOperation.getSha_Algo());
            hashList.add(hashing);
            i++;
        }
        return hashList;
    }
    private List<String> genRecordHashList_string(List<String> record){
        List<String> hashList = new ArrayList<>();
        int i = 0;
        while(i < record.size()){
            String left = record.get(i);
            i ++;
            String right = "";
            if(i != record.size())
                right = record.get(i);
            String hashing = Hashing.hash(left.concat(right).getBytes(),GeneralOperation.getSha_Algo());
            hashList.add(hashing);
            i++;
        }
        return hashList;
    }
}

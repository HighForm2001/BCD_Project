package bcd.app;

import bcd.data.*;
import bcd.function.Block;
import bcd.function.Blockchain;
import bcd.function.StudentRecord;
import org.javatuples.Quintet;

public class testingApp {
    public static void main(String[] args)throws Exception{
        testBlockchain();
    }
    public static void testBlockchain(){
        StudentRecord record = new StudentRecord();
        StudentInformation si = new StudentInformation();
        StudentResult sr = new StudentResult();
        Certificate c = new Certificate();
        OutstandingFees of = new OutstandingFees();
        PaymentTransaction pf = new PaymentTransaction();
        Quintet myQuintet = new Quintet<>(si,sr,c,of,pf);
        record.add(myQuintet);
        System.out.println(record);
        Block block = new Block("0");
        block.setRecord(record);
        System.out.println(block);
        Blockchain.distribute();
//        Blockchain.eliminate();
    }
}

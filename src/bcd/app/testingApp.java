package bcd.app;

import bcd.client.User;
import bcd.data.*;
import bcd.function.Block;
import bcd.function.Blockchain;
import bcd.function.MerkleTree;
import bcd.function.StudentRecord;
import org.javatuples.Quintet;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

public class testingApp {
    public static void main(String[] args)throws Exception {
//        testBlockchain();
//        testMerkleTree();
        testUser();
        String email = "jychin47a";
        String singature = testRegister(email);
        boolean isValid = testLogin(email,singature);
        System.out.println(isValid);
    }


    private static void testUser(){
        User u = new User();
        String email = "jychinsss";
        String signature = u.register(email);
        boolean isValid = u.login(email,signature);
        System.out.println(isValid);
    }
    private static String testRegister(String email){
        User u = new User();
        return u.register(email);
    }
    private static boolean testLogin(String email, String signature){
        User u = new User();
        return u.login(email, signature);
    }

    public static void testBlockchain() throws Exception{
        StudentRecord record = new StudentRecord();
        StudentInformation si = new StudentInformation();
        StudentResult sr = new StudentResult();
        Certificate c = new Certificate();
        OutstandingFees of = new OutstandingFees();
        PaymentTransaction pf = new PaymentTransaction();
        Quintet myQuintet = new Quintet<>(si,sr,c,of,pf);
        record.add(myQuintet);
        record.add(myQuintet);
        record.add(myQuintet);
        System.out.println(record);
        Block block = new Block("0");
        block.setRecord(record);
        TimeUnit.SECONDS.sleep(3);
        Block block2 = new Block("0");
        block2.setRecord(record);
//        System.out.println(block);
        Blockchain.genesis(record);
        Blockchain.nextBlock(block);
        Blockchain.nextBlock(block);
        Blockchain.nextBlock(block2);
        Blockchain.nextBlock(block2);
        Blockchain.get().forEach(block1 -> System.out.println(block1.getRecord()));
        Blockchain.distribute();
    }
}

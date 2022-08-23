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
        testRegister("jychin47@gmail.com");
//        System.out.println(testLogin("jychin47@gmail.com"));
    }



    private static void testUser(){
        User u = new User();
        String email = "jychinsss";
        u.register(email);
    }
    private static void testRegister(String email){
        User u = new User();
        u.register(email);
    }
    private static boolean testLogin(String email){
        User u = new User();
        return u.login(email);
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

    }
}

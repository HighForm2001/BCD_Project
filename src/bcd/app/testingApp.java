package bcd.app;

import bcd.client.ManagementUser;
import bcd.client.User;
import bcd.function.Block;
import bcd.function.Blockchain;
import bcd.function.StudentRecord;

import java.util.List;

public class testingApp {
    public static void main(String[] args)throws Exception {
//        testBlockchain();
//        testMerkleTree();
//        testRegister("jychin47@gmail.com");
//        System.out.println(testLogin("jychin47@gmail.com"));
//        testBlockchain();
        testVerify();
    }

    private static void testVerify() {
        User u = new User();
        List<Block> blocks = Blockchain.retrieve_chain();
        Block lastBlock = blocks.get(blocks.size()-1);
        StudentRecord sr = new StudentRecord();

        ViewRecord.findRecord(sr,"(30)");
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

    public static void testBlockchain() throws Exception{//can be port to create function
        ManagementUser mu = new ManagementUser();
        mu.createRecord();


    }
}

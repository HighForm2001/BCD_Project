package bcd.app;

import bcd.client.User;
import bcd.config.GeneralOperation;
import bcd.data.*;
import bcd.function.Block;
import bcd.function.Blockchain;
import bcd.function.StudentRecord;
import org.javatuples.Quintet;

import java.io.File;
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
        StudentRecord sr = lastBlock.getRecord();
        u.viewRecord(sr,"(1)");
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

    public static void testBlockchain() throws Exception{//can be port to create function
        File f = new File(GeneralOperation.getMaster_binary());
        StudentRecord record = new StudentRecord();
        for(int i = 0; i < 31; i ++){
            StudentInformation si = new StudentInformation("Student " + i, "("+i+")");
            StudentResult sr = new StudentResult();
            Certificate c = new Certificate();
            OutstandingFees of = new OutstandingFees();
            PaymentTransaction pf = new PaymentTransaction();
            Quintet myQuintet = new Quintet<>(si,sr,c,of,pf);
            System.out.println("Loop " + (i+1) + " added this student inside: " + si.getName());
            if(!record.add(myQuintet)){

                if(!f.exists()||Blockchain.retrieve_chain()==null){
                    Blockchain.genesis(record);
                }else{
                    Block previousBlock = Blockchain.retrieve_chain().get(Blockchain.retrieve_chain().size()-1);
                    Block newBlock = new Block(previousBlock.getHeader().getCurrHash(),record);
                    Blockchain.nextBlock(newBlock);

                }
                record = new StudentRecord();
                record.add(myQuintet);
            }
        }
        List<Block> bc = Blockchain.retrieve_chain();
        Block lastBlock = bc.get(bc.size()-1);
        Block leftOut = new Block(lastBlock.getHeader().getCurrHash(),record);
        Blockchain.nextBlock(leftOut);
        Blockchain.distribute();


    }
}

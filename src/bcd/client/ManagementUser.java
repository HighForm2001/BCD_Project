package bcd.client;

import bcd.config.GeneralOperation;
import bcd.data.*;
import bcd.function.Block;
import bcd.function.Blockchain;
import bcd.function.StudentRecord;
import org.javatuples.Quintet;

import java.io.File;
import java.util.List;

public class ManagementUser extends User{
    public ManagementUser(){
        super();

    }
    public void createRecord(){
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

                if(!f.exists()|| Blockchain.retrieve_chain()==null){
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

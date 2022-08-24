package bcd.app;

import bcd.config.GeneralOperation;
import bcd.data.*;
import bcd.function.Block;
import bcd.function.Blockchain;
import bcd.function.StudentRecord;
import org.javatuples.Quintet;

import java.io.File;
import java.util.List;

public class CreateRecord {
    public static void createRecord(){
        File f = new File(GeneralOperation.getMaster_binary());
        StudentRecord record = new StudentRecord();
        int id = Blockchain.retrieve_chain()==null?0:Blockchain.retrieve_chain().size()*10;
        System.out.println(id);
        for( int i = 0; i < 31; i ++){
            StudentInformation si = new StudentInformation("Student " + id, "("+id+")");
            StudentResult sr = new StudentResult();
            Certificate c = new Certificate();
            OutstandingFees of = new OutstandingFees();
            PaymentTransaction pf = new PaymentTransaction("Transaction " + id);
            Quintet myQuintet = new Quintet<>(si,sr,c,of,pf);
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
            id++;
        }
        List<Block> bc = Blockchain.retrieve_chain();
        Block lastBlock = bc.get(bc.size()-1);
        Block leftOut = new Block(lastBlock.getHeader().getCurrHash(),record);
        Blockchain.nextBlock(leftOut);
//        Blockchain.distribute();
    }
}

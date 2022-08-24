package bcd.app;

import bcd.data.*;
import bcd.function.Block;
import bcd.function.Blockchain;
import bcd.function.StudentRecord;
import org.javatuples.Quintet;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ViewRecord {
    public static void findRecord(String id){
        if(Blockchain.retrieve_chain()!=null) {
            List<Block> blocks = Blockchain.retrieve_chain();
            AtomicBoolean found = new AtomicBoolean(false);
            assert blocks != null;
            blocks.forEach(block -> {
                if (!found.get())
                    found.set(findRecord(block.getRecord(), id));
            });
            if (!found.get())
                JOptionPane.showMessageDialog(null, "Record does not exist in the blockchain.");
        }else
            JOptionPane.showMessageDialog(null,"No block in the blockchain.");
    }
    public static boolean findRecord(StudentRecord record, String id){
        if(isCorrect(record)){
            List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>>
                    meet_requirement = record.getRecordList().stream()
                    .filter(objects -> objects.getValue0().getID().equals(id))
                    .toList();
            if(meet_requirement.size()>0) {
                meet_requirement.forEach(objects -> JOptionPane.showMessageDialog(null, objects));
                return true;
            }
        }
        return false;
    }

    private static boolean isCorrect(StudentRecord record) {
        String mekleRoot = record.getMerkleRoot();
        if(Blockchain.retrieve_chain()!=null) {
            List<Block> blocks = Blockchain.retrieve_chain();
            assert blocks != null;
            List<StudentRecord> studentRecords = blocks.stream()
                    .map(Block::getRecord)
                    .filter(blockRecord -> blockRecord.getMerkleRoot()
                            .equals(mekleRoot)).toList();
            return studentRecords.size() > 0;
        }

        return false;
    }
}

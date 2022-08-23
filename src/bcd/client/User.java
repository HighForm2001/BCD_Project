package bcd.client;

import bcd.config.GeneralOperation;
import bcd.crypto.Asymmetric;
import bcd.crypto.KeyAccess;
import bcd.data.*;
import bcd.function.Block;
import bcd.function.Blockchain;
import bcd.function.StudentRecord;
import bcd.signature.CustomedSign;
import org.javatuples.Quintet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class User {
    private final CustomedSign sign = new CustomedSign();
    private final Asymmetric crypting = new Asymmetric(GeneralOperation.getRsa_Algo());
    private final String path = GeneralOperation.get_encrypted_decrypted_path();
    public User() {
    }
    public boolean login(String email){
        try{
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String checkLine = br.readLine();
            while(checkLine!=null){
                if(crypting.decrypt(checkLine,KeyAccess.getPrivateKey()).equals(email)){
                    String signature = sign.sign(email);
                    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
                    checkLine = r.readLine();
                    r.close();
                    return sign.verify(email,checkLine +signature);
                }
                System.out.println(checkLine);
                checkLine = br.readLine();
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void logout(){
        System.out.println("Logout successfully");
    }
    public void viewRecord(StudentRecord record, String id){
        if(isCorrect(record)){
            List<Quintet<StudentInformation, StudentResult, Certificate, OutstandingFees, PaymentTransaction>>
                    meet_requirement = record.getRecordList().stream()
                                             .filter(objects -> objects.getValue0().getID().equals(id))
                                             .toList();
            if(meet_requirement.size()>0)
                meet_requirement.forEach(System.out::println);
            else
                System.out.println("No student with this id " + id + " under this record.\n" + record);

        }
        else
            System.out.println("Record does not exist in the blockchain.");
    }

    private boolean isCorrect(StudentRecord record) {
        String mekleRoot = record.getMerkleRoot();
        List<Block> blocks = Blockchain.retrieve_chain();
        List<StudentRecord> studentRecords = blocks.stream().filter(block->block.getRecord().getMerkleRoot().equals(mekleRoot)).map(Block::getRecord).toList();
        return studentRecords.size()>0;
    }

    public void register(String email){
        try {
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String checkLine = br.readLine();
            while(checkLine!= null){
                if(crypting.decrypt(checkLine,KeyAccess.getPrivateKey()).equals(email)){
                    System.out.println("This is a registered email!");
                    return;
                }
                checkLine = br.readLine();
            }
            String cipherText = crypting.encrypt(email, KeyAccess.getPublicKey());
            if (f.exists())
                Files.writeString(Paths.get(path),cipherText+"\n",StandardOpenOption.APPEND);
            else
                Files.writeString(Paths.get(path),cipherText+"\n", StandardOpenOption.CREATE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

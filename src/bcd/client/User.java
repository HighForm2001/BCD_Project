package bcd.client;

import bcd.config.GeneralOperation;
import bcd.crypto.Asymmetric;
import bcd.crypto.KeyAccess;
import bcd.data.*;
import bcd.function.Block;
import bcd.function.Blockchain;
import bcd.function.Hashing;
import bcd.function.StudentRecord;
import bcd.signature.CustomedSign;
import org.javatuples.Quintet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
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
            record.getRecordList().stream().filter(objects -> objects.getValue0().getID().equals(id)).forEach(System.out::println);
        }
    }

    private boolean isCorrect(StudentRecord record) {
        String mekleRoot = record.getMerkleRoot();
        List<Block> blocks = Blockchain.get();
        Block block_record = blocks.get(blocks.indexOf(record));
        StudentRecord verify_record = block_record.getRecord();
        String verify_MekleRoot = verify_record.getMerkleRoot();
        if(mekleRoot.equals(verify_MekleRoot))
            return true;
        else{
            List<String> hashes = record.getHashes();
            List<String> verify_hashes = verify_record.getHashes();
            if(hashes.size()>verify_hashes.size()){
                System.out.println("Tampered! This record does not exist in blockchain.");
                verify_hashes.forEach(hashes::remove);
                hashes.forEach(System.out::println);
            } else if (hashes.size() < verify_hashes.size()) {
                System.out.println("Tampered! This record has been deleted");
                hashes.forEach(verify_hashes::remove);
                verify_hashes.forEach(System.out::println);
            }else{
                List<String> differentHashes = hashes.stream().filter(s -> !verify_hashes.contains(s)).collect(Collectors.toList());
                System.out.println("These records are being tampered.");
                record.getRecordList().stream().filter(objects -> differentHashes.contains(Hashing.hash(objects.toString().getBytes(),GeneralOperation.getSha_Algo()))).forEach(System.out::println);
            }
            return false;
        }
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

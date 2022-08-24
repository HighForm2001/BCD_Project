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

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.function.Consumer;

public class User {
    private final CustomedSign sign = new CustomedSign();
    private final Asymmetric crypting = new Asymmetric(GeneralOperation.getRsa_Algo());
    private final String path = GeneralOperation.get_encrypted_decrypted_path();
    public User() {
    }
    public boolean login(String email, JTextField textField1){
        try{
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String checkLine = br.readLine();
            while(checkLine!=null){
                if(crypting.decrypt(checkLine,KeyAccess.getPrivateKey()).equals(email)){
                    String signature = sign.sign(email);
                    textField1.setText(signature);
                    return true;
                }
//                System.out.println(checkLine);
                checkLine = br.readLine();
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean verify(String email, JTextField textField1) {
        try {
            return sign.verify(email, textField1.getText());
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void logout(){
        System.out.println("Logout successfully");
    }


    public void register(String email){
        try {
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String checkLine = br.readLine();
            while(checkLine!= null){
                if(crypting.decrypt(checkLine,KeyAccess.getPrivateKey()).equals(email)){
                    JOptionPane.showMessageDialog(null,"This is a registered email\nDirecting to Login Page!");
                    return;
                }
                checkLine = br.readLine();
            }
            String cipherText = crypting.encrypt(email, KeyAccess.getPublicKey());
            if (f.exists())
                Files.writeString(Paths.get(path),cipherText+"\n",StandardOpenOption.APPEND);
            else
                Files.writeString(Paths.get(path),cipherText+"\n", StandardOpenOption.CREATE);
            JOptionPane.showMessageDialog(null,"Email registered successfully.\nDirecting to Login Page");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

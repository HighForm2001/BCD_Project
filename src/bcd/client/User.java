package bcd.client;

import bcd.config.GeneralOperation;
import bcd.signature.CustomedSign;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    static CustomedSign sign = new CustomedSign();
    protected String signature_path = GeneralOperation.get_signature_path();
    public User() {
    }
    public boolean login(String email, String signature){ //check file signature
//        try{
//            BufferedReader reader = new BufferedReader(new FileReader(signature_path));
//            List<String> lines = new ArrayList<>();
//            String line = reader.readLine();
//            while(line != null){
//                lines.add(line);
//                line = reader.readLine();
//            }
//            reader.close();
//            for(int i = 0; i < lines.size()-1; i = i+2){
//                int j = i+1;
//                if(lines.get(i).equals(email) && lines.get(j).equals(signature)) {
//                    System.out.println("Signed in successful");
//                    return;
//                }
//            }
//            System.out.println("User not found");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        try{
//            String email2 = "2222";
//            String signature2 = sign.sign(email2);
//            return sign.verify(email2,signature2);
            return sign.verify(email,signature);

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("someting wrong");
            return false;
        }

    }
    public void logout(){
        System.out.println("Logout successfully");
    }
    public void viewRecord(String record){
    }
    public String register(String email){
        String signature = sign.sign(email);
        File f = new File(signature_path);
        byte[] toWrite = (email + "\n" + signature + "\n").getBytes();
        try {
            if (f.exists()) {
                BufferedReader read = new BufferedReader(new FileReader(f));
                String line = read.readLine();
                while (line != null) {
                    if (line.equals(email)) {
                        System.out.println("Already registered!");
                        return read.readLine();
                    }
                    line = read.readLine();
                }
                read.close();
                Files.write(Paths.get(signature_path), toWrite, StandardOpenOption.APPEND);
            }else
                Files.write(Paths.get(signature_path), toWrite, StandardOpenOption.CREATE);
        }catch(Exception e){
            e.printStackTrace();
        }
        return signature;

    }
}

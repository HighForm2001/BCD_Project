package bcd.keygen;

import bcd.config.GeneralOperation;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

public class KeyGen {
    private KeyPairGenerator kg;
    private KeyPair kp;
    public KeyGen() {
        try {
            kg = KeyPairGenerator.getInstance(GeneralOperation.getRsa_Algo());
            kg.initialize(1024);
        }catch(Exception e){
            System.out.println("Error in KeyGen line 13");
            e.printStackTrace();
        }
    }
    public static void createKeyPair(){
        try {
            KeyGen keyGen = new KeyGen();
            keyGen.kp = keyGen.kg.generateKeyPair();
            PublicKey pbk = keyGen.kp.getPublic();
            PrivateKey pvk = keyGen.kp.getPrivate();
            write_file(pbk.getEncoded(), GeneralOperation.get_public_path());
            write_file(pvk.getEncoded(), GeneralOperation.get_private_path());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
       private static void write_file(byte[] encoded, String path) {
        byte[] both = Arrays.copyOf(encoded, encoded.length + 1);
        System.arraycopy("\n".getBytes(),0,both,encoded.length,1); //append new line
        File f = new File(path);
        System.out.println(path);
        f.getParentFile().mkdirs();
        try{
            Files.write(Paths.get(path), both, StandardOpenOption.CREATE);
        }catch(Exception e){
            System.out.println("Error in KeyGen line 44");
            e.printStackTrace();
        }
    }
    public KeyPairGenerator getKeyPairGenerator(){
        return kg;
    }

}

package bcd.security;

import bcd.config.GeneralOperation;

import java.security.*;

public class KeyGen {
    KeyPairGenerator kg;
    KeyPair kp;

    public KeyGen() throws NoSuchAlgorithmException {
        kg = KeyPairGenerator.getInstance(GeneralOperation.getAlgorithm());
        kg.initialize(1024);
    }
    public static void createKeyPair(){
        try {
            KeyGen keyGen = new KeyGen();
            keyGen.kp = keyGen.kg.generateKeyPair();
            PublicKey pbk = keyGen.kp.getPublic();
            PrivateKey pvk = keyGen.kp.getPrivate();

            //write pbk and pvk into the files
            GeneralOperation.write_file(pbk.getEncoded(), GeneralOperation.get_public_path());
            GeneralOperation.write_file(pvk.getEncoded(), GeneralOperation.get_private_path());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

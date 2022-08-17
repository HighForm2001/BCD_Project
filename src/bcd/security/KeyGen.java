package bcd.security;

import bcd.config.Configuration;

import java.security.*;

public class KeyGen {
    KeyPairGenerator kg;
    KeyPair kp;

    public KeyGen() throws NoSuchAlgorithmException {
        kg = KeyPairGenerator.getInstance(Configuration.getAlgorithm());
        kg.initialize(1024);
    }
    public static void createKeyPair(){
        try {
            KeyGen keyGen = new KeyGen();
            keyGen.kp = keyGen.kg.generateKeyPair();
            PublicKey pbk = keyGen.kp.getPublic();
            PrivateKey pvk = keyGen.kp.getPrivate();

            //write pbk and pvk into the files
            Configuration.write_file(pbk.getEncoded(), Configuration.get_public_path());
            Configuration.write_file(pvk.getEncoded(), Configuration.get_private_path());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

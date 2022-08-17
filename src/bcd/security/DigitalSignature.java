package bcd.security;

import bcd.config.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;

public class DigitalSignature {
    public static void Keygen(){
        try{
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(Configuration.getAlgorithm());
            kpg.initialize(1024);
            KeyPair kp = kpg.generateKeyPair();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

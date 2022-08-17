package bcd.security;

import bcd.config.GeneralOperation;

import java.io.FileInputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DigitalSignature {
    public static void Keygen(){
        try{
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(GeneralOperation.getAlgorithm());
            kpg.initialize(1024);
            KeyPair kp = kpg.generateKeyPair();
            RSAPublicKey rsa_pbk = (RSAPublicKey) kp.getPublic();
            RSAPrivateKey rsa_pvk = (RSAPrivateKey) kp.getPrivate();
            GeneralOperation.saveKey(GeneralOperation.get_public_path(),rsa_pbk.getEncoded());
            GeneralOperation.saveKey(GeneralOperation.get_private_path(),rsa_pvk.getEncoded());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static String sign(String data) throws Exception{
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(KeyAccess.getPrivateKey().getEncoded());
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey pk = kf.generatePrivate(spec);
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initSign(pk);
        signature.update(data.getBytes());
        //generate the digital signature
        byte[] digital_signature = signature.sign();
        return Base64.getEncoder().encodeToString(digital_signature);
    }
    public static boolean verify(String data, String DS) throws Exception{
        X509EncodedKeySpec spec = new X509EncodedKeySpec(KeyAccess.getPublicKey().getEncoded());
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey pk = kf.generatePublic(spec);
        Signature signature = Signature.getInstance("SHA256WithRSA");
        signature.initVerify(pk);
        signature.update(data.getBytes());
        return signature.verify(Base64.getDecoder().decode(DS));
    }
    private void verifyActionPerformed(){

    }
}

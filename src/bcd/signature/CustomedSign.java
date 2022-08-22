package bcd.signature;

import bcd.client.User;
import bcd.config.GeneralOperation;
import bcd.keygen.KeyGen;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CustomedSign {//one time use only
    private Signature sig;

    private KeyGen keyGen;

    private KeyPair keyPair;
    {
        try{
            keyGen = new KeyGen();
            keyPair = keyGen.getKeyPairGenerator().generateKeyPair();
        }catch(Exception e){
            System.out.println("Error in Customed Sign line 17");
            e.printStackTrace();
        }
    }
    public CustomedSign(){
        super();
        try{
            sig = Signature.getInstance(GeneralOperation.getSha_rsa_Algo());
        }catch(Exception e){
            System.out.println("Error in Customed Sign line 26");
            e.printStackTrace();
        }
    }
    public String sign(String data){
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
            KeyFactory kf = KeyFactory.getInstance(GeneralOperation.getRsa_Algo());
            PrivateKey privateKey = kf.generatePrivate(keySpec);
            sig.initSign(privateKey);
            sig.update(data.getBytes());
            return Base64.getEncoder().encodeToString(sig.sign());
        }catch(Exception e){
            System.out.println("Erros in Customed Sign line 34");
            e.printStackTrace();
            return null;
        }
    }
    public boolean verify(String data, String signature)throws Exception{
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
        KeyFactory kf = KeyFactory.getInstance(GeneralOperation.getRsa_Algo());
        PublicKey publicKey = kf.generatePublic(keySpec);
        System.out.println("Data: " + data);
        System.out.println("Signature: " + signature);
        sig.initVerify(publicKey);
        sig.update(data.getBytes());
        return sig.verify(Base64.getDecoder().decode(signature));
    }
    public static void main(String[] args)throws Exception{
        String data = "jychin47@gmail.com";
        CustomedSign sign = new CustomedSign();
        System.out.println("Date: " + data);
        String signature = sign.sign(data);
//        String signature2 = sign.sign(data);
        System.out.println("Signature: " +signature);

    }
}

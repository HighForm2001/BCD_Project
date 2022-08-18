package bcd.signature;

import bcd.config.GeneralOperation;
import bcd.keygen.KeyGen;

import java.security.KeyPair;
import java.security.Signature;
import java.util.Base64;

public class CustomedSign {
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
    public String sign(String data)throws Exception{
        sig.initSign(keyPair.getPrivate());
        sig.update(data.getBytes());
        return Base64.getEncoder().encodeToString(sig.sign());
    }
    public boolean verify(String data, String signature)throws Exception{
        sig.initVerify(keyPair.getPublic());
        sig.update(data.getBytes());
        return sig.verify(Base64.getDecoder().decode(signature));
    }
    public static void main(String[] args)throws Exception{
        String data = "This";
        CustomedSign sign = new CustomedSign();
        System.out.println("Date: " + data);
        String signature = sign.sign(data);
        System.out.println("Signature: " +signature);
        boolean isValid = sign.verify(data,signature);
        System.out.println(isValid);
    }
}

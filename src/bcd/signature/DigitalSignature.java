package bcd.signature;

import bcd.config.GeneralOperation;
import bcd.keygen.KeyGen;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class DigitalSignature {
    private static String Hashing_Algo = GeneralOperation.getSha_Algo();
    private static String Crypto_Algo = GeneralOperation.getRsa_Algo();
    private Cipher cipher;
    public DigitalSignature(){
        super();
        try{
            cipher = Cipher.getInstance(Crypto_Algo);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private KeyGen keyGen;
    private KeyPair keyPair;
    {
        try{
            keyGen = new KeyGen();
            keyPair = keyGen.getKeyPairGenerator().generateKeyPair();
        }catch(Exception e){
            System.out.println("error in Digitar Signature line 26~30");
            e.printStackTrace();
        }
    }
    //hash function
    public byte[] hash(String data){
        try{
            return MessageDigest.getInstance(Hashing_Algo).digest(data.getBytes());
        }catch(Exception e){
            System.out.println("Exception in digital signature class line 37");
            e.printStackTrace();
            return null;
        }
    }

    public String encrypt(byte[] hash){
        String signature = "";
        try{
            cipher.init(Cipher.ENCRYPT_MODE,keyPair.getPrivate());
            signature = Base64.getEncoder().encodeToString(cipher.doFinal(hash));
        }catch(Exception e){
            System.out.println("Exception in digital signature class line 46");
            e.printStackTrace();

        }
        return signature;
    }

    public boolean verify(String data, String signature){
        byte[] dataHash = this.hash(data);
        try{
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
            byte[] signatureBytes = cipher.doFinal(Base64.getDecoder().decode(signature));
            return Arrays.equals(dataHash, signatureBytes);
        }catch(Exception e){
            System.out.println("Error in digital signature class line 60");
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args){
        DigitalSignature ds = new DigitalSignature();
        String data = "This";
        System.out.println("Date: " + data);
        String signature = ds.encrypt(ds.hash(data));
        System.out.println("Signature: " +signature);
        boolean isValid = ds.verify(data,signature);
        System.out.println(isValid);
    }
}

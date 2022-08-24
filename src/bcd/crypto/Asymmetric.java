package bcd.crypto;

import javax.crypto.Cipher;
import java.security.Key;
import java.util.Base64;

public class Asymmetric extends Crypto{//this one done
    public Asymmetric(String param){
        super(param);
    }
    public String encrypt(String data, Key key) throws Exception{
        String cipherText;
        //initialize
        cipher.init(Cipher.ENCRYPT_MODE,key);
        //encrypt
        byte[] cipherBytes = cipher.doFinal(data.getBytes());
        //convert to string
        cipherText = Base64.getEncoder().encodeToString(cipherBytes);
        return cipherText;
    }
    public String decrypt(String cipherText, Key key) throws Exception{
        //initialization
        cipher.init(Cipher.DECRYPT_MODE,key);
        //decrypt
        byte[] cipherBytes = Base64.getDecoder().decode(cipherText.getBytes());
        byte[] dataBytes = cipher.doFinal(cipherBytes);
        return new String(dataBytes);
    }
}

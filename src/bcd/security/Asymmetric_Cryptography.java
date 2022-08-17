package bcd.security;

import bcd.config.GeneralOperation;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class Asymmetric_Cryptography {
    private Cipher cipher;

    public Asymmetric_Cryptography(){
        try{
            cipher = Cipher.getInstance(GeneralOperation.getAlgorithm());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public String encrypt(String data, PublicKey key)throws Exception{
        String encypted_text = null;
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] cipher_byte = cipher.doFinal(data.getBytes());
        encypted_text = Base64.getEncoder().encodeToString(cipher_byte);
        return encypted_text;
    }
    public String decrypt(String encrypted_data, PrivateKey key)throws Exception{
        cipher.init(Cipher.DECRYPT_MODE,key);
        byte[] data_bytes = cipher.doFinal(Base64.getDecoder().decode(encrypted_data));
        return new String (data_bytes);
    }
}

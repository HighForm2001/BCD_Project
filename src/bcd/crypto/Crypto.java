package bcd.crypto;

import javax.crypto.Cipher;
import java.security.Key;

public abstract class Crypto { //this one done
    protected Cipher cipher;
    public Crypto(String param){
        try{
            cipher = Cipher.getInstance(param);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public abstract String encrypt(String data, Key key)throws Exception;

    public abstract String decrypt(String cipherTexdt, Key key) throws Exception;
}

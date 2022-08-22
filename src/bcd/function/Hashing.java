package bcd.function;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Hex;
public class Hashing {
    public static class Salt{
        public static byte[] gen(){
            SecureRandom sr = new SecureRandom();
            byte[] a = new byte[16];
            sr.nextBytes(a);
            return a;
        }
        public static String hash(byte[] blockBytes,String algo){
            String hashValue = null;
            MessageDigest MeDi = null;

            try {
                MeDi = MessageDigest.getInstance(algo);
                MeDi.update(blockBytes);
                MeDi.update("apu".getBytes());
                MeDi.update(Salt.gen());

                byte[] hashBytes = MeDi.digest();

                return String.valueOf(Hex.encodeHex(hashBytes));

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }


    }
    public static String hash(byte[] dataBytes, String algo) {
        String hashValue = null;

        MessageDigest MeDi = null;

        try {
            MeDi = MessageDigest.getInstance(algo);
            MeDi.update(dataBytes);
            MeDi.update("apu".getBytes());
            byte[] hashBytes = MeDi.digest();

            return String.valueOf(Hex.encodeHex(hashBytes));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

}

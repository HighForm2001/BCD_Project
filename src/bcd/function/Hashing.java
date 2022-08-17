package bcd.function;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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

                StringBuilder B = new StringBuilder();
                for(int i = 0;i<hashBytes.length; i++){
                    B.append(Integer.toHexString(0xFF & hashBytes[i]));

                }
                hashValue = B.toString();

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            return hashValue;
        }


    }
    public static String hash(String data, String algo) {
        String hashValue = null;
        byte[] dataBytes = data.getBytes();

        MessageDigest MeDi = null;

        String hash;
        try {
            MeDi = MessageDigest.getInstance(algo);
            MeDi.update(dataBytes);
            MeDi.update("apu".getBytes());
            MeDi.update(Salt.gen());

            byte[] hashBytes = MeDi.digest();

            StringBuilder B = new StringBuilder();
            for (int i = 0; i < hashBytes.length; i++) {
                B.append(Integer.toHexString(0xFF & hashBytes[i]));

            }
            hash = B.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hash;

    }

}

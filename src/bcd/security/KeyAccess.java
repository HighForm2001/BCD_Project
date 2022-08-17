package bcd.security;

import bcd.config.Configuration;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class KeyAccess {
    public static PublicKey getPublicKey() throws Exception{
        byte[] keyBytes = Files.readAllBytes(Paths.get(Configuration.get_public_path()));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(Configuration.getAlgorithm()).generatePublic(keySpec);
    }
    public static PrivateKey getPrivateKey() throws Exception{
        byte[] keyBytes = Files.readAllBytes(Paths.get(Configuration.get_private_path()));
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance(Configuration.getAlgorithm()).generatePrivate(keySpec);
    }
}

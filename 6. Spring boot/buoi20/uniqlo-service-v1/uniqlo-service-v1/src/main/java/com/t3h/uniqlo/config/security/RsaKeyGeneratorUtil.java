package com.t3h.uniqlo.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
@Slf4j
public class RsaKeyGeneratorUtil {

    @Value("${rsa.key.dir:key}")
    private String keyDir;

    private String publicKeyFile;
    private String privateKeyFile;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void init() {
        publicKeyFile = keyDir + "/public_key.pem";
        privateKeyFile = keyDir + "/private_key.pem";
        initKeys();
    }

    private void initKeys() {
        try {
            // Tao thu muc chua key neu chua ton tai
            File dir = new File(keyDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File pubFile = new File(publicKeyFile);
            File privFile = new File(privateKeyFile);

            // Neu ca hai file deu da ton tai thi doc tu file (tranh viec gen lai lam mat hieu luc token cu)
            if (pubFile.exists() && privFile.exists()) {
                log.info("Loading RSA keys from files...");
                loadKeys();
            } else {
                // Neu chua co file thi tu dong gen moi va luu xuong thu muc local
                log.info("RSA keys not found. Generating new keys...");
                generateAndSaveKeys();
            }
        } catch (Exception e) {
            log.error("Error initializing RSA keys", e);
            throw new RuntimeException("Could not initialize RSA keys", e);
        }
    }

    // Ham sinh cap khoa RSA moi (Do dai 2048 bit)
    private void generateAndSaveKeys() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

        saveKey(publicKeyFile, publicKey.getEncoded(), "PUBLIC KEY");
        saveKey(privateKeyFile, privateKey.getEncoded(), "PRIVATE KEY");
        log.info("RSA keys generated and saved successfully.");
    }

    private void saveKey(String filename, byte[] keyBytes, String type) throws Exception {
        String base64Key = Base64.getMimeEncoder(64, new byte[]{'\n'}).encodeToString(keyBytes);
        String pem = "-----BEGIN " + type + "-----\n" + base64Key + "\n-----END " + type + "-----\n";
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            fos.write(pem.getBytes());
        }
    }

    private void loadKeys() throws Exception {
        byte[] pubBytes = readPemFile(publicKeyFile, "PUBLIC KEY");
        byte[] privBytes = readPemFile(privateKeyFile, "PRIVATE KEY");

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubBytes);
        publicKey = keyFactory.generatePublic(pubSpec);

        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privBytes);
        privateKey = keyFactory.generatePrivate(privSpec);
    }

    private byte[] readPemFile(String filename, String type) throws Exception {
        Path path = Paths.get(filename);
        String pem = new String(Files.readAllBytes(path));
        String base64 = pem.replace("-----BEGIN " + type + "-----", "")
                           .replace("-----END " + type + "-----", "")
                           .replaceAll("\\s", "");
        return Base64.getDecoder().decode(base64);
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}

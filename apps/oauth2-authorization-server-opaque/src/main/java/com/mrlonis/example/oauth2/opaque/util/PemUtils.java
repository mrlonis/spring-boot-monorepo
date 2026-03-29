package com.mrlonis.example.oauth2.opaque.util;

import com.mrlonis.example.oauth2.opaque.model.PemFile;
import com.nimbusds.jose.jwk.RSAKey;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@UtilityClass
@Slf4j
public class PemUtils {
    public static RSAKey generateRsaKey(String privateKeyNameAndPath, String publicKeyNameAndPath)
            throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        KeyPair keyPair = readOrGenerateRsaKeyPair(privateKeyNameAndPath, publicKeyNameAndPath);
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(generateKeyId(publicKey))
                .build();
    }

    private static KeyPair readOrGenerateRsaKeyPair(String privateKeyNameAndPath, String publicKeyNameAndPath)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        RSAPublicKey publicKey;
        RSAPrivateKey privateKey;

        Resource privateKeyFile = loadResource(privateKeyNameAndPath);
        Resource publicKeyFile = loadResource(publicKeyNameAndPath);

        if (publicKeyFile.exists() && privateKeyFile.exists()) {
            log.info("Loading RSA key pair from files.");
            publicKey = readX509PublicKey(publicKeyFile.getFile());
            privateKey = readPKCS8PrivateKey(privateKeyFile.getFile());
        } else {
            log.info("Generating new RSA key pair.");
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair keyPair = generator.generateKeyPair();

            writePemFile(keyPair.getPublic(), "PUBLIC KEY", "./src/main/resources/" + publicKeyNameAndPath);
            writePemFile(keyPair.getPrivate(), "PRIVATE KEY", "./src/main/resources/" + privateKeyNameAndPath);

            return keyPair;
        }

        return new KeyPair(publicKey, privateKey);
    }

    private static String generateKeyId(RSAPublicKey publicKey) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = publicKey.getEncoded();
        byte[] digest = sha256.digest(keyBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }

    private static Resource loadResource(String filenameAndPath) {
        return new ClassPathResource(filenameAndPath);
    }

    private RSAPublicKey readX509PublicKey(File file)
            throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        KeyFactory factory = KeyFactory.getInstance("RSA");

        try (FileReader keyReader = new FileReader(file);
                PemReader pemReader = new PemReader(keyReader)) {

            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
            return (RSAPublicKey) factory.generatePublic(pubKeySpec);
        }
    }

    private RSAPrivateKey readPKCS8PrivateKey(File file)
            throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        KeyFactory factory = KeyFactory.getInstance("RSA");

        try (FileReader keyReader = new FileReader(file);
                PemReader pemReader = new PemReader(keyReader)) {

            PemObject pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
            return (RSAPrivateKey) factory.generatePrivate(privKeySpec);
        }
    }

    private static void writePemFile(Key key, String description, String filename) throws IOException {
        Path path = Paths.get(filename);
        Path parentDir = path.getParent();

        // Ensure parent directories exist
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
            log.info("Created missing directories: {}", parentDir.toAbsolutePath());
        }

        PemFile pemFile = new PemFile(key, description);
        pemFile.write(filename);

        log.info("{} successfully writen in file {}.", description, filename);
    }
}

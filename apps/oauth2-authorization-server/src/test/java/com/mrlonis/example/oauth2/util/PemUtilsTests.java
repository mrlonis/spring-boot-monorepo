package com.mrlonis.example.oauth2.util;

import static com.mrlonis.example.oauth2.util.PemUtils.generateRsaKey;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

class PemUtilsTests {
    @Test
    void testGenerateRsaKey_rsaExists() {
        assertDoesNotThrow(() -> generateRsaKey("exists/id_rsa", "exists/id_rsa.pub"));

        Path rsa = Paths.get("./src/main/resources/exists/id_rsa");
        assertTrue(Files.exists(rsa));

        Path rsaPub = Paths.get("./src/main/resources/exists/id_rsa.pub");
        assertTrue(Files.exists(rsaPub));

        assertDoesNotThrow(() -> generateRsaKey("exists/id_rsa", "exists/id_rsa.pub"));

        assertTrue(Files.exists(rsa));
        assertTrue(Files.exists(rsaPub));

        assertDoesNotThrow(() -> Files.delete(rsa));
        assertDoesNotThrow(() -> Files.delete(rsaPub));
    }

    @Test
    void testGenerateRsaKey_rsaDoesntExist() {
        Path rsa = Paths.get("./src/main/resources/fake/id_rsa");
        assertFalse(Files.exists(rsa));

        Path rsaPub = Paths.get("./src/main/resources/fake/id_rsa.pub");
        assertFalse(Files.exists(rsaPub));

        assertDoesNotThrow(() -> generateRsaKey("fake/id_rsa", "fake/id_rsa.pub"));

        assertTrue(Files.exists(rsa));
        assertTrue(Files.exists(rsaPub));

        assertDoesNotThrow(() -> Files.delete(rsa));
        assertDoesNotThrow(() -> Files.delete(rsaPub));

        assertFalse(Files.exists(rsa));
        assertFalse(Files.exists(rsaPub));
    }
}

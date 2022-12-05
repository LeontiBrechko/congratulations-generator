package net.leontibrechko.congratulationsgenerator.security;

import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class NativeKeyStore {
    private final KeyStore keyStore;
    private final String keyStoreFile;
    private final char[] keyStorePassword;

    public NativeKeyStore(final KeyStore keyStore,
                          final String keyStoreFile,
                          final String keyStorePassword) {
        this.keyStore = keyStore;
        this.keyStoreFile = keyStoreFile;
        this.keyStorePassword = keyStorePassword.toCharArray();

        init();
    }

    public void saveSecret(final KeyStoreSecretName secretName, final String secretValue) {
        try {
            keyStore.setEntry(
                    secretName.name(),
                    new KeyStore.SecretKeyEntry(new SecretKeySpec(secretValue.getBytes(), "AES")),
                    new KeyStore.PasswordProtection(keyStorePassword)
            );
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }

        store();
    }

    public String loadSecret(final KeyStoreSecretName secretName) {
        try {
            return new String(keyStore.getKey(secretName.name(), keyStorePassword).getEncoded());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private void init() {
        if (!Paths.get(keyStoreFile).toFile().exists()) {
            try {
                keyStore.load(null, keyStorePassword);
            } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
                throw new RuntimeException(e);
            }

            store();
        }

        load();
    }

    private void load() {
        try (final FileInputStream fileInputStream = new FileInputStream(keyStoreFile)) {
            keyStore.load(fileInputStream, keyStorePassword);
        } catch (IOException | CertificateException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void store() {
        try (final FileOutputStream fileOutputStream = new FileOutputStream(keyStoreFile)) {
            keyStore.store(fileOutputStream, keyStorePassword);
        } catch (CertificateException | KeyStoreException | IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

package net.leontibrechko.congratulationsgenerator.config;

import net.leontibrechko.congratulationsgenerator.security.NativeKeyStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyStore;
import java.security.KeyStoreException;

@Configuration
public class KeyStoreConfiguration {
    @Bean
    public NativeKeyStore localKeyStore() {
        try {
            return new NativeKeyStore(
                    KeyStore.getInstance(KeyStore.getDefaultType()),
                    "src/main/resources/keystore.jks",
                    "someRandomPasswordAsKeyStoreIsLocal"
            );
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
    }
}

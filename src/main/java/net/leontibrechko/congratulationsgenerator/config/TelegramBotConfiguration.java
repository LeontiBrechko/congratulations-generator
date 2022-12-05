package net.leontibrechko.congratulationsgenerator.config;

import net.leontibrechko.congratulationsgenerator.security.KeyStoreSecretName;
import net.leontibrechko.congratulationsgenerator.ui.telegram.GeneratorTelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfiguration {
    private final GeneratorConfig generatorConfig;
    private final KeyStoreConfiguration keyStoreConfiguration;

    public TelegramBotConfiguration(final GeneratorConfig generatorConfig,
                                    final KeyStoreConfiguration keyStoreConfiguration) {
        this.generatorConfig = generatorConfig;
        this.keyStoreConfiguration = keyStoreConfiguration;
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        try {
            return new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public GeneratorTelegramBot generatorTelegramBot() {
        return new GeneratorTelegramBot(
                keyStoreConfiguration.localKeyStore().loadSecret(KeyStoreSecretName.TELEGRAM_BOT_USERNAME),
                keyStoreConfiguration.localKeyStore().loadSecret(KeyStoreSecretName.TELEGRAM_BOT_TOKEN),
                generatorConfig.congratsGenerator()
        );
    }
}

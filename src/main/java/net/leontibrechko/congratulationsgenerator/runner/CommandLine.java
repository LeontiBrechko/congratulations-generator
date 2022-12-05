package net.leontibrechko.congratulationsgenerator.runner;

import lombok.extern.slf4j.Slf4j;
import net.leontibrechko.congratulationsgenerator.config.TelegramBotConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class CommandLine implements CommandLineRunner {
    private final TelegramBotConfiguration telegramBotConfiguration;

    public CommandLine(final TelegramBotConfiguration telegramBotConfiguration) {
        this.telegramBotConfiguration = telegramBotConfiguration;
    }

    @Override
    public void run(String... args) {
        try {
            telegramBotConfiguration.telegramBotsApi()
                    .registerBot(telegramBotConfiguration.generatorTelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

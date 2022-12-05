package net.leontibrechko.congratulationsgenerator.ui.telegram;

import lombok.extern.slf4j.Slf4j;
import net.leontibrechko.congratulationsgenerator.generator.CongratsGenerator;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class GeneratorTelegramBot extends TelegramLongPollingBot {
    private final String botUsername;
    private final String botToken;
    private final CongratsGenerator congratsGenerator;

    public GeneratorTelegramBot(final String botUsername,
                                final String botToken,
                                final CongratsGenerator congratsGenerator) {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.congratsGenerator = congratsGenerator;
    }

    public void sendMessageToChat(long chatId, String messageText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(messageText);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.debug("Received update: {}", update);

        final String[] messageParts = update.getMessage().getText().split(",");

        final String generatedCongrats;
        if (messageParts.length == 4) {
            generatedCongrats = congratsGenerator.generateCongrats(
                    messageParts[0],
                    messageParts[1],
                    messageParts[2],
                    messageParts[3]);
        } else {
            generatedCongrats = "Invalid input: please provide 4 comma-separated values:\nname,language,occasion,relationship\n\nExample: John,English,Graduation,friend";
        }

        sendMessageToChat(update.getMessage().getChatId(), generatedCongrats);
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}

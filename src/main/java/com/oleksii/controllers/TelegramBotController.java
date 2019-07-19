package com.oleksii.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
@AllArgsConstructor
public class TelegramBotController extends TelegramLongPollingBot {
    @Autowired
    private final BotMessagesHandler botMessagesHandler;

    @Override
    public String getBotUsername() {
        return "playtika_helper_bot";
    }

    @Override
    public String getBotToken() {
        return "924745318:AAFcTTcQgRsSxbG2KzIGyMoJMeXv0t3dlms";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chatId)
                    .setText(String.join("\n", botMessagesHandler.getResult(messageText)));
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                log.error("Error during Telegram message sending", e);
            }
        }
    }
}
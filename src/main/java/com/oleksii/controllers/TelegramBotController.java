package com.oleksii.controllers;

import com.oleksii.jazzyspellcheck.JazzySpellChecker;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@AllArgsConstructor
public class TelegramBotController extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(TelegramBotController.class);
    @Autowired
    private final JazzySpellChecker jazzySpellChecker;

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
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            String correctedText = jazzySpellChecker.getCorrectedText(message_text);


            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(correctedText);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
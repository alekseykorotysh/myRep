package com.oleksii;

import com.oleksii.controllers.TelegramBotController;
import com.oleksii.jazzyspellcheck.JazzySpellChecker;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class);

    public static void main(String[] args) {
        //Add this line to initialize bots context
        ApiContextInitializer.init();
        SpringApplication.run(App.class, args);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramBotController(new JazzySpellChecker()));
        } catch (TelegramApiException e) {
            LOGGER.error(e.getMessage());
        }
    }
}

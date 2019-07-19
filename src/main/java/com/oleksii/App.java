package com.oleksii;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import com.oleksii.controllers.TelegramBotController;
import com.oleksii.dbScript.DbScriptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Slf4j
@SpringBootApplication
public class App {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private Environment env;
    @Autowired
    private TelegramBotController telegramBotController;

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(App.class, args);
    }

    @PostConstruct
    private void initDb() {
        String sqlStatements[] = {
                DbScriptUtil.getScript()
        };

        Arrays.asList(sqlStatements).forEach(sql -> {
            jdbcTemplate.execute(sql);
        });
        registerTelegramBot();
    }

    private void registerTelegramBot() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(telegramBotController);
        } catch (TelegramApiException e) {
            log.error("Telegram registration failed", e);
        }
    }
}

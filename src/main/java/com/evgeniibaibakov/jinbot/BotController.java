package com.evgeniibaibakov.jinbot;


import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotController {

    @Value("${bot.api.key}")
    private String apiKey;

    @Bean
    TelegramBot bot() {
        return new TelegramBot(apiKey);
    }

    @Bean
    JinUpdateListener updateListener() {
        JinUpdateListener jinUpdateListener = new JinUpdateListener();
        bot().removeGetUpdatesListener();
        bot().setUpdatesListener(jinUpdateListener);
        return jinUpdateListener;
    }
}

package com.evgeniibaibakov.jinbot.bot.processing;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public abstract class BotAwareCommand implements Command {

    @Resource
    private TelegramBot bot;

    protected TelegramBot getBot() {
        return this.bot;
    }

}

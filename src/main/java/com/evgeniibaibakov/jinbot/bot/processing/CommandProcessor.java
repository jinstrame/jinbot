package com.evgeniibaibakov.jinbot.bot.processing;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;


@Component
public interface CommandProcessor {
    void process(Update update);
}

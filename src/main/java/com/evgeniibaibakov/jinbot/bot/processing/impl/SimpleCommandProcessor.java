package com.evgeniibaibakov.jinbot.bot.processing.impl;

import com.evgeniibaibakov.jinbot.bot.processing.CommandConverterFactory;
import com.evgeniibaibakov.jinbot.bot.processing.CommandProcessor;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SimpleCommandProcessor implements CommandProcessor {

    @Resource
    private CommandConverterFactory commandConverterFactory;


    @Override
    public void process(Update update) {
        commandConverterFactory.getHandlerForUpdate(update)
                .ifPresent(c -> c.handle(update));
    }
}

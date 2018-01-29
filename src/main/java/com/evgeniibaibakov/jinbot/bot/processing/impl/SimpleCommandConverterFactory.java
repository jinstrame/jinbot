package com.evgeniibaibakov.jinbot.bot.processing.impl;

import com.evgeniibaibakov.jinbot.bot.processing.Command;
import com.evgeniibaibakov.jinbot.bot.processing.CommandConverterFactory;
import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Component
public class SimpleCommandConverterFactory implements CommandConverterFactory {

    @Resource
    private List<Command> commands;

    @Override
    public Optional<Command> getHandlerForUpdate(Update update) {
        return commands.stream()
                .filter(c -> c.isResponsibleForUpdate(update))
                .findFirst();
    }
}

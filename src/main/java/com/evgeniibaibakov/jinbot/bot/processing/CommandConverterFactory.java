package com.evgeniibaibakov.jinbot.bot.processing;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CommandConverterFactory {
    Optional<Command> getHandlerForUpdate(Update update);
}

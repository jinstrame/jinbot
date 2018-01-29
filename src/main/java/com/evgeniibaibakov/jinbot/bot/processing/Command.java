package com.evgeniibaibakov.jinbot.bot.processing;

import com.pengrad.telegrambot.model.Update;
import org.springframework.stereotype.Component;

@Component
public interface Command {
    default void handle(Update update) {
        if (isResponsibleForUpdate(update)) {
            process(update);
        }
    }

    boolean isResponsibleForUpdate(Update update);

    void process(Update update);

}

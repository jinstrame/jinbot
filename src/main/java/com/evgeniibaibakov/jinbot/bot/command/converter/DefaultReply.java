package com.evgeniibaibakov.jinbot.bot.command.converter;

import com.evgeniibaibakov.jinbot.bot.processing.BotAwareCommand;
import com.evgeniibaibakov.jinbot.bot.processing.Command;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;


@Component
public class DefaultReply extends BotAwareCommand {


    @Override
    public boolean isResponsibleForUpdate(Update update) {
        return Optional.ofNullable(update.message())
                .map(Message::text)
                .map(StringUtils::isNotEmpty)
                .orElse(false);
    }

    @Override
    public void process(Update update) {
        Message message = update.message();
        String text = message.text();
        getBot().execute(new SendMessage(message.chat().id(), text));
    }
}

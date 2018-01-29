package com.evgeniibaibakov.jinbot.bot.command.converter;

import com.evgeniibaibakov.jinbot.bot.processing.BotAwareCommand;
import com.evgeniibaibakov.jinbot.pomodoro.PomodoController;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.AnswerCallbackQuery;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
@Log4j
public class PomodoStatusReply extends BotAwareCommand {

    @Resource
    private PomodoController pomodoController;

    @Override
    public boolean isResponsibleForUpdate(Update update) {
        return Optional.ofNullable(update.callbackQuery())
                .map(CallbackQuery::data)
                .isPresent();
    }

    @Override
    public void process(Update update) {
        log.info("processed " + update.callbackQuery().data());
        String existance = pomodoController.getPomodo(update.callbackQuery().message().chat().id().toString())
                .map(pomodo -> "Pomodo running")
                .orElse("No pomodo");
        getBot().execute(new AnswerCallbackQuery(update.callbackQuery().id())
                .text(existance)
                .showAlert(false));
    }
}

package com.evgeniibaibakov.jinbot.bot.command.converter;

import com.evgeniibaibakov.jinbot.bot.keyboard.KeyboardFactory;
import com.evgeniibaibakov.jinbot.bot.processing.BotAwareCommand;
import com.evgeniibaibakov.jinbot.pomodoro.PomodoController;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class PomodoReply extends BotAwareCommand{

    @Resource
    private PomodoController pomodoController;

    @Resource
    private KeyboardFactory keyboardFactory;

    @Override
    public boolean isResponsibleForUpdate(Update update) {
        return Optional.ofNullable(update.message())
                .map(Message::text)
                .map(t -> t.equals("/pomodo"))
                .orElse(false);
    }

    @Override
    public void process(Update update) {
        String userId = update.message().from().id().toString();
        Long chatId = update.message().chat().id();

        SendMessage request = new SendMessage(chatId, "Pomodo for 1 min started").replyMarkup(
                keyboardFactory.getInlineKeyboard(new String[][]{{"5", "10", "15"}, {"20", "25", "30"}})
        );

        getBot().execute(request);
        pomodoController.createPomodo(userId, 1, afterPomodoNitidication(chatId));
    }

    private Runnable afterPomodoNitidication(Long chatId) {
        return () -> getBot().execute(new SendMessage(chatId, "Pomodo for 1 min ended"));
    }
}

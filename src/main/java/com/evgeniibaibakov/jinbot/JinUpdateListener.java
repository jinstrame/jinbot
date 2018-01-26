package com.evgeniibaibakov.jinbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.extern.log4j.Log4j;

import javax.annotation.Resource;
import java.util.List;

@Log4j
public class JinUpdateListener implements UpdatesListener {

    @Resource
    private TelegramBot bot;

    @Override
    public int process(List<Update> updates) {
        updates.stream()
                .map(u -> new SendMessage(u.message().chat().id(), "keyboard").replyMarkup(getInline(u.message().text())))
                .forEach(r -> bot.execute(r));

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    Keyboard getKeyboard(String text) {
        return new ReplyKeyboardMarkup(
                new String[]{text})
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);
    }

    Keyboard getInline(String text) {
        return new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{
                        new InlineKeyboardButton(text).callbackData(text + "1")
                }
        );
    }
}
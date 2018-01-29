package com.evgeniibaibakov.jinbot;

import com.evgeniibaibakov.jinbot.bot.processing.CommandProcessor;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.*;
import com.pengrad.telegrambot.request.BaseRequest;
import com.pengrad.telegrambot.response.BaseResponse;
import lombok.extern.log4j.Log4j;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;

@Log4j
public class JinUpdateListener implements UpdatesListener {

    @Resource
    private CommandProcessor commandProcessor;

    private ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

    @Override
    public int process(List<Update> updates) {
        updates.forEach(this::executeAsync);

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void executeAsync(Update update) {
        forkJoinPool.execute(() -> commandProcessor.process(update));
    }


//    Keyboard getKeyboard(String text) {
//        return new ReplyKeyboardMarkup(
//                new String[]{text})
//                .oneTimeKeyboard(true)
//                .resizeKeyboard(true)
//                .selective(true);
//    }
//
//    Keyboard getInline(String text) {
//        return new InlineKeyboardMarkup(
//                new InlineKeyboardButton[]{
//                        new InlineKeyboardButton(text).callbackData(text + "1")
//                }
//        );
//    }
}
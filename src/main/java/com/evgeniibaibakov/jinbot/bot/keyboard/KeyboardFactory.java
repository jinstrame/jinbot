package com.evgeniibaibakov.jinbot.bot.keyboard;

import com.pengrad.telegrambot.model.request.Keyboard;

public interface KeyboardFactory {

    Keyboard getInlineKeyboard(String[]... buttonLines);

    Keyboard getKeyboard(String[]... buttonLines);


}

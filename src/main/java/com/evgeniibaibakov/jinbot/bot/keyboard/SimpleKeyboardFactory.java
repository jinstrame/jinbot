package com.evgeniibaibakov.jinbot.bot.keyboard;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


@Component
public class SimpleKeyboardFactory implements KeyboardFactory {
    @Override
    public Keyboard getInlineKeyboard(String[]... buttonLines) {

        InlineKeyboardButton[][] inlineKeyboardButtons = new InlineKeyboardButton[buttonLines.length][];

        Stream.of(buttonLines)
                .map(this::mapArrayToButtonLine)
                .collect(toList())
                .toArray(inlineKeyboardButtons);

        return new InlineKeyboardMarkup(inlineKeyboardButtons);

}

    private InlineKeyboardButton[] mapArrayToButtonLine(String[] buttonsInLine) {
        InlineKeyboardButton[] inlineKeyboardButtons = new InlineKeyboardButton[buttonsInLine.length];

        Stream.of(buttonsInLine)
                .map(s -> new InlineKeyboardButton(s + s).callbackData(s))
                .collect(toList())
                .toArray(inlineKeyboardButtons);

        return inlineKeyboardButtons;
    }

    @Override
    public Keyboard getKeyboard( String[]... buttonLines) {
        return new ReplyKeyboardMarkup(
                buttonLines)
                .oneTimeKeyboard(true)
                .resizeKeyboard(true)
                .selective(true);
    }


}
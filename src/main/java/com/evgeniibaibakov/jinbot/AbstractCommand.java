package com.evgeniibaibakov.jinbot;

import com.pengrad.telegrambot.TelegramBot;

import javax.annotation.Resource;

public abstract class AbstractCommand {
    @Resource
    private TelegramBot bot;
}
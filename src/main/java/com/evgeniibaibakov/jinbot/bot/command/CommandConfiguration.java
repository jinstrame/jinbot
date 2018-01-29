package com.evgeniibaibakov.jinbot.bot.command;

import com.evgeniibaibakov.jinbot.bot.processing.Command;
import com.pengrad.telegrambot.request.BaseRequest;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Configuration
@Log4j
public class CommandConfiguration implements ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Bean
    List<Command> commands() {
        LinkedList<Command> commands = new LinkedList<>();

        converter("pomodoReply").ifPresent(commands::add);
        converter("pomodoStatusReply").ifPresent(commands::add);
        converter("defaultReply").ifPresent(commands::add);

        return commands;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    private Optional<Command> converter(String name) {
        try {
            return Optional.ofNullable(applicationContext.getBean(name))
                    .map(Command.class::cast);
        } catch (Exception e) {
            log.error(e);
        }
        return Optional.empty();
    }
}

package com.evgeniibaibakov.jinbot.pomodoro.impl;

import com.evgeniibaibakov.jinbot.pomodoro.Pomodo;
import com.evgeniibaibakov.jinbot.pomodoro.PomodoController;
import com.evgeniibaibakov.jinbot.pomodoro.PomodoRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


@Component
@Log4j
public class SimplePomodoController implements PomodoController {

    @Resource
    private PomodoRepository pomodoRepository;

    @Override
    public Pomodo createPomodo(String owner, int minutes, Runnable afterPomodo) {
        final Pomodo pomodo = new Pomodo(owner, minutes, afterPomodo);
        pomodoRepository.add(pomodo);
        return pomodo;
    }

    @Override
    public Optional<Pomodo> getPomodo(String owner) {
        return pomodoRepository.find(owner);
    }
}

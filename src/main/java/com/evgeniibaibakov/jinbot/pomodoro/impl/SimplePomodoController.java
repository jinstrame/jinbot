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

    private static int MILLIS_IN_MINUTE = 60 * 1000;

    @Resource
    private PomodoRepository pomodoRepository;

    @Override
    public Pomodo createPomodo(String owner, int minutes, Runnable afterPomodo) {
        final Pomodo pomodo = new Pomodo(owner, minutes);

        CompletableFuture.runAsync(() -> pomodoRepository.add(pomodo))
                .thenRunAsync(sleep(pomodo.getMinutes()))
                .thenRunAsync(afterPomodo)
                .thenRunAsync(() -> pomodoRepository.remove(pomodo.getOwner()));

        return pomodo;
    }

    @Override
    public Optional<Pomodo> getPomodo(String owner) {
        return pomodoRepository.find(owner);
    }

    private static Runnable sleep(int minutes) {
        return () -> {
            try {
                Thread.sleep(minutes * MILLIS_IN_MINUTE);
            } catch (Exception e) {
                log.error(e);
            }
        };
    }
}

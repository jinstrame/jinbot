package com.evgeniibaibakov.jinbot.pomodoro;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class PomodoScheduller {

    @Resource
    private PomodoRepository pomodoRepository;

    @Scheduled(fixedRate = 15000)
    public void findFinishedPomodos() {
        pomodoRepository.findAfter(new Date())
                .stream().parallel()
                .peek(pomodo -> pomodo.getAfterPomodo().run())
                .forEach(pomodo -> pomodoRepository.remove(pomodo));
    }

}

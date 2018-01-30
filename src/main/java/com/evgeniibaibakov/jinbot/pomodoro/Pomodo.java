package com.evgeniibaibakov.jinbot.pomodoro;

import lombok.Getter;

import java.util.Date;

@Getter
public class Pomodo {

    public static int MILLIS_IN_MINUTE = 60 * 1000;

    private final String owner;
    private final int minutes;
    private final long start;
    private final long end;

    private final Runnable afterPomodo;

    public Pomodo(String owner, int minutes, Runnable afterPomodo) {
        this.owner = owner;
        this.minutes = minutes;
        this.afterPomodo = afterPomodo;

        start = new Date().getTime();
        end = start + (minutes * MILLIS_IN_MINUTE);
    }
}

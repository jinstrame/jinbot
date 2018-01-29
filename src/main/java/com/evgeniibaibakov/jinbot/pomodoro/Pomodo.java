package com.evgeniibaibakov.jinbot.pomodoro;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
public class Pomodo {
    private String owner;
    private int minutes;
}

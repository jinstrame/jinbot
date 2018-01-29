package com.evgeniibaibakov.jinbot.pomodoro;

import java.util.Optional;

public interface PomodoController {
    Pomodo createPomodo(String owner, int minutes, Runnable afterPomodo);

    Optional<Pomodo> getPomodo(String owner);
}
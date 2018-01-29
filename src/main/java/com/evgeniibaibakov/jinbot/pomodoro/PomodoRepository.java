package com.evgeniibaibakov.jinbot.pomodoro;

import java.util.Optional;

public interface PomodoRepository {
    void add(Pomodo pomodo);

    Optional<Pomodo> find(String owner);

    Optional<Pomodo> remove(Pomodo pomodo);

    Optional<Pomodo> remove(String owner);
}

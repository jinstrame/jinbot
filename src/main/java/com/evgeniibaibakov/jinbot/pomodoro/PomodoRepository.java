package com.evgeniibaibakov.jinbot.pomodoro;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PomodoRepository {
    void add(Pomodo pomodo);

    Optional<Pomodo> find(String owner);

    Optional<Pomodo> remove(Pomodo pomodo);

    Optional<Pomodo> remove(String owner);

    List<Pomodo> findAfter(Date date);
}

package com.evgeniibaibakov.jinbot.pomodoro.impl;

import com.evgeniibaibakov.jinbot.pomodoro.Pomodo;
import com.evgeniibaibakov.jinbot.pomodoro.PomodoRepository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

@Component
public class SimplePomodoRepository implements PomodoRepository {

    private Map<String, Pomodo> pomodoStorage = new ConcurrentHashMap<>();

    @Override
    public void add(Pomodo pomodo) {
        pomodoStorage.put(pomodo.getOwner(), pomodo);
    }

    @Override
    public Optional<Pomodo> find(String owner) {
        return Optional.ofNullable(pomodoStorage.get(owner));
    }

    @Override
    public Optional<Pomodo> remove(Pomodo pomodo) {
        return remove(pomodo.getOwner());
    }

    @Override
    public Optional<Pomodo> remove(String owner) {
        return Optional.ofNullable(pomodoStorage.remove(owner));
    }

    @Override
    public List<Pomodo> findAfter(Date date) {
        long time = date.getTime();

        return pomodoStorage.entrySet().stream()
                .filter(e -> e.getValue().getEnd() < time)
                .map(Map.Entry::getValue)
                .collect(toList());
    }
}

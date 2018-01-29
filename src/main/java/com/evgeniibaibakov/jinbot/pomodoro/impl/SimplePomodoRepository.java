package com.evgeniibaibakov.jinbot.pomodoro.impl;

import com.evgeniibaibakov.jinbot.pomodoro.Pomodo;
import com.evgeniibaibakov.jinbot.pomodoro.PomodoRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot.bot.state;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import ru.p03.questbot.util.UpdateUtil;

/**
 *
 * @author altmf
 */
public class StateHolder {

    private boolean uniqueStates = true;

    Map<Integer, List<State>> states = new HashMap<>();

    public StateHolder() {

    }

    public void put(User user, State state) {
        if (states.containsKey(user.getId())) {
            if (uniqueStates) {
                states.get(user.getId()).removeIf((State t) -> {
                    return t.getAction().getName().equals(state.getAction().getName());
                });
            }
            states.get(user.getId()).add(state);

        } else {
            states.put(user.getId(), new ArrayList<>(Arrays.asList(state)));
        }
    }

    public List<State> get(User user) {
        return states.get(user.getId()) == null ? new ArrayList<>() : states.get(user.getId());
    }

    public List<State> remove(User user) {
        return states.remove(user.getId());
    }

    public State getLast(User user) {
        State s = null;
        if (states.containsKey(user.getId())) {
            if (!states.get(user.getId()).isEmpty()) {
                s = states.get(user.getId()).get(states.get(user.getId()).size() - 1);
            }
        }
        return s;
    }

    public State getFirst(User user) {
        State s = null;
        if (states.containsKey(user.getId()) && !states.get(user.getId()).isEmpty()) {
            s = states.get(user.getId()).get(0);
        }
        return s;
    }

    public void put(Update update, State state) {
        User u = getUserFromUpdate(update);
        put(u, state);
    }

    public List<State> get(Update update) {
        User u = getUserFromUpdate(update);
        return get(u);
    }

    public List<State> get(Update update, String actionName) {
        User u = getUserFromUpdate(update);
        List<State> sts = get(u);
        return sts.stream()
                .filter((s) -> s.getAction().getName().equals(actionName))
                .collect(Collectors.toList());
    }

    public State getLast(Update update, String actionName) {
        List<State> list = get(update, actionName);
        State s = null;
        if (!list.isEmpty()) {
            s = list.get(list.size() - 1);
        }
        return s;
    }

    public List<State> remove(Update update) {
        User u = getUserFromUpdate(update);
        return remove(u);
    }

    public State getLast(Update update) {
        User u = getUserFromUpdate(update);
        return getLast(u);
    }

    public State getFirst(Update update) {
        User u = getUserFromUpdate(update);
        return getFirst(u);
    }

    public boolean contains(User user, State... statelist) {
        boolean result = false;
        if (states.containsKey(user.getId())) {
            List<State> list = states.get(user.getId());
            result = list.containsAll(Arrays.asList(statelist));
        }
        return result;
    }

    public boolean contains(User user, String... statelist) {
        boolean result = false;
        if (states.containsKey(user.getId())) {
            List<State> list = states.get(user.getId());
            result = list.stream().map((s) -> s.getAction().getName())
                    .collect(Collectors.toList())
                    .containsAll(Arrays.asList(statelist));
        }
        return result;
    }

    public boolean contains(Update update, String... statelist) {
        User u = getUserFromUpdate(update);
        return contains(u, statelist);
    }

    private User getUserFromUpdate(Update update) {
        return UpdateUtil.getUserFromUpdate(update);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot.bot.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import ru.p03.questbot.util.UpdateUtil;

/**
 *
 * @author altmf
 */
public class QuestStateHolder{
    private Map<Integer, QuestEnumeration> questStates = new HashMap<>();   
    
    public QuestEnumeration get(User user) {
        return questStates.get(user.getId()) == null ? null : questStates.get(user.getId());
    }
    
    public QuestEnumeration get(Update update) {
        User u = UpdateUtil.getUserFromUpdate(update);
        return get(u);
    }
    
    public void put(Update update, QuestEnumeration questEnumeration) {
        User u = UpdateUtil.getUserFromUpdate(update);
        put(u, questEnumeration);
    }
    
    public void put(User user, QuestEnumeration questEnumeration) {
        questStates.put(user.getId(), questEnumeration);
    }

}

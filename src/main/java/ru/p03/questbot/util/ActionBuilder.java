/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot.util;

import org.telegram.telegrambots.api.objects.Update;
import ru.p03.questbot.bot.schema.Action;
import ru.p03.questbot.bot.document.spi.DocumentMarshaller;

/**
 *
 * @author altmf
 */
public class ActionBuilder {

    private final DocumentMarshaller marshaller;
    private Action action = new Action();

    public ActionBuilder(DocumentMarshaller marshaller) {
        this.marshaller = marshaller;
    }

    public ActionBuilder setName(String name) {
        action.setName(name);
        return this;
    }

    public ActionBuilder setValue(String name) {
        action.setValue(name);
        return this;
    }

    public String asString() {
        return marshaller.<Action>marshal(action);
    }

    public Action buld() {
        return action;
    }

    public Action buld(Update update) {
        String data = update.getCallbackQuery().getData();
        if (data == null) {
            return null;
        }

        action = marshaller.<Action>unmarshal(data);

        if (action == null) {
            return null;
        }
        return action;
    }
}

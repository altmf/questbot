/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot.util;

import org.telegram.telegrambots.api.objects.Update;
import ru.p03.questbot.bot.schema.Action;
import ru.p03.questbot.bot.document.spi.DocumentMarshalerAggregator;
import ru.p03.questbot.model.ClsDocType;

/**
 *
 * @author altmf
 */
public class ActionBuilder {

    private final DocumentMarshalerAggregator marshalFactory;
    private Action action = new Action();

    public ActionBuilder(DocumentMarshalerAggregator marshalFactory) {
        this.marshalFactory = marshalFactory;
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
        return marshalFactory.<Action>marshal(action, ClsDocType.ACTION);
    }

    public Action buld() {
        return action;
    }

    public Action buld(Update update) {
        String data = update.getCallbackQuery().getData();
        if (data == null) {
            return null;
        }

        action = marshalFactory.<Action>unmarshal(data, ClsDocType.ACTION);

        if (action == null) {
            return null;
        }
        return action;
    }
}

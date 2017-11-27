/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot.bot.state;

import ru.p03.questbot.bot.schema.Action;

/**
 *
 * @author altmf
 */
public class State {
    
    private Action action; 
    private Object value;

    public State(){
        
    }
    
    public State(Action action){
        this.action = action;
    }
    
    public State(Action action, Object value){
        this.action = action;
        this.value = value;
    }
    /**
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(Action action) {
        this.action = action;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }
}

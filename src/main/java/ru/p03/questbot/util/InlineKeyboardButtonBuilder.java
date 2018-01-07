/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.p03.questbot.util;

import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

/**
 *
 * @author timofeevan
 */
public class InlineKeyboardButtonBuilder {

    private final InlineKeyboardButton button;
    
    public InlineKeyboardButtonBuilder(){
        this.button = new InlineKeyboardButton();
    }

    
    public InlineKeyboardButtonBuilder setText(String text){
        button.setText(text);
        return this;
    }
    
    public InlineKeyboardButtonBuilder setCallbackData(String callbackData){
        button.setCallbackData(callbackData);
        return this;
    }
    
    public InlineKeyboardButton build(){
        return button;
    }
}

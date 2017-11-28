/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author timofeevan
 */
public class Bot extends TelegramLongPollingBot {

    private static final String TOKEN = "430612129:AAHP8Fb0rSsa4WhxW9mxmY-1WSoFZqQ3F24";
    private static final String USERNAME = "annaquestbot";

    public Bot() {
    }

    public Bot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            processCommand(update);
        } else if (update.hasInlineQuery()) {
        } else if (update.hasCallbackQuery()) {
            processCallbackQuery(update);
        }
    }

    private void processCallbackQuery(Update update) {
        List<SendMessage> answerMessage = null;
        //try {
            String data = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            if (data == null) {
                return;
            }

            answerMessage = AppEnv.getContext().getMenuManager().processCallbackQuery(update);
            
            if (answerMessage != null && answerMessage.isEmpty()) {  
//                for (SendMessage sendMessage : answerMessage) {
//                    execute(sendMessage);
//                }
                answerMessage.clear();
                //return;
            }

//        } catch (TelegramApiException ex) {
//            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void processCommand(Update update) {
        SendMessage answerMessage = null;
        try {
            String text = update.getMessage().getText();
//            if ("/start".equalsIgnoreCase(text)) {
//
//                answerMessage = new SendMessage();
//                
//                ReplyKeyboard rk = new ReplyKeyboardMarkup();
//
//                SendPhoto sendPhoto = new SendPhoto();
//                sendPhoto.setChatId(update.getMessage().getChatId());
//                sendPhoto.setNewPhoto(new File("C:\\Temp\\123.jpg"));
//                sendPhoto(sendPhoto);
//                
//                sendPhoto = new SendPhoto();
//                sendPhoto.setChatId(update.getMessage().getChatId());
//                sendPhoto.setNewPhoto(new File("C:\\Temp\\4.jpg"));
//                sendPhoto(sendPhoto);
//                
//                SendDocument sendDocument = new SendDocument();
//                //PhotoSize ps = new PhotoSize();
//                //ps.
//               // sendDocument.
//                sendDocument(sendDocument);
//
//
//            }
            answerMessage = AppEnv.getContext().getMenuManager().processCommand(update);
            if (answerMessage != null) { //U+1F60D \U0001F60D U+1F61C
                //String s = ;//Character.toString((char)0x1F60D); //\\U0001F60D
//                answerMessage.setText("<b>Привет, красотка" + smiling_face_with_heart_eyes + ", ты готова поиграть в увлекательную викторину?</b>"
//                        + " Будет легко и смешно: 26 шуточных вопросов с веселыми или красивыми фоточками, имеющими отношение ко всем тем, кто знает об этой викторине."
//                        + " Победителю викторины - приз!" + winking_face_with_tongue);
//                answerMessage.setParseMode("HTML");
//                answerMessage.setChatId(update.getMessage().getChatId());
                execute(answerMessage);
                return;
            }

        } catch (TelegramApiException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot;

import java.io.File;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiValidationException;

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
            //processInlineQuery(update);
        } else if (update.hasCallbackQuery()) {
            processCallbackQuery(update);
//            SendMessage answerMessage = new SendMessage();
//            answerMessage.setParseMode("HTML");
//            answerMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
            //processCallbackQuery(answerMessage, update);
        }
    }

    private void processCallbackQuery(Update update) {
        SendMessage answerMessage = null;
        try {
            String data = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            if (data == null) {
                return;
            }

//            answerMessage = AppEnv.getContext().getMenuManager().processCallbackQuery(update);
//            
//            if (answerMessage != null) {                 
//                answerMessage.setParseMode("HTML");
//                answerMessage.setChatId(chatId);
//                sendMessage(answerMessage);
//                return;
//            }
//            
//            answerMessage = AppEnv.getContext().getServiceManager().processCallbackQuery(update);
//            
//            if (answerMessage != null){               
//                answerMessage.setParseMode("HTML");
//                answerMessage.setChatId(chatId);
//                sendMessage(answerMessage);
//                return;
//            }
//            
//            answerMessage = AppEnv.getContext().getEmployeeManager().processCallbackQuery(update);
//            
//            if (answerMessage != null){               
//                answerMessage.setParseMode("HTML");
//                answerMessage.setChatId(chatId);
//                sendMessage(answerMessage);
//                return;
//            }
//            
//            ScheduleInfoManager sim = new ScheduleInfoManager(AppEnv.getContext().getClassifierRepository(),
//                    AppEnv.getContext().getRegScheduleRepository(),
//                    LocalDateTime.now(), AppEnv.getContext().getMarschaller(), 
//                    AppEnv.getContext().getStateHolder());
//            answerMessage = sim.processCallbackQuery(update);
            if (answerMessage != null) {
                answerMessage.setParseMode("HTML");
                answerMessage.setChatId(chatId);
                sendMessage(answerMessage);
                return;
            }

        } catch (TelegramApiException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processCommand(Update update) {
        SendMessage answerMessage = null;
        try {
            String text = update.getMessage().getText();
            if ("/start".equalsIgnoreCase(text)) {

                answerMessage = new SendMessage();
                
                ReplyKeyboard rk = new ReplyKeyboardMarkup();

                SendPhoto sendPhoto = new SendPhoto();
                sendPhoto.setChatId(update.getMessage().getChatId());
                sendPhoto.setNewPhoto(new File("C:\\Temp\\123.jpg"));
                sendPhoto(sendPhoto);
                
                sendPhoto = new SendPhoto();
                sendPhoto.setChatId(update.getMessage().getChatId());
                sendPhoto.setNewPhoto(new File("C:\\Temp\\4.jpg"));
                sendPhoto(sendPhoto);
                
                SendDocument sendDocument = new SendDocument();
                //PhotoSize ps = new PhotoSize();
                //ps.
               // sendDocument.
                sendDocument(sendDocument);

                answerMessage.setText("Здравствуйте, ");
                answerMessage.setParseMode("HTML");
                answerMessage.setChatId(update.getMessage().getChatId());
                sendMessage(answerMessage);

            }
//            answerMessage = AppEnv.getContext().getMenuManager().processCommand(update);
//            if (answerMessage != null) {
//                answerMessage.setText("<b>Нажмите на кнопку, чтобы начать запись</b>");
//                answerMessage.setParseMode("HTML");
//                answerMessage.setChatId(update.getMessage().getChatId());
//                sendMessage(answerMessage);
//                return;
//            }

        } catch (TelegramApiException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
//        SendMessage answerMessage = new SendMessage();
//        answerMessage.setParseMode("HTML");
//        answerMessage.setChatId(update.getMessage().getChatId());
//        String text = update.getMessage().getText();
//        try {
//            if (update.getMessage().isCommand()) {
//                User from = update.getMessage().getFrom();
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, update.getMessage().getText() + " " + from.getFirstName() + " " + from.getLastName() + "   " + new Date());
//                if ("/hello".equalsIgnoreCase(text)) {
//                    String answer = "Привет, " + from.getFirstName() + " " + from.getLastName() + "!";
//                    answerMessage.setText(answer);
//                    sendMessage(answerMessage); // Call method to send the message
//                } else if ("/start".equalsIgnoreCase(text)) {
//                    answerMessage.setText("<b>Команды:</b>\n"
//                            + "/hello -  Поздороваться");
//                    sendMessage(answerMessage); // Call method to send the message
//                } else if (text != null && (text.toLowerCase().startsWith("/report") || text.toLowerCase().startsWith("/отчет") || text.toLowerCase().startsWith("/отчёт"))) {
//                    processReport(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/map") || text.toLowerCase().startsWith("/карта"))) {
//                    processMap(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/tab"))) {
//                    processTab(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/cuvp"))) {
//                    processCuvp(answerMessage, update, update.getMessage().getChatId());
//                }
//            } else {
//                answerMessage.setText("Пока я обрабатываю только команды. Команды начинаются с символа /");
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, update.getMessage().getText());
//                sendMessage(answerMessage); // Call method to send the message
//            }
//
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        SendMessage answerMessage = new SendMessage();
//        answerMessage.setParseMode("HTML");
//        answerMessage.setChatId(update.getMessage().getChatId());
//        String text = update.getMessage().getText();
//        try {
//            if (update.getMessage().isCommand()) {
//                User from = update.getMessage().getFrom();
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, update.getMessage().getText() + " " + from.getFirstName() + " " + from.getLastName() + "   " + new Date());
//                if ("/hello".equalsIgnoreCase(text)) {
//                    String answer = "Привет, " + from.getFirstName() + " " + from.getLastName() + "!";
//                    answerMessage.setText(answer);
//                    sendMessage(answerMessage); // Call method to send the message
//                } else if ("/start".equalsIgnoreCase(text)) {
//                    answerMessage.setText("<b>Команды:</b>\n"
//                            + "/hello -  Поздороваться");
//                    sendMessage(answerMessage); // Call method to send the message
//                } else if (text != null && (text.toLowerCase().startsWith("/report") || text.toLowerCase().startsWith("/отчет") || text.toLowerCase().startsWith("/отчёт"))) {
//                    processReport(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/map") || text.toLowerCase().startsWith("/карта"))) {
//                    processMap(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/tab"))) {
//                    processTab(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/cuvp"))) {
//                    processCuvp(answerMessage, update, update.getMessage().getChatId());
//                }
//            } else {
//                answerMessage.setText("Пока я обрабатываю только команды. Команды начинаются с символа /");
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, update.getMessage().getText());
//                sendMessage(answerMessage); // Call method to send the message
//            }
//
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        SendMessage answerMessage = new SendMessage();
//        answerMessage.setParseMode("HTML");
//        answerMessage.setChatId(update.getMessage().getChatId());
//        String text = update.getMessage().getText();
//        try {
//            if (update.getMessage().isCommand()) {
//                User from = update.getMessage().getFrom();
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, update.getMessage().getText() + " " + from.getFirstName() + " " + from.getLastName() + "   " + new Date());
//                if ("/hello".equalsIgnoreCase(text)) {
//                    String answer = "Привет, " + from.getFirstName() + " " + from.getLastName() + "!";
//                    answerMessage.setText(answer);
//                    sendMessage(answerMessage); // Call method to send the message
//                } else if ("/start".equalsIgnoreCase(text)) {
//                    answerMessage.setText("<b>Команды:</b>\n"
//                            + "/hello -  Поздороваться");
//                    sendMessage(answerMessage); // Call method to send the message
//                } else if (text != null && (text.toLowerCase().startsWith("/report") || text.toLowerCase().startsWith("/отчет") || text.toLowerCase().startsWith("/отчёт"))) {
//                    processReport(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/map") || text.toLowerCase().startsWith("/карта"))) {
//                    processMap(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/tab"))) {
//                    processTab(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/cuvp"))) {
//                    processCuvp(answerMessage, update, update.getMessage().getChatId());
//                }
//            } else {
//                answerMessage.setText("Пока я обрабатываю только команды. Команды начинаются с символа /");
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, update.getMessage().getText());
//                sendMessage(answerMessage); // Call method to send the message
//            }
//
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        SendMessage answerMessage = new SendMessage();
//        answerMessage.setParseMode("HTML");
//        answerMessage.setChatId(update.getMessage().getChatId());
//        String text = update.getMessage().getText();
//        try {
//            if (update.getMessage().isCommand()) {
//                User from = update.getMessage().getFrom();
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, update.getMessage().getText() + " " + from.getFirstName() + " " + from.getLastName() + "   " + new Date());
//                if ("/hello".equalsIgnoreCase(text)) {
//                    String answer = "Привет, " + from.getFirstName() + " " + from.getLastName() + "!";
//                    answerMessage.setText(answer);
//                    sendMessage(answerMessage); // Call method to send the message
//                } else if ("/start".equalsIgnoreCase(text)) {
//                    answerMessage.setText("<b>Команды:</b>\n"
//                            + "/hello -  Поздороваться");
//                    sendMessage(answerMessage); // Call method to send the message
//                } else if (text != null && (text.toLowerCase().startsWith("/report") || text.toLowerCase().startsWith("/отчет") || text.toLowerCase().startsWith("/отчёт"))) {
//                    processReport(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/map") || text.toLowerCase().startsWith("/карта"))) {
//                    processMap(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/tab"))) {
//                    processTab(answerMessage, update, update.getMessage().getChatId());
//                }else if (text != null && (text.toLowerCase().startsWith("/cuvp"))) {
//                    processCuvp(answerMessage, update, update.getMessage().getChatId());
//                }
//            } else {
//                answerMessage.setText("Пока я обрабатываю только команды. Команды начинаются с символа /");
//                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, update.getMessage().getText());
//                sendMessage(answerMessage); // Call method to send the message
//            }
//
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

/**
 *
 * @author timofeevan
 */
public class Main {

    static {
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

    public static void main(String[] args) {

        java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, "Бот. Начало работы");

        if (args.length > 0) {
            AppEnv.getContext(args[0].replaceFirst("-", ""));//.init(args[0]);
        } else {
            AppEnv.getContext();//.init();
        }

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        Runnable r = () -> {
            Bot bot = null;
            HttpHost proxy = AppEnv.getContext().getProxy();
            if (proxy == null) {
                bot = new Bot();
            } else {
                DefaultBotOptions instance = ApiContext
                        .getInstance(DefaultBotOptions.class);
                RequestConfig rc = RequestConfig.custom()
                        .setProxy(proxy).build();
                instance.setRequestConfig(rc);
                bot = new Bot(instance);
            }

            try {
                botsApi.registerBot(bot);
                bot.setClassifierRepository(AppEnv.getContext().getClassifierRepository());
                bot.setMarshaller(AppEnv.getContext().getMarschaller());
                bot.setQuestStateHolder(AppEnv.getContext().getQuestStateHolder());
                //AppEnv.getContext().getMenuManager().setBot(bot);
            } catch (TelegramApiRequestException ex) {
                Logger.getLogger(Main.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        };

        new Thread(r).start();//r.run();

        while (true) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "bot!");
            try {
                Thread.sleep(80000L);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }

    }
}

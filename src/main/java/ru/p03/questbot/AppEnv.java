/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpHost;
import ru.p03.common.util.QueriesEngine;
import ru.p03.questbot.bot.document.spi.DocumentMarshaller;
import ru.p03.questbot.bot.document.spi.JsonDocumentMarshallerImpl;
import ru.p03.questbot.bot.schema.Action;
import ru.p03.questbot.bot.state.QuestStateHolder;
import ru.p03.questbot.model.ClsDocType;
import ru.p03.questbot.model.repository.ClassifierRepository;
import ru.p03.questbot.model.repository.ClassifierRepositoryImpl;

/**
 *
 * @author timofeevan
 */
public class AppEnv {

    public static String ROOT_PATH = "ROOT_PATH";
    public static String PROXY_HOST = "PROXY_HOST";
    public static String PROXY_PORT = "PROXY_PORT";
    public static String PROXY_USE = "PROXY_USE";
    public static String SERVICE_FILE = "SERVICE_FILE";
    public static String EMPLOYEE_FILE = "EMPLOYEE_FILE";

    //    PROPERTIES:
    //    ROOT_PATH=absolute path
    //    PROXY_USE=false
    //    TOKEN=bot token
    //    USERNAME=bot user name
    private Map environments = new HashMap();

    private static AppEnv CONTEXT;

    private DocumentMarshaller marshaller = new JsonDocumentMarshallerImpl(Action.class, ClsDocType.ACTION);

    private ClassifierRepository classifierRepository = new ClassifierRepositoryImpl();


    //private MenuManager menuManager;
    //private StateHolder stateHolder;
    private QuestStateHolder questStateHolder;

    private AppEnv() {

    }

//    private void initMarschaller() {
//        DocumentMarshaller mrsh2 = new JsonDocumentMarshallerImpl(Action.class, ClsDocType.ACTION);
//        marshaller.setMarshallers(Arrays.asList(mrsh2));//, mrsh3, mrsh4));
//        marshaller.init();
//    }

    private void initManagers() {
        questStateHolder =  new QuestStateHolder();
        //menuManager = new MenuManager(classifierRepository, marshaller, questStateHolder);

    }

    private Properties initProperties(String propFileName) {
        Properties properties = null;
        File fProp = null;
        String propDir = null;
        if (propFileName == null) {
            propFileName = "conf.properties";
            propDir = "conf";

            fProp = new File(propDir + File.separator + propFileName);
        } else {
            fProp = new File(propFileName);
        }

        if (!fProp.exists()) {
            Logger.getLogger(AppEnv.class.getName()).log(Level.SEVERE, "not exists: " + fProp.getAbsolutePath());
            fProp = new File(".." + "/" + propDir + "/" + propFileName);
            if (!fProp.exists()) {
                Logger.getLogger(AppEnv.class.getName()).log(Level.SEVERE, "not exists: " + fProp.getAbsolutePath());
                fProp = new File("...." + "/" + propDir + "/" + propFileName);
                if (!fProp.exists()) {
                    Logger.getLogger(AppEnv.class.getName()).log(Level.SEVERE, "not exists: " + fProp.getAbsolutePath());
                    fProp = new File(propFileName);
                    Logger.getLogger(AppEnv.class.getName()).log(Level.SEVERE, "default: " + fProp.getAbsolutePath());
                }
            }
        }
        if (fProp.exists()) {
            try (InputStream fis = new FileInputStream(fProp);) {
                properties = new Properties();
                properties.load(new InputStreamReader(fis, Charset.forName("UTF-8")));
                for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                    Logger.getLogger(AppEnv.class.getName()).log(Level.SEVERE, entry.getKey() + " = " + entry.getValue());
                }
                environments.putAll(properties);
                Bot.TOKEN = properties.getProperty("TOKEN");
                Bot.USERNAME = properties.getProperty("USERNAME");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AppEnv.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AppEnv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return properties;
    }

    public ClassifierRepository getClassifierRepository() {
        return classifierRepository;
    }

//    public MenuManager getMenuManager() {
//        return menuManager;
//    }
//
    public DocumentMarshaller getMarschaller() {
        return marshaller;
    }


    public void init(String propFileName) {
        initProperties(propFileName);
        //initMarschaller();
        initManagers();
    }

    public void init() {
        initProperties(null);

        String db = "db";
        String dbUrl = "jdbc:h2:" + getRootPath() + File.separator + db + File.separator + "QUEB;AUTO_SERVER=TRUE"; //<property name=\"javax.persistence.jdbc.url\" value=\
        Map hm = new HashMap();
        hm.put("javax.persistence.jdbc.url", dbUrl);

        QueriesEngine dao = QueriesEngine.instance("QUE", hm);

        ((ClassifierRepositoryImpl) getClassifierRepository()).setDAO(dao);

        //stateHolder = new StateHolder();

        //initMarschaller();
        initManagers();
    }

    public static AppEnv getContext(String propFileName) { //https://habrahabr.ru/post/129494/
        if (CONTEXT == null) {
            CONTEXT = new AppEnv();
            CONTEXT.init(propFileName);
        }
        return CONTEXT;
    }

    public static AppEnv getContext() { //https://habrahabr.ru/post/129494/
        if (CONTEXT == null) {
            CONTEXT = new AppEnv();
            CONTEXT.init();
        }
        return CONTEXT;
    }

    public String getRootPath() {
        return (String) environments.get(ROOT_PATH);
    }

    public HttpHost getProxy() {
        if (environments.get(PROXY_HOST) != null && environments.get(PROXY_PORT) != null
                && environments.get(PROXY_USE) != null && "true".equalsIgnoreCase((String) environments.get(PROXY_USE))) {
            try {
                HttpHost proxy = new HttpHost((String) environments.get(PROXY_HOST), Integer.valueOf((String) environments.get(PROXY_PORT)));
                return proxy;
            } catch (NumberFormatException ex) {
                Logger.getLogger(AppEnv.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    /**
     * @return the questStateHolder
     */
    public QuestStateHolder getQuestStateHolder() {
        return questStateHolder;
    }
}

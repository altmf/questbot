/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.questbot;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.p03.questbot.AppEnv;
import ru.p03.questbot.model.ClsAnswer;
import ru.p03.questbot.model.ClsQuest;
import ru.p03.questbot.model.ClsQuestPhoto;

/**
 *
 * @author altmf
 */
public class RepoTest {

    private Logger log = Logger.getLogger(RepoTest.class.getName());

    public RepoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        AppEnv.getContext();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testClsQuest() {
        ClsQuest find = AppEnv.getContext().getClassifierRepository().find(ClsQuest.class, 1L);
        log.log(Level.SEVERE, "QUEST = " + find.getQuestText());
        find.getClsAnswerCollection().stream()
                .map((a) -> a.getAnswerText())
                .forEach(System.out::println);
        
        find.getClsQuestPhotoCollection().stream()
                .map((a) -> a.getRelFilePath())
                .forEach(System.out::println);
    }
    
    @Test
    public void testAnswer() {
        ClsAnswer find = AppEnv.getContext().getClassifierRepository().find(ClsAnswer.class, 1L);
        log.log(Level.SEVERE, "ANSWER = " + find.getAnswerText());
    }
    
    @Test
    public void testPhoto() {
        ClsQuestPhoto find = AppEnv.getContext().getClassifierRepository().find(ClsQuestPhoto.class, 1L);
        log.log(Level.SEVERE, "PHOTO = " + find.getRelFilePath());
    }
}

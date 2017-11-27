package ru.p03.questbot.bot.document.spi;

/**
 * Created by oleg.gorlachev on 29.12.2015.
 */
public interface DocumentAdapter<M, S> {
    S getSource();
    M getModel();
}

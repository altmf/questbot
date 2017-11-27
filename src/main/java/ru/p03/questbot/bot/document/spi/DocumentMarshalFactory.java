package ru.p03.questbot.bot.document.spi;

/**
 * Created by oleg.gorlachev on 29.12.2015.
 */
public interface DocumentMarshalFactory {
    <T> String marshal(T document, Long docType);

    <T> String marshal(T document, String docTypeCode);

    <T> T unmarshal(String xml, Long docType);

    <T> T unmarshal(String xml, String docTypeCode);
}

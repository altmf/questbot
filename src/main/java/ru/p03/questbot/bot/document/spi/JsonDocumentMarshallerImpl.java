package ru.p03.questbot.bot.document.spi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonDocumentMarshallerImpl implements DocumentMarshaller {

    private static final Logger log = Logger.getLogger(JsonDocumentMarshallerImpl.class.getSimpleName());
    private JAXBContext context;
    private String docTypeCode;
    private Class<?> clazz;

    public JsonDocumentMarshallerImpl() {
    }

    public JsonDocumentMarshallerImpl(Class clazz, String docTypeCode) {
        this.setDocTypeCode(docTypeCode);
        this.setClazz(clazz);
        init();
    }

    public void init() {
        log.info("got associated class : " + getClazz() + ", docTypeCode == " + getDocTypeCode());
        try {
            this.setContext(JAXBContext.newInstance(getClazz()));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean supports(String docTypeCode) {
        return this.getDocTypeCode().equals(docTypeCode);
    }

    /**
     * Marshalls a document.
     *
     * @param document a document to marshal
     * @return an XMl represenation of the document
     */
    @Override
    public <T> String marshal(T document) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String v = objectMapper.writeValueAsString(document);
            //writeValueAsString(a, Action.class);
            return v;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(JsonDocumentMarshallerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public <T> T unmarshal(String xml, Date operationDate) {
        return unmarshal(xml);
    }

    /**
     * Unmarshalls a document.
     *
     * @param xml an XMl represenation of the document
     * @return {@link Document}
     */
    @Override
    public <T> T unmarshal(String xml) {
        return unmarshal(xml, this.clazz);
    }

    @Override
    public <T> T unmarshal(String xml, Class clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        T s = null;
        try {
            s = (T) objectMapper.readValue(xml, clazz);
        } catch (IOException ex) {
            Logger.getLogger(JsonDocumentMarshallerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public JAXBContext getContext() {
        return context;
    }

    public void setContext(JAXBContext context) {
        this.context = context;
    }

    public String getDocTypeCode() {
        return docTypeCode;
    }

    public void setDocTypeCode(String docTypeCode) {
        this.docTypeCode = docTypeCode;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

}

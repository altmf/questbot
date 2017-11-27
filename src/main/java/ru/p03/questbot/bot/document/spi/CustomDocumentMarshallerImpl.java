package ru.p03.questbot.bot.document.spi;

import ru.p03.common.util.JaxbUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.bind.util.ValidationEventCollector;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomDocumentMarshallerImpl implements DocumentMarshaller {

    private static final Logger log = Logger.getLogger(CustomDocumentMarshallerImpl.class.getSimpleName());
    private JAXBContext context;
    private String docTypeCode;
    private Class<?> clazz;

    public CustomDocumentMarshallerImpl() {
    }

    public CustomDocumentMarshallerImpl(Class clazz, String docTypeCode) {
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
        if (document instanceof String) {
            return new JaxbUtil().marshal((String) document, createMarshaller());
        }
        return new JaxbUtil().marshal(document, createMarshaller());
    }

    private Marshaller createMarshaller() {
        try {
            final Marshaller marshaller = getContext().createMarshaller();
            marshaller.setEventHandler(new DefaultValidationEventHandler());
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            return marshaller;
        } catch (JAXBException e) {
            log.log(Level.SEVERE, "Unable to Create Marshaller.", e);
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
        return new JaxbUtil().unmarshal(xml, createUnmarshaller());
    }
    
    @Override
    public <T> T unmarshal(String xml, Class clazz) {    
        return new JaxbUtil().unmarshal(xml, createUnmarshaller());
    }

    private Unmarshaller createUnmarshaller() {
        try {
            Unmarshaller unmarshaller = getContext().createUnmarshaller();
            unmarshaller.setEventHandler(new ValidationEventCollector());
            return unmarshaller;
        } catch (JAXBException e) {
            log.log(Level.SEVERE, "Unable to Create UnMarshaller.", e);
        }
        return null;
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

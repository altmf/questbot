/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.p03.common.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author timofeevan
 */
public class JaxbUtil {

    private static final Logger log = Logger.getLogger(JaxbUtil.class.getSimpleName());

    public <T> String marshal(T obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            return marshal(obj, marshaller);
        } catch (JAXBException e) {
            log.log(Level.SEVERE, "Marshaling failed.", e);
//			throw new RuntimeException("Marshaling failed.", e);
        }
        return null;
    }

    public <T> String marshal(T obj, Marshaller marshaller) {
        if (marshaller != null) {
            try {
                StringWriter writer = new StringWriter();
                marshaller.marshal(obj, writer);
                return writer.toString();
            } catch (JAXBException e) {
                log.log(Level.SEVERE, "Marshaling failed.", e);
//            throw new RuntimeException("Marshaling failed.", e);
            }
        }
        return null;
    }

    public <T> T unmarshal(String xml, Unmarshaller unmarshaller) {
        if (unmarshaller != null) {
            try {
                return (T) unmarshaller.unmarshal(new StringReader(xml));
            } catch (JAXBException e) {
                log.log(Level.SEVERE, "Unmarshaling failed.", e);
            }
        }
        return null;
    }
}

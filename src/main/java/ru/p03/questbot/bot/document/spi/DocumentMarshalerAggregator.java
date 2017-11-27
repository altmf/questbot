package ru.p03.questbot.bot.document.spi;

import java.util.ArrayList;
import java.util.List;
import ru.p03.questbot.model.ClsDocType;


/**
 *
 * @author Pavel.Piskun
 */
public class DocumentMarshalerAggregator implements DocumentMarshalFactory {

    private List<DocumentMarshaller> marshallers = new ArrayList<>();
    private List<ClsDocType> types;
  

    public void init() {
        setTypes(ClsDocType.types());
    }

    public <T> String marshal(T document, Long docType) {
        return getMarshaller(docType).marshal(document);
    }

    public <T> String marshal(T document, String docTypeCode) {
        return getMarshaller(docTypeCode).marshal(document);
    }

    public <T> T unmarshal(String xml, Long docType) {
        return getMarshaller(docType).unmarshal(xml);
    }

    public <T> T unmarshal(String xml, String docTypeCode) {
//        if (ClsDocType.ACTION.equals(docTypeCode)){
//            return getMarshaller(docTypeCode).unmarshal(xml, Action.class);
//        }
//        if (ClsDocType.MAN.equals(docTypeCode)){
//            return getMarshaller(docTypeCode).unmarshal(xml, Man.class);
//        }
        return getMarshaller(docTypeCode).unmarshal(xml);
    }

    private DocumentMarshaller getMarshaller(Long docType) {
        String documentTypeCode = getDocumentTypeCode(docType);
        return getMarshaller(documentTypeCode);
    }

    private DocumentMarshaller getMarshaller(String docTypeCode) {
        for (DocumentMarshaller marshaller : getMarshallers()) {
            if (marshaller.supports(docTypeCode)) {
                return marshaller;
            }
        }
        throw new RuntimeException("Incompatible document type: " + docTypeCode);
    }

    private String getDocumentTypeCode(Long documentType) {
        for (ClsDocType docType : getTypes()) {
            if (docType.getCode() != null && docType.getId().equals(documentType)) {
                return docType.getCode();
            }
        }
        throw new RuntimeException("Cannot find code for type " + documentType
                + " Check code in NOTAR document type classifier.");
    }

    public List<DocumentMarshaller> getMarshallers() {
        return marshallers;
    }

    public void setMarshallers(List<DocumentMarshaller> marshallers) {
        this.marshallers = marshallers;
    }

    public List<ClsDocType> getTypes() {
        return types;
    }

    public void setTypes(List<ClsDocType> types) {
        this.types = types;
    }

}

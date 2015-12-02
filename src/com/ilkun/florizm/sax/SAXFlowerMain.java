package com.ilkun.florizm.sax;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexander-ilkun
 */
public class SAXFlowerMain {

    public static void main(String[] args) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SAXFlowerHandler fh = new SAXFlowerHandler();
            reader.setContentHandler(fh);
            if (fh != null) {
                reader.parse("xml/FlowerXML.xml");
                System.out.println(fh.getFlowers());
            }
        } catch (SAXException | IOException ex) {
            Logger.getLogger(SAXFlowerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

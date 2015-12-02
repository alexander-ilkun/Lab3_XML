package com.ilkun.florizm.validation;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.SAXException;
import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 *
 * @author alexander-ilkun
 */
public class XSDValidation {

    public static void main(String[] args) {
        String filename = "xml/FlowerXML.xml";
        DOMParser parser = new DOMParser();
        try {
            parser.setErrorHandler(new FlowerErrorHandler("log.txt"));
            parser.setFeature(
                    "http://xml.org/sax/features/validation", true);
            parser.setFeature(
                    "http://apache.org/xml/features/validation/schema", true);
            parser.parse(filename);
        } catch (IOException ex) {
            Logger.getLogger(XSDValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXNotRecognizedException ex) {
            Logger.getLogger(XSDValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXNotSupportedException ex) {
            Logger.getLogger(XSDValidation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XSDValidation.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(filename + " checked");
    }
}

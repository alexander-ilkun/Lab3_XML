package com.ilkun.florizm.transformation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author alexander-ilkun
 */
public class Transform {

    public static void main(String[] args) {
        String xmlPath = "xml/FlowerXML.xml";
        String xslPath = "xml/FlowerXSL.xsl";
        String newXMLPath = "xml/newXML.html";

        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();

            Source xmlDoc = new StreamSource(xmlPath);
            Source xslDoc = new StreamSource(xslPath);
            OutputStream htmlFile = new FileOutputStream(newXMLPath);

            Transformer transformer = tFactory.newTransformer(xslDoc);
            transformer.transform(xmlDoc, new StreamResult(htmlFile));
            System.out.println("XML to XML transform is completed.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Transform.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Transform.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Transform.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

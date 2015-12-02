package com.ilkun.florizm.dom;

import com.ilkun.florizm.entity.ConFlower;
import java.util.ArrayList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author alexander-ilkun
 */
public class DOMFlowerMain {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbf
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse("xml/FlowerXML.xml");
            Element root = document.getDocumentElement();
            ArrayList<ConFlower> flowers = DOMFlowerHandler.listBuilder(root);
            System.out.println(flowers);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(DOMFlowerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

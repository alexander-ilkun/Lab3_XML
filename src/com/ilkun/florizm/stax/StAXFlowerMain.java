package com.ilkun.florizm.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexander-ilkun
 */
public class StAXFlowerMain {

    public static void main(String[] args) {
        try {
            StAXFlowerHandler parser = new StAXFlowerHandler();
            InputStream input;
            input = new FileInputStream("xml/FlowerXML.xml");
            parser.parse(input);
            Collections.sort(parser.getFlowers());
            System.out.println(parser.getFlowers());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StAXFlowerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

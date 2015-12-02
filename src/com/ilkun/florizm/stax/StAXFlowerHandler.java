package com.ilkun.florizm.stax;

import com.ilkun.florizm.entity.Mult;
import com.ilkun.florizm.entity.FullColor;
import com.ilkun.florizm.entity.ConFlower;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author alexander-ilkun
 */
public class StAXFlowerHandler {

    private final ArrayList<ConFlower> flowers = new ArrayList<>();
    private String curPar;
    private ConFlower curr = null;
    private FullColor fColor;

    public ArrayList<ConFlower> getFlowers() {
        return flowers;
    }

    public void parse(InputStream input) {
        XMLInputFactory inputFactory
                = XMLInputFactory.newInstance();
        try {
            XMLStreamReader reader
                    = inputFactory.createXMLStreamReader(input);
            process(reader);
        } catch (XMLStreamException ex) {
            Logger.getLogger(StAXFlowerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void process(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    curPar = reader.getLocalName();
                    switch (curPar) {
                        case "concreteFlower":
                            curr = new ConFlower();
                            curr.setName(reader.getAttributeValue(0));
                            curr.setQuantity(Integer.parseInt(reader.getAttributeValue(1)));
                            break;
                        case "stem":
                            fColor = new FullColor();
                            break;
                        case "leaf":
                            fColor = new FullColor();
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    curPar = reader.getLocalName();
                    switch (curPar) {
                        case "concreteFlower":
                            flowers.add(curr);
                            break;
                        case "stem":
                            curr.getVisualParameters().setStem(fColor);
                            break;
                        case "leaf":
                            curr.getVisualParameters().setLeaf(fColor);
                            break;
                        default:
                            break;
                    }
                    curPar = "";
                    break;
                case XMLStreamConstants.CHARACTERS:
                    switch (curPar) {
                        case "soil":
                            curr.setSoil(reader.getText());
                            break;
                        case "origin":
                            curr.setOrigin(reader.getText());
                            break;
                        case "r":
                            fColor.setR(Integer.parseInt(reader.getText()));
                            break;
                        case "g":
                            fColor.setG(Integer.parseInt(reader.getText()));
                            break;
                        case "b":
                            fColor.setB(Integer.parseInt(reader.getText()));
                            break;
                        case "size":
                            curr.getVisualParameters().setSize(
                                    Float.parseFloat(reader.getText()));
                            break;
                        case "temperature":
                            curr.getGrowingTips().setTemperature(
                                    Float.parseFloat(reader.getText()));
                            break;
                        case "photophilous":
                            curr.getGrowingTips().setPhotophilous(reader.getText());
                            break;
                        case "watering":
                            curr.getGrowingTips().setWatering(
                                    new BigInteger(reader.getText()));
                            break;
                        case "multiplying":
                            curr.setMultiplying(Mult.fromValue(reader.getText()));
                            break;
                        default:
                            break;
                    }
                    curPar = "";
                    break;
                default:
                    break;
            }
        }
    }
}

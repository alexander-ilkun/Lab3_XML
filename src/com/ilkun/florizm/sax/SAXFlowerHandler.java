package com.ilkun.florizm.sax;

import com.ilkun.florizm.entity.Mult;
import com.ilkun.florizm.entity.FullColor;
import com.ilkun.florizm.entity.ConFlower;
import java.math.BigInteger;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author alexander-ilkun
 */
public class SAXFlowerHandler extends DefaultHandler {

    private final ArrayList<ConFlower> flowers = new ArrayList<>();
    private ConFlower curr = null;
    private String curPar;
    private FullColor fColor;

    public ArrayList<ConFlower> getFlowers() {
        return flowers;
    }

    @Override
    public void startDocument() {
        System.out.println("parsing started");
    }

    @Override
    public void endDocument() {
        System.out.println("parsing ended");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (localName.equals("concreteFlower")) {
            curr = new ConFlower();
            curr.setName(attrs.getValue(0));
            curr.setQuantity(Integer.parseInt(attrs.getValue(1)));
        }

        if ("soil".equals(localName)
                || "origin".equals(localName)
                || "multiplying".equals(localName)
                || "size".equals(localName)
                || "r".equals(localName)
                || "g".equals(localName)
                || "b".equals(localName)
                || "temperature".equals(localName)
                || "photophilous".equals(localName)
                || "watering".equals(localName)) {
            curPar = localName;
        }

        if ("stem".equals(localName)
                || "leaf".equals(localName)) {
            fColor = new FullColor();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (localName.equals("concreteFlower")) {
            flowers.add(curr);
        }

        if (localName.equals("stem")) {
            curr.getVisualParameters().setStem(fColor);
        }
        if (localName.equals("leaf")) {
            curr.getVisualParameters().setLeaf(fColor);
        }

        curPar = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();

        if (curPar == null) {
            return;
        }

        switch (curPar) {
            case "soil":
                curr.setSoil(s);
                break;
            case "origin":
                curr.setOrigin(s);
                break;
            case "multiplying":
                curr.setMultiplying(Mult.fromValue(s));
                break;
            case "r":
                fColor.setR(Integer.parseInt(s));
                break;
            case "g":
                fColor.setG(Integer.parseInt(s));
                break;
            case "b":
                fColor.setB(Integer.parseInt(s));
                break;
            case "size":
                curr.getVisualParameters().setSize(Float.parseFloat(s));
                break;
            case "temperature":
                curr.getGrowingTips().setTemperature(Float.parseFloat(s));
                break;
            case "photophilous":
                curr.getGrowingTips().setPhotophilous(s);
                break;
            case "watering":
                curr.getGrowingTips().setWatering(new BigInteger(s));
                break;
            default:
                break;
        }
    }
}

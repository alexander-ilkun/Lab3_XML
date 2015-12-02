package com.ilkun.florizm.dom;

import com.ilkun.florizm.entity.Mult;
import com.ilkun.florizm.entity.FullColor;
import com.ilkun.florizm.entity.ConFlower;
import java.util.ArrayList;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.math.BigInteger;

/**
 *
 * @author alexander-ilkun
 */
public class DOMFlowerHandler {

    public static ArrayList<ConFlower> listBuilder(Element root) throws SAXException, IOException {
        ArrayList<ConFlower> flowers = new ArrayList<>();
        NodeList flowersNodes = root.getElementsByTagName("f:concreteFlower");
        ConFlower flower = null;
        for (int i = 0; i < flowersNodes.getLength(); i++) {
            flower = new ConFlower();
            Element flowerElement = (Element) flowersNodes.item(i);
            flower.setName(flowerElement.getAttribute("name"));
            flower.setQuantity(Integer.parseInt(flowerElement.getAttribute("quantity")));
            flower.setSoil(getBabyValue(flowerElement, "cf:soil"));
            flower.setOrigin(getBabyValue(flowerElement, "cf:origin"));

            Element VisParElement = getBaby(flowerElement, "cf:visualParameters");
            FullColor fColor = new FullColor();

            Element StemElement = getBaby(VisParElement, "vp:stem");
            fColor.setR(
                    Integer.parseInt(getBabyValue(StemElement, "vp:r")));
            fColor.setG(
                    Integer.parseInt(getBabyValue(StemElement, "vp:g")));
            fColor.setB(
                    Integer.parseInt(getBabyValue(StemElement, "vp:b")));
            flower.getVisualParameters().setStem(fColor);

            Element LeafElement = getBaby(VisParElement, "vp:leaf");
            fColor = new FullColor();
            fColor.setR(
                    Integer.parseInt(getBabyValue(LeafElement, "vp:r")));
            fColor.setG(
                    Integer.parseInt(getBabyValue(LeafElement, "vp:g")));
            fColor.setB(
                    Integer.parseInt(getBabyValue(LeafElement, "vp:b")));
            flower.getVisualParameters().setLeaf(fColor);

            flower.getVisualParameters().setSize(
                    Float.parseFloat(getBabyValue(VisParElement, "vp:size")));

            Element GrowingTipsElement = getBaby(flowerElement, "cf:growingTips");

            flower.getGrowingTips().setTemperature(
                    Float.parseFloat(getBabyValue(GrowingTipsElement, "cf:temperature")));

            flower.getGrowingTips().setPhotophilous(
                    getBabyValue(GrowingTipsElement, "cf:photophilous"));

            flower.getGrowingTips().setWatering(
                    new BigInteger(getBabyValue(GrowingTipsElement, "cf:watering")));

            flower.setMultiplying(Mult.fromValue(getBabyValue(flowerElement, "cf:multiplying")));

            flowers.add(flower);
        }
        return flowers;
    }

    private static Element getBaby(Element parent, String childName) {
        NodeList nlist = parent.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }

    private static String getBabyValue(Element parent, String childName) {
        Element child = getBaby(parent, childName);
        Node node = child.getFirstChild();
        String value = node.getNodeValue();
        return value;
    }
}

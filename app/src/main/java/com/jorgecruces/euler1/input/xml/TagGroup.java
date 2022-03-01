package com.jorgecruces.euler1.input.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class TagGroup {

    private Element elementTagGroup;

    public TagGroup(Node node) {
        elementTagGroup = null;
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            elementTagGroup = (Element) node;
        }
    }

    public String getAttribute(String attribute) {
        return this.elementTagGroup.getAttribute(attribute);
    }

    public Tag getChildTagByName(String name) {
        return new Tag(name,elementTagGroup.getElementsByTagName(name).item(0).getTextContent());
    }

    public Tag getChildTagByNumber(int number) {

        return new Tag(elementTagGroup.getChildNodes().item(number).getNodeName()
                ,elementTagGroup.getChildNodes().item(number).getTextContent());

    }
}

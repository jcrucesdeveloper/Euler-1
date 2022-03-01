package com.jorgecruces.euler1.input.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.InputStream;

public class XMLParser {

    private Document documentParser;

    // Nodes
    private NodeList parentTagNodes;

    public XMLParser(Document documentParser) {
        this.documentParser = documentParser;
        documentParser.normalize();
    }

    public void setParentTag(String nameParent) {
        parentTagNodes = documentParser.getElementsByTagName(nameParent);
    }

    public String getParentTagName() {
        return parentTagNodes.item(0).getNodeName();
    }

    public int getSizeQuestions() {
        return parentTagNodes.getLength();
    }

    public TagGroup getTagGroupByNumber(int number) {
        return new TagGroup(parentTagNodes.item(number));
    }


}

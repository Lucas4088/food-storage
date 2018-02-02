package com.wat.foodmanager.model.utils;

import com.wat.foodmanager.model.Exceptions.UnableToReadEmbededFileException;
import com.wat.foodmanager.model.Unit;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlFileReader {

    private String filePath;

    public XmlFileReader(String filePath) {
        this.filePath = filePath;

    }

    public List<Unit> readFile() throws UnableToReadEmbededFileException {
        File fXmlFile = new File(filePath);

        List<Unit> units = new ArrayList<>();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);

            NodeList nList = null;
            if (doc != null) {
                nList = doc.getElementsByTagName("unit");
                for (int i = 0; i < nList.getLength(); i++) {
                    units.add(new Unit(doc.getElementsByTagName("unit").item(i).getTextContent()));
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new UnableToReadEmbededFileException();
        }
        return units;
    }


}

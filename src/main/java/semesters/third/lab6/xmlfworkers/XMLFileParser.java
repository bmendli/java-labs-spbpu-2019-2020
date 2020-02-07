package semesters.third.lab6.xmlfworkers;

import semesters.third.lab6.RegistrationAccounts;
import semesters.third.lab6.base.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLFileParser {

    public List<Transaction> parse() {
        try {
            final File file = new File(XMLFileCreator.getPathToXmlFile());
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            NodeList nodeList = document.getElementsByTagName("transaction");
            List<Transaction> transactions = new ArrayList<>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    transactions.add(
                      new Transaction(
                          Integer.parseInt(element.getAttribute("id")),
                          RegistrationAccounts.getAccount(Integer.parseInt(element.getAttribute("from"))),
                          RegistrationAccounts.getAccount(Integer.parseInt(element.getAttribute("to"))),
                          Integer.parseInt(element.getAttribute("amount"))
                      )
                    );
                }
            }
            return transactions;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }
}

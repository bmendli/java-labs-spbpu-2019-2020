package lab6;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class XMLFileCreator {

    private static final int MAX_VALUE_SUMM_TRANSACTION = 100_000;
    private static final int TRANSACTION_QUANTITY = 500;
    private static final int USERS_QUANTITY = 20;

    private Random random;

    public XMLFileCreator() {
        this.random = new Random();
    }

    public void createXMLFile() {

        try (FileWriter fileWriter
                 = new FileWriter("D:\\Program Files (x86)\\java-labs-spbpu-2019-2020\\lab6.xml")) {
            fileWriter.write(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<transactions count=\"" + TRANSACTION_QUANTITY + "\">\n"
            );

            int from, to;
            for (int i = 0; i < TRANSACTION_QUANTITY; i++) {
                do {
                    from = random.nextInt(USERS_QUANTITY);
                    to = random.nextInt(USERS_QUANTITY);
                } while (to == from);
                fileWriter.write(
                    String.format(
                        "\t<transaction id=\"%d\" from=\"%d\" to=\"%d\" amount=\"%d\" />\n",
                        i,
                        from,
                        to,
                        random.nextInt(MAX_VALUE_SUMM_TRANSACTION)
                    )
                );
            }
            fileWriter.write("</transactions>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        XMLFileCreator xmlFileCreator = new XMLFileCreator();
        xmlFileCreator.createXMLFile();
    }
}

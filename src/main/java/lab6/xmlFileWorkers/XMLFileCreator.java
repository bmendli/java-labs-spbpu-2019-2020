package lab6.xmlFileWorkers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class XMLFileCreator {

    private static final Random random = new Random();
    private static final int MAX_VALUE_SUM_TRANSACTION = 50_000 + random.nextInt(75_000);
    private static final int TRANSACTION_QUANTITY = 400 + random.nextInt(800);
    private static final int USERS_QUANTITY = 10 + random.nextInt(20);
    private static final String PATH_TO_XML_FILE = "D:\\Program Files (x86)\\java-labs-spbpu-2019-2020\\lab6.xml";


    public void createXMLFile() {

        try (FileWriter fileWriter
                 = new FileWriter(PATH_TO_XML_FILE)) {
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
                        random.nextInt(MAX_VALUE_SUM_TRANSACTION)
                    )
                );
            }
            fileWriter.write("</transactions>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getUsersQuantity() {
        return USERS_QUANTITY;
    }

    public static int getMaxValueSumTransaction() {
        return MAX_VALUE_SUM_TRANSACTION;
    }

    static String getPathToXmlFile() {
        return PATH_TO_XML_FILE;
    }
}

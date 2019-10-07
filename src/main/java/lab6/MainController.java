package lab6;

import lab6.baseClasses.Account;
import lab6.baseClasses.Transaction;
import lab6.xmlFileWorkers.XMLFileCreator;
import lab6.xmlFileWorkers.XMLFileParser;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class MainController {

    private static List<Transaction> transactions;
    private static OwnLogger logger;
    private static ConcurrentMap<Account, Queue<Transaction>> accountTransactions;
    static List<Queue<Transaction>> tasks;

    public static void main(String[] args) {
        XMLFileCreator xmlFileCreator = new XMLFileCreator();
        xmlFileCreator.createXMLFile();
        RegistrationAccounts.registration();
        XMLFileParser xmlFileParser = new XMLFileParser();
        transactions = xmlFileParser.parse();
        logger = new OwnLogger();
        accountTransactions = new ConcurrentHashMap<>();
        executeTasks();
    }

    private synchronized static void executeTasks() {
        transactions.forEach(
            transaction -> accountTransactions.compute(
                RegistrationAccounts.getAccount(transaction.getFromID().getID()),
                (account, tasks) -> {
                    if (tasks == null) {
                        tasks = new ArrayDeque<>();
                    }
                    tasks.add(transaction);
                    return tasks;
                }
            )
        );
        tasks = new ArrayList<>(accountTransactions.values());

        ForkJoinPool forkJoinPool = new ForkJoinPool(tasks.size());
        forkJoinPool.invoke(new TransactionHandler(0, logger, System.getLogger("Transactions")));
        forkJoinPool.awaitQuiescence(1, TimeUnit.MINUTES);
        forkJoinPool.shutdown();
        try {
            logger.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

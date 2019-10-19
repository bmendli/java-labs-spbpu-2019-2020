package lab6;

import lab6.base.Account;
import lab6.base.Transaction;
import lab6.xmlfworkers.XMLFileCreator;
import lab6.xmlfworkers.XMLFileParser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class MainController {

    private static List<Transaction> transactions;
    private static OwnLogger logger;
    private static ConcurrentMap<Account, Queue<Transaction>> accountTransactions;
    static List<Queue<Transaction>> tasks;
    private boolean isExecute;

    public MainController(OwnLogger logger) {
        MainController.logger = logger;
        isExecute = false;
    }

    private synchronized static void executeTasks() throws InterruptedException {
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
        forkJoinPool.invoke(new TransactionHandler(0, logger, Logger.getLogger("Transactions")));
        forkJoinPool.awaitQuiescence(1, TimeUnit.MINUTES);
        forkJoinPool.shutdown();
        Thread.sleep(1000);

    }

    public boolean isExecute() {
        return isExecute;
    }

    public void executeRandomFile() throws InterruptedException {
        Account.counterAccounts = 0;
        XMLFileCreator xmlFileCreator = new XMLFileCreator();
        xmlFileCreator.createXMLFile();
        RegistrationAccounts.registration();
        XMLFileParser xmlFileParser = new XMLFileParser();
        transactions = xmlFileParser.parse();
        accountTransactions = new ConcurrentHashMap<>();
        executeTasks();
    }
}

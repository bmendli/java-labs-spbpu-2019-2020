package lab6;

import lab6.baseClasses.Transaction;
import lab6.ownExceptions.NotEnoughMoneyException;
import lab6.ownExceptions.TransactionsIsCompletedYet;

import java.util.Queue;
import java.util.concurrent.RecursiveAction;

public class TransactionHandler extends RecursiveAction {

    private static final int ATTEMPTS = 10;
    private System.Logger consoleLogger;

    private int indexQueue;
    private OwnLogger logger;

    TransactionHandler(int indexQueue, OwnLogger logger, System.Logger consoleLogger) {
        this.indexQueue = indexQueue;
        this.logger = logger;
        this.consoleLogger = consoleLogger;
    }

    @Override
    protected void compute() {
        if (indexQueue > MainController.tasks.size()) {
            return;
        }
        TransactionHandler transactionHandler = new TransactionHandler(
            indexQueue + 1,
            logger,
            consoleLogger);
        transactionHandler.fork();
        Queue<Transaction> queue = MainController.tasks.get(indexQueue);

        while (!queue.isEmpty()) {
            Transaction transaction = queue.poll();
            for (int i = 0; i < 10; i++) {
                try {
                    transaction.executeTransaction();
                    consoleLogger.log(System.Logger.Level.INFO, "transaction id=" + transaction.getID()
                        + " executed" + " from id=" + transaction.getFromID().getID()
                        + " to id=" + transaction.getToID().getID() + " amount=" + transaction.getAmount() + "\n");
                    logger.log(System.Logger.Level.INFO, "transaction id=" + transaction.getID() + " executed"
                        + " from id=" + transaction.getFromID().getID()
                        + " to id=" + transaction.getToID().getID() + " amount=" + transaction.getAmount() + "\n");
                    break;

                } catch (NotEnoughMoneyException e) {
                    consoleLogger.log(System.Logger.Level.WARNING, e.getMessage() + "\n");
                    logger.log(System.Logger.Level.WARNING, e.getMessage() + "\n");
                    helpQuiesce();
                } catch (TransactionsIsCompletedYet transactionsIsCompletedYet) {
                    consoleLogger.log(System.Logger.Level.WARNING, "transaction id=" + transaction.getID()
                        + " already executed\n");
                    logger.log(System.Logger.Level.WARNING, "transaction id=" + transaction.getID()
                        + " already executed\n");
                }
            }
            if (!transaction.isCompleted()) {
                consoleLogger.log(System.Logger.Level.WARNING, "transaction id=" + transaction.getID()
                    + " not executed\n");
                logger.log(System.Logger.Level.WARNING, "transaction id=" + transaction.getID()
                    + " not executed\n");
            }
        }
    }
}

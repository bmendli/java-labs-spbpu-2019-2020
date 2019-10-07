package lab6;

import lab6.baseClasses.Account;
import lab6.ownExceptions.NoSuchAccountException;
import lab6.xmlFileWorkers.XMLFileCreator;

import java.util.ArrayList;
import java.util.List;

public final class RegistrationAccounts {

    private RegistrationAccounts() {}

    private static List<Account> accounts;

    static void registration() {
        accounts = new ArrayList<>();
        for (int i = 0; i < XMLFileCreator.getUsersQuantity(); i++) {
            accounts.add(new Account());
        }
    }

    public static Account getAccount(int ID) {
        for (Account account : accounts) {
            if (account.getID() == ID) {
                return account;
            }
        }
        throw new NoSuchAccountException("No such account " + ID);
    }
}

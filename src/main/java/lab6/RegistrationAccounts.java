package lab6;

import lab6.base.Account;
import lab6.exceptions.NoSuchAccountException;
import lab6.xmlfworkers.XMLFileCreator;

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
        return accounts.stream().filter((account -> account.getID() == ID)).findFirst()
            .orElseThrow(NoSuchAccountException::new);
    }
}

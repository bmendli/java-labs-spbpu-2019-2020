package semesters.third.lab6.base;

import semesters.third.lab6.xmlfworkers.XMLFileCreator;

import java.util.Objects;
import java.util.Random;

public class Account {

    private static Random random = new Random();
    public static int counterAccounts = 0;
    private final int ID;
    private int cash;

    public Account() {
        this.ID = counterAccounts++;
        this.cash = 300_000 + random.nextInt(XMLFileCreator.getMaxValueSumTransaction());
    }

    public int getID() {
        return ID;
    }

    int getCash() {
        return cash;
    }

    void income(int cash) {
        this.cash += cash ;
    }

    void outcome(int cash) {
        this.cash -= cash ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return ID == account.getID() &&
            cash == account.getCash();
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, cash);
    }
}

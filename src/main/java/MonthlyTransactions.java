package main.java;

import java.time.YearMonth;
import java.time.LocalDate;
import java.util.*;

public class MonthlyTransactions implements Comparable<MonthlyTransactions>{
    private YearMonth yearMonth;
    private List<Transaction> transactions;

    public MonthlyTransactions(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
        transactions = new ArrayList<Transaction>(); //instantiate transactions list
    }

    public void addTransaction(LocalDate date, int amount) {
        this.transactions.add(new Transaction(date, amount));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    //function to sort the transactions list for this month
    public void sortTransactions() {
        Comparator<Transaction> comparator = Comparator.comparing(Transaction::getDate).thenComparing(Transaction::isCredit);
        Collections.sort(transactions, comparator); //sorted by date then if Credit
    }

    //overridden function for the Comparable interface to have each customer's MonthlyTransactions be sorted by MM/YYYY
    @Override
    public int compareTo(MonthlyTransactions o) {
        return getYearMonth().compareTo(o.getYearMonth());
    }
}

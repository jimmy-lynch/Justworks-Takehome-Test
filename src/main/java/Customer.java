package main.java;

import java.util.*;
import java.time.LocalDate;
import java.time.YearMonth;

public class Customer {
    private static HashMap<String, Customer> directory = new HashMap<>();
    private List<MonthlyTransactions> transactionsByMonth;
    private List<YearMonth> transactionTracker;
    private List<MonthlyBalances> balancesByMonth;
    private String cID;

    private Customer(String cID) { //private constructor to align with design pattern
        this.cID = cID;
        this.transactionsByMonth = new ArrayList<MonthlyTransactions>();
        this.balancesByMonth = new ArrayList<MonthlyBalances>();
        this.transactionTracker = new ArrayList<YearMonth>();
    }

    //getters
    public static Customer getCustomer(String cID) { //implementing the multiton design pattern
        if (!directory.containsKey(cID)) {
            directory.put(cID, new Customer(cID));
        }
        return directory.get(cID);
    }

    public List<MonthlyBalances> getBalancesByMonth() {
        return balancesByMonth;
    }

    public static HashMap<String, Customer> getDirectory() {
        return directory;
    }

    public void addTransaction(LocalDate date, int amount) {
        YearMonth curr = YearMonth.of(date.getYear(), date.getMonth());
        if (!transactionTracker.contains(curr)) { //check if there are currently any transactions for this month
            transactionTracker.add(curr);
            Collections.sort(transactionTracker); //sort tracker, aligned with Transactions for index
            transactionsByMonth.add(new MonthlyTransactions(curr));
            Collections.sort(transactionsByMonth);
        }
        int index = transactionTracker.indexOf(curr);
        transactionsByMonth.get(index).addTransaction(date, amount);
    }

    public void processTransactions() { //process all transactions for the customer by month
        for (MonthlyTransactions transactions : transactionsByMonth) {
            transactions.sortTransactions(); //sort Transactions by month then by whether it's a credit or debit.
            int endingBalance = 0;
            int minBalance = 0;
            int maxBalance = 0;
            boolean first = true;

            for (Transaction t : transactions.getTransactions()) {
                if (t.isCredit()) { //complete arithmetic based on if it's a credit or a debit
                    endingBalance += t.getAmount();
                } else {
                    endingBalance -= t.getAmount();
                }

                if (first) { //first transaction, setting up min & max balances
                    minBalance = endingBalance;
                    maxBalance = endingBalance;
                    first = false;
                }

                if (endingBalance < minBalance) { //tracking min balance
                    minBalance = endingBalance;
                }

                if (endingBalance > maxBalance) { //tracking max balance
                    maxBalance = endingBalance;
                }
            }

            //create a new MonthlyBalances, set variables, and add the object to the balancesByMonth list.
            MonthlyBalances newBalances = new MonthlyBalances(transactions.getYearMonth());
            newBalances.setEndingBalance(endingBalance);
            newBalances.setMinBalance(minBalance);
            newBalances.setMaxBalance(maxBalance);

            balancesByMonth.add(newBalances);
        }

        Collections.sort(balancesByMonth); //sort the balancesByMonth from oldest to most recent
    }
}

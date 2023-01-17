package main.java;

import java.time.YearMonth;

public class MonthlyBalances implements Comparable<MonthlyBalances> {
    private YearMonth yearMonth;
    private int endingBalance;
    private int minBalance;
    private int maxBalance;

    public MonthlyBalances(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
        endingBalance = 0;
        minBalance = 0;
        maxBalance = 0;
    }

    //getters
    public YearMonth getYearMonth() {
        return yearMonth;
    }
    public int getEndingBalance() {
        return endingBalance;
    }
    public int getMinBalance() {
        return minBalance;
    }
    public int getMaxBalance() {
        return maxBalance;
    }

    //setters
    public void setEndingBalance(int bal) {
        endingBalance = bal;
    }
    public void setMinBalance(int bal) {
        minBalance = bal;
    }
    public void setMaxBalance(int bal) {
        maxBalance = bal;
    }

    //overridden function for the Comparable interface to have each customer's MonthlyBalances be sorted by MM/YYYY
    @Override
    public int compareTo(MonthlyBalances o) {
        return getYearMonth().compareTo(o.getYearMonth());
    }
}

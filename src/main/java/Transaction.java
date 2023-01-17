package main.java;

import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private boolean credit; //True = credit, False = debit
    private int amount;

    public Transaction(LocalDate date, int amount) {
        this.date = date;
        this.amount = Math.abs(amount);
        if (amount < 0) {
            this.credit = false;
        } else {
            this.credit = true;
        }
    }

    //getters
    public LocalDate getDate() {
        return date;
    }
    public boolean isCredit() {
        return credit;
    }
    public int getAmount() {
        return amount;
    }

}

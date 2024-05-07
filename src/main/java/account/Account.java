package account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account {

    private final String accountNumber;
    private final String name;
    private final Map<String, Double> transactions;

    public Account(String accountNumber, String name) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.transactions = new HashMap<>();
    }

    public void deposit(String transactionId, double amount) {
        transactions.put(transactionId, amount);
    }

    public void withdraw(String transactionId, double amount) {
        transactions.put(transactionId, -amount);
    }

    public double calculateBalance() {
        double balance = 0;
        for (double amount : transactions.values()) {
            balance = balance + amount;
        }
        return balance;
    }

    public boolean includesAccountDetails(String expectedName, String expectedAccountNumber) {
        return expectedName.equals(name) && expectedAccountNumber.equals(accountNumber);
    }

    public boolean includesName(String expectedName) {
        return expectedName.equals(name);
    }


    public boolean includesTransactionDetails(List<String> expectedTransactions) {
        for (String transaction : expectedTransactions) {
            if (!transactions.containsKey(transaction)) {
                return false;
            }
        }
        return true;
    }

    public boolean includesBalance(double expectedBalance) {
        return calculateBalance() == expectedBalance;
    }

}

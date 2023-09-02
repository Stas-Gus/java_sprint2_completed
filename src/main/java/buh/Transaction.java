package main.java.buh;

public class Transaction {
    String name;
    int quantity;
    int sum;
    boolean isExpense;

    @Override
    public String toString() {
        return "Transaction{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", sum=" + sum +
                ", isExpense=" + isExpense +
                '}';
    }
}

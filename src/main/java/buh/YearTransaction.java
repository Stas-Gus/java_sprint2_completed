package main.java.buh;

public class YearTransaction {
    int month;
    boolean isExpense;
    int amount;

    @Override
    public String toString() {
        return "YearTransaction{" +
                "month=" + month +
                ", isExpense=" + isExpense +
                ", amount=" + amount +
                '}';
    }
}

package main.java.buh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YearlyReportEngine {
    //

    HashMap<Integer, MontlyReport> monthlyReports = new HashMap<>();
    YearlyReport yearlyReport;

    void readMonthlyReport() {
        String delimiter = ",";
        for (int i = 1; i <= 3; i++) {
            String fileName = "m.20210" + i + ".csv";
            ArrayList<String> strings = FileReader.readFileContent(fileName);
            MontlyReport montlyReport = new MontlyReport();
            for (int j = 1; j < strings.size(); j++) {
                String row = strings.get(j);
                String[] splitted = row.split(delimiter);
                Transaction transaction = new Transaction();
                transaction.name = splitted[0];
                transaction.sum = Integer.parseInt(splitted[3]);
                transaction.quantity = Integer.parseInt(splitted[2]);
                transaction.isExpense = Boolean.parseBoolean(splitted[1]);
                montlyReport.rows.add(transaction);
            }
            montlyReport.year = Integer.parseInt(fileName.substring(2, 7));
            montlyReport.month = i;
            monthlyReports.put(i, montlyReport);
        }
    }

    void readYearlyReport() {
        String delimiter = ",";
        String fileName = "y.2021.csv";
        ArrayList<String> strings = FileReader.readFileContent(fileName);
        YearlyReport yearlyReport = new YearlyReport();
        for (int i = 1; i < strings.size(); i++) {
            String row = strings.get(i);
            String[] split = row.split(delimiter);
            YearTransaction yearTransaction = new YearTransaction();
            yearTransaction.month = Integer.parseInt(split[0].substring(1, 2));
            yearTransaction.amount = Integer.parseInt(split[1]);
            yearTransaction.isExpense = Boolean.parseBoolean(split[2]);
            yearlyReport.rows.add(yearTransaction);
        }
        this.yearlyReport = yearlyReport;
    }

    void checkReports() {
        for (Map.Entry<Integer, MontlyReport> set : monthlyReports.entrySet()) {
            int sumSpending = 0;
            int sumProfit = 0;

            ArrayList<Transaction> monthTransactions = set.getValue().rows;
            for (Transaction transaction : monthTransactions) {
                if (transaction.isExpense) {
                    sumSpending += transaction.quantity * transaction.sum;
                } else {
                    sumProfit += transaction.quantity * transaction.sum;
                }
            }

            YearTransaction yearExpense = yearlyReport.rows.stream()
                    .filter(y -> y.month == set.getKey() && y.isExpense).findFirst().orElseThrow();

            YearTransaction yearProfit = yearlyReport.rows.stream()
                    .filter(y -> y.month == set.getKey() && !y.isExpense).findFirst().orElseThrow();

            if (sumSpending == yearExpense.amount) {
                System.out.println("Годовые и месячные расходы равны");
            } else {
                System.out.println("Годовой и месячный отчеты не равны, месяц " + set.getValue().month);
            }

            if (sumProfit == yearProfit.amount) {
                System.out.println("Годовая и месячная прибыль равна");
            }
        }
    }
    void showMonthInfo() {
        for (Map.Entry<Integer, MontlyReport> set : monthlyReports.entrySet()) {
            MontlyReport montlyReport = set.getValue();

            String itemName = null;
            int maxProfit = 0;
            for (Transaction monthTransaction : montlyReport.rows) {
                if (!monthTransaction.isExpense) {
                    if (monthTransaction.sum * monthTransaction.quantity > maxProfit) {
                        maxProfit = monthTransaction.sum * monthTransaction.quantity;
                        itemName = monthTransaction.name;
                    }
                }
            }

            System.out.println(montlyReport.month + " " + montlyReport.year + " " + montlyReport.rows +
                    " название самого прибыльного товара: " + itemName + ", сумма: " + maxProfit);
        }
    }
    void showYearInfo() {
        double expenseSum = 0.0;
        double profitSum = 0.0;

        for (YearTransaction yearTransaction : yearlyReport.rows) {
            System.out.println("Шел 2021 год, " + yearTransaction);
            if (yearTransaction.isExpense) {
                expenseSum += yearTransaction.amount;
            } else {
                profitSum += yearTransaction.amount;
            }
        }

        System.out.println("Средняя сумма трат за год: " + expenseSum / 3 +
                ", средняя прибыль за год: " + profitSum / 3);
    }
}
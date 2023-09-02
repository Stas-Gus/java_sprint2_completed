package main.java.buh;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        YearlyReportEngine engine = new YearlyReportEngine();

        Scanner scanner = new Scanner(System.in);
        boolean readMonth = false;
        boolean readYear = false;

        while (true) { // бесконечный цикл запуска программы
            printMenu(); // вывод общего меню на экран
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                readMonth = true;
                engine.readMonthlyReport(); // считать месячные отчеты
            } else if (userInput == 2) {
                readYear = true;
                engine.readYearlyReport(); // считать годовой отчет
            } else if (userInput == 3) {
                if (readMonth == false && readYear == false) {
                    System.out.println("Сначала считайте месячные и годовые отчеты");
                } else {
                    engine.checkReports();  // сверка годового и месячных отчетов
                }
            } else if (userInput == 4) {
                if (readMonth == false && readYear == false) {
                    System.out.println("Сначала считайте месячные и годовые отчеты");
                } else {
                    engine.showMonthInfo(); // информация о месячных отчетах
                }
            } else if (userInput == 5) {
                if (readMonth == false && readYear == false) {
                    System.out.println("Сначала считайте месячные и годовые отчеты");
                } else {
                    engine.showYearInfo(); // инфиормация о годовых отчетах
                }
            } else if (userInput == 6) { // пункт, который был в задании с вводом специального пароля для завершения программы я понял имено так
                System.out.println("Программа завершилась");
                break;
            } else {
                System.out.println("Такой команды нет!"); // выход за область допустимых пунктов меню
            }
        }
    }
    private static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Сравнить все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить месячный и годовой отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Выход");
    }
}

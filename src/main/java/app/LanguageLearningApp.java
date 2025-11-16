package app;

import facade.LearningFacade;
import visitor.StatisticsVisitor;
import visitor.Visitors;

import java.util.Scanner;

public class LanguageLearningApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите ваше имя: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Guest";
        }

        String language = "English";

        // создаём фасад с пользователем и языком
        LearningFacade facade = new LearningFacade(name, language);
        facade.loadDefaultWords();   // добавляем базовые слова

        System.out.println("Добро пожаловать, " + name + "!");

        boolean running = true;

        while (running) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Flash Card");
            System.out.println("2. Multiple Choice");
            System.out.println("3. Translation");
            System.out.println("4. Run Exercise");
            System.out.println("5. Show Progress (Visitor)");
            System.out.println("6. Show Report (Visitor)");
            System.out.println("7. Show Difficulty Stats (Visitor)");
            System.out.println("8. Exit");
            System.out.print("Choose: ");

            String ch = scanner.nextLine().trim();

            switch (ch) {
                case "1":
                case "2":
                case "3":
                    facade.setStrategy(ch);
                    break;

                case "4":
                    facade.runExercise();
                    break;

                case "5": {
                    StatisticsVisitor v = new Visitors.ProgressCalculator();
                    facade.applyVisitor(v);
                    break;
                }

                case "6": {
                    StatisticsVisitor v = new Visitors.ReportGenerator();
                    facade.applyVisitor(v);
                    break;
                }

                case "7": {
                    StatisticsVisitor v = new Visitors.DifficultyAnalyzer();
                    facade.applyVisitor(v);
                    break;
                }

                case "8":
                    running = false;
                    System.out.println("Выход...");
                    break;

                default:
                    System.out.println("Нет такой опции.");
                    break;
            }
        }
    }
}
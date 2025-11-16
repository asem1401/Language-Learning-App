package app;

import java.util.*;
import model.*;

import  strategy.*;
import visitor.*;


public class LanguageLearningApp {
    public static void main(String[] args){
        List<Word> allWords = new ArrayList<>();
        allWords.add(new Word("Леброн", "Lebron", "Basketball", 1));
        allWords.add(new Word("Кроссовки", "Snickers", "shoes", 2));
        allWords.add(new Word("Айфон", "iphone", "electronics", 1));
        allWords.add(new Word("Диван", "Sofa", "furniture", 3));
        allWords.add(new Word("Кольцо", "Rim1", "Basketball", 2));
        User user = new User("Asem");
        Vocabulary vocab = new Vocabulary("English");
        for (Word w : allWords) vocab.addWord(w);

        Scanner scanner = new Scanner(System.in);
        LearningStrategy strategy = null;
        boolean running = true;

        while(running) {
            System.out.println("LanguageLearningApp!!!");
            System.out.println("1. Flash Card");
            System.out.println("2. Multiple Choice");
            System.out.println("3. Translation");
            System.out.println("4. Run Exercise");
            System.out.println("5. Show Progress Visitor");
            System.out.println("6. Show Report Visitor");
            System.out.println("7. Show Difficulty Visitor");
            System.out.println("8. Exit");
            System.out.print("Choose: ");
            String ch = scanner.nextLine().trim();

            switch (ch) {
                case "1":
                    strategy = new Strategies.FlashCardStrategy();
                    System.out.println("Flash Card выбран.");
                    break;
                case "2":
                    strategy = new Strategies.MultipleChoiceStrategy(allWords);
                    System.out.println("Multiple Choice выбран.");
                    break;
                case "3":
                    strategy = new Strategies.TranslationStrategy();
                    System.out.println("Translation выбран.");
                    break;
                case "4":
                    if (strategy == null) {
                        System.out.println("Сначала выберите стратегию!");
                        break;
                    }
                    Word word = allWords.get((int)(Math.random() * allWords.size()));
                    strategy.teach(word);
                    boolean ok = strategy.test(word);
                    if (ok) {
                        System.out.println("Верно!");
                        user.addCorrectAnswer();
                        user.addLearnedWord(word);
                    } else {
                        System.out.println("Неверно! Верно: " + word.getTranslation());
                        user.addWrongAnswer();
                    }
                    break;
                case "5":
                    StatisticsVisitor visitor1 = new Visitors.ProgressCalculator();
                    user.accept(visitor1);
                    vocab.accept(visitor1);
                    break;
                case "6":
                    StatisticsVisitor visitor2 = new Visitors.ReportGenerator();
                    user.accept(visitor2);
                    vocab.accept(visitor2);
                    break;
                case "7":
                    StatisticsVisitor visitor3 = new Visitors.DifficultyAnalyzer();
                    user.accept(visitor3);
                    vocab.accept(visitor3);
                    break;
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






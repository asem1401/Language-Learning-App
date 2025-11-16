package facade;

import decorator.HintDecorator;
import decorator.TimerDecorator;
import factory.StrategyFactory;
import model.User;
import model.Vocabulary;
import model.Word;
import observer.Observers;
import observer.ProgressObserver;
import strategy.LearningStrategy;
import strategy.Strategies;
import visitor.StatisticsVisitor;
import visitor.Visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LearningFacade {
    private User user;
    private Vocabulary vocabulary;
    private LearningStrategy currentStrategy;
    private List<Word> words;
    private static Scanner sharedScanner = new Scanner(System.in);

    public LearningFacade(String userName, String language) {
        user = new User(userName);
        vocabulary = new Vocabulary(language);
        this.words = new ArrayList<>();
        user.addObserver(new Observers.ConsoleProgressObserver());
    }

    public void addWord(String nativeWord, String translation, String category, int difficulty) {
        Word word = new Word(nativeWord, translation, category, difficulty);
        words.add(word);
        vocabulary.addWord(word);
        System.out.println("Word added successfully: " + nativeWord);
    }

    private Word getRandomWord() {
        return words.get((int) (Math.random() * words.size()));
    }

    public void performExercise(LearningStrategy strategy, Word word) {
        System.out.println("Starting Learning");
        strategy.teach(word);
        boolean correct = strategy.test(word);

        if (correct) {
            System.out.println("Correct answer!");
            user.addCorrectAnswer();
            user.addLearnedWord(word);
        } else {
            System.out.println("Wrong answer! Correct is: " + word.getTranslation());
            user.addWrongAnswer();
        }
        System.out.println("Exercise completed successfully");
    }

    public void loadDefaultWords() {
        addWord("Lebron", "Леброн", "basketball", 1);
        addWord("Sneakers", "Кроссовки", "Shoes", 1);
        addWord("iPhone", "Айфон", "electronics", 1);
        addWord("Sofa", "Диван", "furniture", 3);
        addWord("Rim", "Кольцо", "Basketball", 2);
        System.out.println("Default words loaded successfully: " + words.size() + " words");
    }

    public void setStrategy(String type) {
        LearningStrategy strategy = StrategyFactory.createStrategy(type, words);
        if (strategy != null) {
            this.currentStrategy = strategy;
            System.out.println(type + " strategy loaded successfully");
        } else {
            System.out.println("Unknown strategy: " + type);
        }
    }

    public void runSimpleExercise() {
        if (this.currentStrategy == null) {
            System.out.println("No strategy selected! please select a strategy");
            return;
        }

        if (this.words.isEmpty()) {
            System.out.println("No words found!");
            return;
        }

        Word word = getRandomWord();
        performExercise(currentStrategy, word);
    }

    public void runExerciseWithTimer() {
        if (this.currentStrategy == null) {
            System.out.println("No strategy selected! please select a strategy");
            return;
        }
        if (this.words.isEmpty()) {
            System.out.println("No words found!");
            return;
        }
        Word word = getRandomWord();
        LearningStrategy decoratedStrategy = new TimerDecorator(currentStrategy);
        performExercise(decoratedStrategy, word);
    }

    public void runExerciseWithHint() {
        if (currentStrategy == null) {
            System.out.println("No strategy selected! please select a strategy");
            return;
        }
        if (this.words.isEmpty()) {
            System.out.println("No words found!");
            return;
        }
        Word word = getRandomWord();
        LearningStrategy decoratedStrategy = new HintDecorator(currentStrategy);
        performExercise(decoratedStrategy, word);
    }

    public void runExerciseCustom(boolean withTimer, boolean withHint) {
        if (currentStrategy == null) {
            System.out.println("No strategy selected! please select a strategy");
            return;
        }
        if (this.words.isEmpty()) {
            System.out.println("No words found!");
            return;
        }
        Word word = getRandomWord();
        LearningStrategy decoratedStrategy = currentStrategy;

        if (withTimer) {
            decoratedStrategy = new TimerDecorator(decoratedStrategy);
        }
        if (withHint) {
            decoratedStrategy = new HintDecorator(decoratedStrategy);
        }

        performExercise(decoratedStrategy, word);
    }

    public void showProgress() {
        System.out.println("Progress report");
        StatisticsVisitor progressVisitor = new Visitors.ProgressCalculator();
        user.accept(progressVisitor);
        vocabulary.accept(progressVisitor);
    }

    public void showDetailedReport() {
        System.out.println("Detailed report");
        StatisticsVisitor reportVisitor = new Visitors.ReportGenerator();
        user.accept(reportVisitor);
        vocabulary.accept(reportVisitor);
    }

    public void showDifficultyReport() {
        System.out.println("Difficulty analysis");
        StatisticsVisitor difficultyVisitor = new Visitors.DifficultyAnalyzer();
        user.accept(difficultyVisitor);
        vocabulary.accept(difficultyVisitor);
    }

    public void showAllStats() {
        showProgress();
        showDetailedReport();
        showDifficultyReport();
    }

    public void addProgressObserver(ProgressObserver observer) {
        user.addObserver(observer);
        System.out.println("Observer added successfully");
    }

    public void removeProgressObserver(ProgressObserver observer) {
        user.removeObserver(observer);
        System.out.println("Observer removed successfully");
    }

    public User getUser() {
        return user;
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }

    public int getTotalWords() {
        return words.size();
    }

    public LearningStrategy getCurrentStrategy() {
        return currentStrategy;
    }

    public List<Word> getAllWords() {
        return new ArrayList<>(words);
    }

    public static Scanner getScanner() {
        return sharedScanner;
    }

    public static void closeScanner() {
        if (sharedScanner != null) {
            sharedScanner.close();
        }
    }
}









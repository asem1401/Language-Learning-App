package facade;

import java.util.ArrayList;
import java.util.List;

import factory.StrategyFactory;
import model.User;
import model.Vocabulary;
import model.Word;
import observer.Observers;
import strategy.LearningStrategy;
import visitor.StatisticsVisitor;

public class LearningFacade {

    private User user;
    private Vocabulary vocabulary;
    private LearningStrategy currentStrategy;
    private List<Word> words = new ArrayList<>();

    public LearningFacade(String userName, String language) {
        this.user = new User(userName);
        this.vocabulary = new Vocabulary(language);


        this.user.addObserver(new Observers.ConsoleProgressObserver());
    }




    public void addWord(String nativeWord, String translation, String category, int difficulty) {
        Word w = new Word(nativeWord, translation, category, difficulty);
        words.add(w);
        vocabulary.addWord(w);
    }


    public void loadDefaultWords() {
        addWord("Lebron", "Леброн", "Basketball", 1);
        addWord("Snickers", "Кроссовки", "shoes", 2);
        addWord("iPhone", "Айфон", "electronics", 1);
        addWord("Sofa", "Диван", "furniture", 3);
        addWord("Rim", "Кольцо", "Basketball", 2);
    }

    private Word getRandomWord() {
        if (words.isEmpty()) return null;
        int index = (int) (Math.random() * words.size());
        return words.get(index);
    }

    public List<Word> getWords() {
        return words;
    }



    public void setStrategy(String menuChoice) {
        this.currentStrategy = StrategyFactory.createStrategy(menuChoice, words);
        if (currentStrategy == null) {
            System.out.println(" Стратегия не выбрана (неверный пункт меню).");
        } else {
            System.out.println(" Стратегия успешно выбрана.");
        }
    }


    public void runExercise() {
        if (currentStrategy == null) {
            System.out.println("Сначала выберите стратегию!");
            return;
        }

        Word w = getRandomWord();
        if (w == null) {
            System.out.println("Нет слов для тренировки!");
            return;
        }

        LearningStrategy strategyWithTimer = new decorator.TimerDecorator(currentStrategy);

        strategyWithTimer.teach(w);
        boolean ok = strategyWithTimer.test(w);

        if (ok) {
            System.out.println("Верно!");
            user.addCorrectAnswer();
            user.addLearnedWord(w);
        } else {
            System.out.println("Неверно! Правильный ответ: " + w.getTranslation());
            user.addWrongAnswer();
        }
    }



    public void applyVisitor(StatisticsVisitor visitor) {
        user.accept(visitor);
        vocabulary.accept(visitor);
    }



    public User getUser() {
        return user;
    }

    public Vocabulary getVocabulary() {
        return vocabulary;
    }
}
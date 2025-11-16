package factory;

import java.util.List;

import model.Word;
import strategy.LearningStrategy;
import strategy.Strategies;

public class StrategyFactory {


    public static LearningStrategy createStrategy(String menuChoice, List<Word> allWords) {
        if (menuChoice == null) return null;


        String choice = menuChoice.toLowerCase().trim();

        switch (choice) {
            case "1":
            case "flashcard":
                return new Strategies.FlashCardStrategy();

            case "2":
            case "multiple":
            case "multiplechoice":
                return new Strategies.MultipleChoiceStrategy(allWords);

            case "3":
            case "translation":
                return new Strategies.TranslationStrategy();

            default:
                System.out.println(" нет: " + menuChoice);
                return null;
        }
    }
}
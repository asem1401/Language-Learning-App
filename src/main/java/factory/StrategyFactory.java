package factory;

import model.Word;
import strategy.LearningStrategy;
import strategy.Strategies;

import java.util.List;

public class StrategyFactory {

    public static LearningStrategy createStrategy(String type, List<Word> words) {
        if (type == null) return null;

        return switch (type.toLowerCase()) {
            case "flashcard" -> new Strategies.FlashCardStrategy();
            case "multiplechoice" -> new Strategies.MultipleChoiceStrategy(words);
            case "translation" -> new Strategies.TranslationStrategy();
            default -> null;
        };
    }
}

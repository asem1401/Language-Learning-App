package decorator;

import model.Word;
import strategy.LearningStrategy;

public class HintDecorator implements LearningStrategy {


    private LearningStrategy strategy;


    public HintDecorator(LearningStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void teach(Word word) {

        System.out.println("[Hint] Category: " + word.getCategory()
                + ", difficulty: " + word.getDifficulty());


        strategy.teach(word);
    }

    @Override
    public boolean test(Word word) {
        return strategy.test(word);
    }
}
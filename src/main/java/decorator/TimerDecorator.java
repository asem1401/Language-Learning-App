package decorator;

import model.Word;
import strategy.LearningStrategy;

public class TimerDecorator implements LearningStrategy {

    private LearningStrategy strategy;
    public TimerDecorator(LearningStrategy strategy){
    this.strategy = strategy;
    }

    @Override
    public void teach(Word word) {
        strategy.teach(word);
    }

    @Override
    public boolean test(Word word) {
        long start = System.currentTimeMillis();

        boolean result = strategy.test(word);

        long end = System.currentTimeMillis();
        long timeMs = end - start;

        System.out.println("[Info] You spent " + timeMs + " ms on this answer.");
        return result;
    }
}
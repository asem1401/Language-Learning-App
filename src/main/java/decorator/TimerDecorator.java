package decorator;

import model.Word;
import strategy.LearningStrategy;

public class TimerDecorator implements LearningStrategy {

    private final LearningStrategy base;

    public TimerDecorator(LearningStrategy base) {
        this.base = base;
    }

    @Override
    public void teach(Word word) {

        base.teach(word);
    }

    @Override
    public boolean test(Word word) {
        long start = System.currentTimeMillis();

        boolean result = base.test(word);

        long end = System.currentTimeMillis();
        long timeMs = end - start;

        System.out.println("[Info] You spent " + timeMs + " ms on this answer.");

        return result;
    }
}
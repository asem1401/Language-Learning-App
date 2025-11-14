package strategy;

import model.Word;



public interface LearningStrategy {
    void teach (Word word);
    boolean test(Word word);

}

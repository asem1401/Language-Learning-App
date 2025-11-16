package strategy;

import model.Word;

import java.util.List;
import java.util.Scanner;

public class Strategies {



    public static class FlashCardStrategy implements LearningStrategy {

        private final Scanner scanner = new Scanner(System.in);

        @Override
        public void teach(Word word) {
            System.out.println("Flash card: " + word.getNative());
            System.out.println("Press Enter for translation...");
            scanner.nextLine();
            System.out.println("Translation: " + word.getTranslation());
        }

        @Override
        public boolean test(Word word) {
            System.out.print("Translate: " + word.getNative() + ": ");
            String answer = scanner.nextLine().trim().toLowerCase();
            return answer.equals(word.getTranslation().toLowerCase());
        }
    }


    public static class MultipleChoiceStrategy implements LearningStrategy {

        private final Scanner scanner = new Scanner(System.in);
        private final List<Word> allWords;

        public MultipleChoiceStrategy(List<Word> allWords) {
            this.allWords = allWords;
        }

        @Override
        public void teach(Word word) {
            System.out.println("Choice: " + word.getNative());
            System.out.println("Translation: " + word.getTranslation());
        }

        @Override
        public boolean test(Word word) {
            System.out.println("What is translation of: " + word.getNative() + "?");

            int rightIndex = (int) (Math.random() * 4);
            String[] options = new String[4];
            options[rightIndex] = word.getTranslation();

            for (int i = 0; i < 4; i++) {
                if (i != rightIndex) {
                    Word w = allWords.get((int) (Math.random() * allWords.size()));
                    options[i] = w.getTranslation();
                }
            }

            for (int i = 0; i < 4; i++) {
                System.out.println((i + 1) + ") " + options[i]);
            }

            try {
                int answer = Integer.parseInt(scanner.nextLine().trim());
                return answer - 1 == rightIndex;
            } catch (Exception e) {
                return false;
            }
        }
    }



    public static class TranslationStrategy implements LearningStrategy {

        private final Scanner scanner = new Scanner(System.in);

        @Override
        public void teach(Word word) {
            System.out.println("Translation: " + word.getNative() + " - " + word.getTranslation());
        }

        @Override
        public boolean test(Word word) {
            System.out.print("Translate: " + word.getNative() + ": ");
            String answer = scanner.nextLine().trim().toLowerCase();
            return answer.equals(word.getTranslation().toLowerCase());
        }
    }
}

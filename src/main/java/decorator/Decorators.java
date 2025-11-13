package decorator;

public class Decorators {



    public static class BaseExercise implements Exercise {
        private final String name;

        public BaseExercise(String name) {
            this.name = name;
        }

        @Override
        public void execute() {
            System.out.println("[Exercise] Running: " + name);
        }

        @Override
        public String getName() {
            return name;
        }
    }



    public static abstract class ExerciseDecorator implements Exercise {

        protected Exercise exercise;

        public ExerciseDecorator(Exercise exercise) {
            this.exercise = exercise;
        }

        @Override
        public String getName() {
            return exercise.getName();
        }
    }


    public static class TimedExercise extends ExerciseDecorator {

        public TimedExercise(Exercise exercise) {
            super(exercise);
        }

        @Override
        public void execute() {
            System.out.println("[Decorator] ⏳ Timer enabled");
            exercise.execute();
        }
    }


    public static class HintedExercise extends ExerciseDecorator {

        public HintedExercise(Exercise exercise) {
            super(exercise);
        }

        @Override
        public void execute() {
            System.out.println("[Decorator] 💡 Hint: Think about similar words!");
            exercise.execute();
        }
    }


    public static class BonusExercise extends ExerciseDecorator {

        public BonusExercise(Exercise exercise) {
            super(exercise);
        }

        @Override
        public void execute() {
            System.out.println("[Decorator] ⭐ Bonus XP activated!");
            exercise.execute();
        }
    }
}
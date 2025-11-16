package app;

import facade.LearningFacade;
import observer.ProgressObserver;
import model.User;

import java.util.Scanner;

public class LanguageLearningApp {
    private static LearningFacade facade;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = LearningFacade.getScanner();

        System.out.println("Welcome to Language Learner!");

        initializeUser();
        loadWords();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    manageWords();
                    break;
                case 2:
                    selectStrategy();
                    break;
                case 3:
                    startLearning();
                    break;
                case 4:
                    viewStatistics();
                    break;
                case 5:
                    manageObservers();
                    break;
                case 6:
                    System.out.println("Thank you for using the app. Goodbye.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        LearningFacade.closeScanner();
    }

    private static void initializeUser() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Which language are you learning? ");
        String language = scanner.nextLine().trim();

        facade = new LearningFacade(name, language);
        System.out.println("Profile created successfully.");
    }

    private static void loadWords() {
        System.out.println("Load the default set of words? (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("yes") || answer.equals("y")) {
            facade.loadDefaultWords();
        } else {
            System.out.println("You can add words later in the word management menu.");
        }
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("MAIN MENU");
        System.out.println("1. Word Management");
        System.out.println("2. Select Learning Strategy");
        System.out.println("3. Start Learning");
        System.out.println("4. View Statistics");
        System.out.println("5. Manage Observers");
        System.out.println("6. Exit");
        System.out.print("Your choice: ");
    }

    private static void manageWords() {
        System.out.println();
        System.out.println("WORD MANAGEMENT");
        System.out.println("1. Add a new word");
        System.out.println("2. Show all words");
        System.out.println("3. Back");
        System.out.print("Select an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                addNewWord();
                break;
            case 2:
                showAllWords();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addNewWord() {
        System.out.println("Add a new word:");

        System.out.print("Native language word: ");
        String nativeWord = scanner.nextLine().trim();

        System.out.print("Translation: ");
        String translation = scanner.nextLine().trim();

        System.out.print("Category: ");
        String category = scanner.nextLine().trim();

        System.out.print("Difficulty (1 easy, 2 medium, 3 hard): ");
        int difficulty = getIntInput();

        if (difficulty < 1 || difficulty > 3) {
            System.out.println("Difficulty must be between 1 and 3. Setting to 2.");
            difficulty = 2;
        }

        facade.addWord(nativeWord, translation, category, difficulty);
    }

    private static void showAllWords() {
        System.out.println("All words:");
        System.out.printf("%-20s %-20s %-15s %-10s%n",
                "Word", "Translation", "Category", "Difficulty");

        facade.getAllWords().forEach(word -> {
            System.out.printf("%-20s %-20s %-15s %-10d%n",
                    word.getNative(),
                    word.getTranslation(),
                    word.getCategory(),
                    word.getDifficulty());
        });

        System.out.println("Total words: " + facade.getTotalWords());
    }

    private static void selectStrategy() {
        System.out.println();
        System.out.println("SELECT LEARNING STRATEGY");
        System.out.println("1. Flash Cards");
        System.out.println("2. Multiple Choice");
        System.out.println("3. Translation");
        System.out.println("4. Back");
        System.out.print("Select a strategy: ");

        int choice = getIntInput();

        switch (choice) {
            case 1 -> facade.setStrategy("flashcard");
            case 2 -> facade.setStrategy("multiplechoice");
            case 3 -> facade.setStrategy("translation");
            case 4 -> {}
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void startLearning() {
        if (facade.getCurrentStrategy() == null) {
            System.out.println("Select a learning strategy first.");
            return;
        }

        if (facade.getTotalWords() == 0) {
            System.out.println("The dictionary is empty. Add some words first.");
            return;
        }

        System.out.println();
        System.out.println("LEARNING MODE");
        System.out.println("1. Regular exercise");
        System.out.println("2. Exercise with timer");
        System.out.println("3. Exercise with hint");
        System.out.println("4. Custom exercise");
        System.out.println("5. Exercise series");
        System.out.println("6. Back");
        System.out.print("Choose a mode: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                facade.runSimpleExercise();
                break;
            case 2:
                facade.runExerciseWithTimer();
                break;
            case 3:
                facade.runExerciseWithHint();
                break;
            case 4:
                customExercise();
                break;
            case 5:
                exerciseSeries();
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void customExercise() {
        System.out.print("Use timer? (yes/no): ");
        boolean withTimer = scanner.nextLine().trim().toLowerCase().matches("yes|y");

        System.out.print("Use hints? (yes/no): ");
        boolean withHint = scanner.nextLine().trim().toLowerCase().matches("yes|y");

        facade.runExerciseCustom(withTimer, withHint);
    }

    private static void exerciseSeries() {
        System.out.print("How many exercises? ");
        int count = getIntInput();

        if (count < 1) {
            System.out.println("The number must be greater than 0.");
            return;
        }

        for (int i = 1; i <= count; i++) {
            System.out.println("Exercise " + i + " of " + count);
            facade.runSimpleExercise();

            if (i < count) {
                System.out.println("Press Enter to continue.");
                scanner.nextLine();
            }
        }

        System.out.println("Series completed.");
        facade.showProgress();
    }

    private static void viewStatistics() {
        System.out.println();
        System.out.println("STATISTICS");
        System.out.println("1. Show progress");
        System.out.println("2. Detailed report");
        System.out.println("3. Difficulty analysis");
        System.out.println("4. Show all statistics");
        System.out.println("5. Back");
        System.out.print("Select: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                facade.showProgress();
                break;
            case 2:
                facade.showDetailedReport();
                break;
            case 3:
                facade.showDifficultyReport();
                break;
            case 4:
                facade.showAllStats();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageObservers() {
        System.out.println();
        System.out.println("OBSERVERS");
        System.out.println("1. Add progress observer");
        System.out.println("2. Observer information");
        System.out.println("3. Back");
        System.out.print("Select: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                addObserver();
                break;
            case 2:
                System.out.println("ConsoleProgressObserver is active by default.");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void addObserver() {
        ProgressObserver motivationObserver = new ProgressObserver() {
            @Override
            public void onProgressChanged(User user) {
                double accuracy = user.getAccuracy();
                if (accuracy >= 90) {
                    System.out.println("Great job.");
                } else if (accuracy >= 70) {
                    System.out.println("Good progress.");
                } else if (accuracy >= 50) {
                    System.out.println("Keep practicing.");
                } else if (user.getTotalAnswers() > 0) {
                    System.out.println("Practice makes perfect.");
                }
            }
        };

        facade.addProgressObserver(motivationObserver);
        System.out.println("Observer added.");
    }

    private static int getIntInput() {
        try {
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }
}

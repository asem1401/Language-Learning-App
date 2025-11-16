package observer;

import model.User;

public class Observers {

    public static class ConsoleProgressObserver implements ProgressObserver {

        @Override
        public void onProgressChanged(User user) {
            System.out.println(
                    "User " + user.getName()
                            + " progress: " + user.getCorrectAnswers()
                            + " / " + user.getTotalAnswers()
                            + " ( " + user.getAccuracy() + "%)"
            );
        }
    }
}
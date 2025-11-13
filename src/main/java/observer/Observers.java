package observer;

public class Observers {


    public static class AchievementObserver implements ProgressObserver {

        @Override
        public void update(int xp) {
            if (xp >= 100) {
                System.out.println("[Observer] 🎉 New Achievement: Beginner 100XP!");
            }
        }
    }


    public static class LevelUpObserver implements ProgressObserver {

        private int level = 1;

        @Override
        public void update(int xp) {
            int newLevel = xp / 50 + 1; // каждый 50 XP — новый уровень

            if (newLevel > level) {
                level = newLevel;
                System.out.println("[Observer] 🔼 Level Up! New level: " + level);
            }
        }
    }


    public static class DailyGoalObserver implements ProgressObserver {

        @Override
        public void update(int xp) {
            if (xp >= 20) {
                System.out.println("[Observer] ⭐ Daily goal completed!");
            }
        }
    }
}
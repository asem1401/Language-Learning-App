package visitor;

import model.User;
import model.Word;
import model.Vocabulary;

public class Visitors {
    public static class ProgressCalculator implements StatisticsVisitor{
        @Override
        public void visit(User user) {
            System.out.println("Answer progress: ");
            System.out.println("Answers: " + user.getCorrectAnswers() + "/" + user.getTotalAnswers()) ;
        }

        @Override
        public void visit(Vocabulary vocabulary) {
            System.out.println("Vocabulary stats: ");
            System.out.println("Words" + vocabulary.GetTotalWords());
        }
    }
    public static    class ReportGenerator implements StatisticsVisitor{
        @Override
        public void visit(User user) {
            System.out.println("User " + user.getName() + "learned " + user.getLearnedWords());

        }

        @Override
        public void visit(Vocabulary vocabulary) {
            int easy = 0, mid = 0, hard = 0;
            for (Word w : vocabulary.getWords()) {
                if (w.getDifficulty() == 1) easy++;
                else if (w.getDifficulty() == 2) mid++;
                else hard++;
            }
            System.out.println("Difficulty: easy=" + easy + ", mid=" + mid + ", hard=" + hard);
        }
    }
    public static class DifficultyAnalyzer implements StatisticsVisitor{
        @Override
        public void visit(User user) {
            System.out.println("level: " + (user.getAccuracy()> 80? "Advanced" : user.getAccuracy()> 50? "Middle": "Beginner"));

        }

        @Override
        public void visit(Vocabulary vocabulary) {
            System.out.println("Language: " + vocabulary.getLanguage());
        }
    }
}





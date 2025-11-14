package visitor;

import model.User;
import model.Vocabulary;

public interface StatisticsVisitor {
    void visit(User user);
    void visit(Vocabulary vocabulary);
}

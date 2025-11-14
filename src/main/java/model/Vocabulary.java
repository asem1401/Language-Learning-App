package model;

import java.util.List;
import java.util.ArrayList;
import visitor.StatisticsVisitor;

public class Vocabulary {
    private List<Word> words = new ArrayList<>();
    private String language;

    public Vocabulary(String language){
        this.language = language;
    }
    public void addWord(Word word){
        words.add(word);
    }
    public void accept(StatisticsVisitor visitor2){
        visitor2.visit(this);

    }


    public List<Word> getWords() {
        return words;
    }
    public String getLanguage(){
        return language;
    }
    public int GetTotalWords(){
        return words.size();
    }
}

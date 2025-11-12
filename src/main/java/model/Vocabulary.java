package model;

import java.util.List;
import java.util.ArrayList;

public class Vocabulary {
    private List<Word> words = new ArrayList<>();
    private String language;

    public Vocabulary(String language){
        this.language = language;
    }
    public void addWord(Word word){
        words.add(word);
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

package model;

import java.util.ArrayList;
import java.util.List;



public class User {
    private String name;
    private int correctAnswers;
    private int totalAnswers;
    private List<Word> learnedWords = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }


    public String getName(){
        return name;
    }

    public int getCorrectAnswers(){
        return correctAnswers;
    }

    public int getTotalAnswers(){
        return totalAnswers;
    }


    public double getAccuracy(){
        return totalAnswers == 0 ? 0 : (correctAnswers * 100 / totalAnswers);

    }


    public List<Word> getLearnedWords(){
        return learnedWords;
    }

    public void addCorrectAnnwers(){
        correctAnswers++; totalAnswers++;
    }
    public void addWrongAnsers(){
        totalAnswers++;
    }
    public void addLearnedWord(Word w){
        learnedWords.add(w);


    }


}

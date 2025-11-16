package model;

import observer.ProgressObserver;
import java.util.ArrayList;
import java.util.List;

import visitor.StatisticsVisitor;


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

    public void addCorrectAnswer(){
        correctAnswers++; totalAnswers++;
    }
    public void addWrongAnswer(){
        totalAnswers++;
    }
    public void addLearnedWord(Word w){
        learnedWords.add(w);
    }
    public void accept(StatisticsVisitor visitor){
        visitor.visit(this);

    }



}

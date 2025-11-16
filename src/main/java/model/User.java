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


    private List<ProgressObserver> observers = new ArrayList<>();


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


    public double getAccuracy() {
        return totalAnswers == 0 ? 0 : (correctAnswers * 100.0 / totalAnswers);
    }



    public List<Word> getLearnedWords(){
        return learnedWords;
    }

    public void addObserver(ProgressObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ProgressObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ProgressObserver observer : observers) {
            observer.onProgressChanged(this);
        }
    }


    public void addCorrectAnswer(){
        correctAnswers++;
        totalAnswers++;
        notifyObservers();
    }
    public void addWrongAnswer(){
        totalAnswers++;
        notifyObservers();
    }
    public void addLearnedWord(Word w){
        learnedWords.add(w);
        notifyObservers();
    }
    public void accept(StatisticsVisitor visitor){
        visitor.visit(this);

    }



}

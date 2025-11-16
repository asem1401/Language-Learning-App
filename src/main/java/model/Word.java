package model;

public class Word {
    private String nativeWord;
    private String translation;
    private String category;
    private int difficulty;

    public Word(String nativeWord, String translation, String category, int difficulty){
        this.nativeWord = nativeWord;
        this.translation = translation;
        this.category = category;
        this.difficulty = difficulty;
    }

    public String getNative(){
        return nativeWord;
    }
    public String getTranslation(){
        return translation;
    }
    public String getCategory(){
        return category;
    }
    public int getDifficulty(){
        return difficulty;
    }
}

package com.example.quiz;

public class Answer {
    private String textAns;
    private boolean Right;

    Answer(String textAns, boolean Right){
        this.textAns = textAns;
        this.Right = Right;
    };

    public String getTextAns(){
        return textAns;
    };

    public boolean getRightAns(){
        return Right;
    };
}

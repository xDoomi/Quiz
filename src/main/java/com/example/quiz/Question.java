package com.example.quiz;

import java.util.ArrayList;

public class Question {
    private String textQuestion;
    private int imageQuestion;
    private ArrayList<Answer> answers;

    Question(String textQuestion, int imageQuestion, ArrayList<Answer> answers) {
        this.textQuestion = textQuestion;
        this.imageQuestion = imageQuestion;
        this.answers = answers;
    }

    public String getTextQuestion(){
        return textQuestion;
    }

    public int getImageQuestion(){
        return imageQuestion;
    }

    public String getTextAns(int index){
        return answers.get(index).getTextAns();
    }

    public boolean getRightAns(int index) {
        return answers.get(index).getRightAns();
    }
}

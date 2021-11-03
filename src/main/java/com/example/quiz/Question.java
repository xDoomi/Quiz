package com.example.quiz;

import java.util.ArrayList;

public class Question {
    private String textQuestion;
    private ArrayList<Answer> answers;

    Question(String textQuestion, ArrayList<Answer> answers) {
        this.textQuestion = textQuestion;
        this.answers = answers;
    }

    public String getQuestion(){
        return textQuestion;
    }

    public String getAnswer(int index){
        return answers.get(index).getAnswer();
    }

    public boolean getRightAnswer(int index) {
        return answers.get(index).getRightAnswer();
    }
}

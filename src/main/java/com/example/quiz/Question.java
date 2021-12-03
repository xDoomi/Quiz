package com.example.quiz;

import java.util.ArrayList;

public class Question {
    private String textQuestion;
    private int imageQuestion;
    private ArrayList<Answer> answers;

    Question() {
    }

    Question(String textQuestion, int imageQuestion, ArrayList<Answer> answers) {
        this.textQuestion = textQuestion;
        this.imageQuestion = imageQuestion;
        this.answers = answers;
    }

    public ArrayList<Answer> getAnswers(){
        return this.answers;
    }

    public String getTextQuestion(){
        return this.textQuestion;
    }

    public int getImageQuestion(){
        return this.imageQuestion;
    }

    public String getTextAns(int index){
        return this.answers.get(index).getTextAns();
    }

    public boolean getRightAns(int index) {
        return this.answers.get(index).getRightAns();
    }

    public void setImageQuestion(int imageQuestion) {
        this.imageQuestion = imageQuestion;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    @Override
    public String toString() {
        return "Question{" +
                "textQuestion='" + textQuestion + '\'' +
                ", imageQuestion=" + imageQuestion +
                ", answers=" + answers +
                '}';
    }
}

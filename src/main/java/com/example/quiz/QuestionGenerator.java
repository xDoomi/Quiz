package com.example.quiz;

import java.util.ArrayList;

public class QuestionGenerator {

    private ArrayList<Question> Questions;

    QuestionGenerator(){
        Questions = new ArrayList<>();
        ArrayList<Answer> OneAnswers = new ArrayList<Answer>();
        OneAnswers.add(new Answer("1 answer", true));
        OneAnswers.add(new Answer("2 answer", false));
        OneAnswers.add(new Answer("3 answer", false));
        OneAnswers.add(new Answer("4 answer", false));
        Questions.add(new Question("1 question", OneAnswers));

        ArrayList<Answer> TwoAnswers = new ArrayList<Answer>();
        TwoAnswers.add(new Answer("1 answer", false));
        TwoAnswers.add(new Answer("2 answer", true));
        TwoAnswers.add(new Answer("3 answer", false));
        TwoAnswers.add(new Answer("4 answer", false));
        Questions.add(new Question("2 question", TwoAnswers));

        ArrayList<Answer> ThreeAnswers = new ArrayList<Answer>();
        ThreeAnswers.add(new Answer("1 answer", false));
        ThreeAnswers.add(new Answer("2 answer", false));
        ThreeAnswers.add(new Answer("3 answer", true));
        ThreeAnswers.add(new Answer("4 answer", false));
        Questions.add(new Question("3 question", ThreeAnswers));
    }

    public ArrayList<Question> getQuestions(){
        return Questions;
    }

    public int getSizeGenerator(){
        return Questions.size();
    }

    public Question getQuestion(int index){
        Question temp = Questions.get(index);
        Questions.remove(index);
        return temp;
    }
}

package com.example.quiz;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        Questions.add(new Question("1 question", 0, OneAnswers));

        ArrayList<Answer> TwoAnswers = new ArrayList<Answer>();
        TwoAnswers.add(new Answer("1 answer", false));
        TwoAnswers.add(new Answer("2 answer", true));
        TwoAnswers.add(new Answer("3 answer", false));
        TwoAnswers.add(new Answer("4 answer", false));
        Questions.add(new Question("2 question", 0, TwoAnswers));

        ArrayList<Answer> ThreeAnswers = new ArrayList<Answer>();
        ThreeAnswers.add(new Answer("1 answer", false));
        ThreeAnswers.add(new Answer("2 answer", false));
        ThreeAnswers.add(new Answer("3 answer", true));
        ThreeAnswers.add(new Answer("4 answer", false));
        Questions.add(new Question("3 question", 0, ThreeAnswers));

        ArrayList<Answer> FourAnswers = new ArrayList<Answer>();
        FourAnswers.add(new Answer("1 answer", false));
        FourAnswers.add(new Answer("2 answer", false));
        FourAnswers.add(new Answer("3 answer", false));
        FourAnswers.add(new Answer("4 answer", true));
        Questions.add(new Question("4 question", R.drawable.car, FourAnswers));
    }

    QuestionGenerator(ArrayList questions){
        this.Questions = questions;
    }

    public int getSizeGenerator(){
        return Questions.size();
    }

    public Question getQuestion(int index){
        Question temp = Questions.get(index);
        Questions.remove(index);
        return temp;
    }

    public void basicReadWrite(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        int size = getSizeGenerator();
        for(int i=0; i<size; i++){
            Question question = Questions.get(i);
           // myRef.child(Question.class.getSimpleName()).child(String.valueOf(i)).setValue(question);
        }
        myRef.child("Question").setValue(Questions);
    }
}

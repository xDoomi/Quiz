package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity implements onSomeEventListener{

    FragmentStart frag1;
    FragmentQuiz frag2;
    FragmentLeader frag3;
    FragmentTransaction fTrans;
    QuestionGenerator questionGenerator;

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference questionRef = myRef.child("Question");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Question> questions = new ArrayList<>();
        Query query = questionRef.orderByChild("textQuestion");
        query.addValueEventListener(new ValueEventListener() {
            //  questionRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                    Question question = questionSnapshot.getValue(Question.class);
                    questions.add(question);
                    System.out.println(question.getAnswers());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("loadQuestion:onCancelled, error: " + error.toException());
            }
        });
        questionGenerator = new QuestionGenerator(questions);
//        QuestionGenerator generator = new QuestionGenerator();
//        generator.basicReadWrite();

        frag1 = new FragmentStart();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, frag1);
        fTrans.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                frag1 = new FragmentStart();
                fTrans = getSupportFragmentManager().beginTransaction();
                fTrans.replace(R.id.frgmCont, frag1);
                fTrans.commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void someEvent(String s){
        if(s == "Start"){
            ArrayList<Question> questions = new ArrayList<>();
            Query query = questionRef.orderByChild("textQuestion");
            query.addValueEventListener(new ValueEventListener() {
                //  questionRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                        Question question = questionSnapshot.getValue(Question.class);
                        questions.add(question);
                        System.out.println(question.getAnswers());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    System.out.println("loadQuestion:onCancelled, error: " + error.toException());
                }
            });
            questionGenerator = new QuestionGenerator(questions);
            frag1 = new FragmentStart();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.frgmCont, frag1);
            fTrans.commit();
        }
        else if(s == "Quiz"){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    frag2 = new FragmentQuiz(questionGenerator);
                    fTrans = getSupportFragmentManager().beginTransaction();
                    fTrans.replace(R.id.frgmCont, frag2);
                    fTrans.commit();
                }
            }, 500);
        }
        else if(s == "Dialog"){
            fTrans = getSupportFragmentManager().beginTransaction();
            DialogName dialogname = new DialogName();
            dialogname.show(fTrans, "DialogName");
        }
        else if(s == "Leader"){
            frag3 = new FragmentLeader();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.frgmCont, frag3);
            fTrans.commit();
        }
    }
}
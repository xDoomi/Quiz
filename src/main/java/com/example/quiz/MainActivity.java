package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements onSomeEventListener{

    FragmentStart frag1;
    FragmentQuiz frag2;
    FragmentLeader frag3;
    FragmentTransaction fTrans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //QuestionGenerator generator = new QuestionGenerator();
        //generator.basicReadWrite();

        frag1 = new FragmentStart();
        fTrans = getSupportFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmCont, frag1);
        fTrans.commit();
    }

    @Override
    public void someEvent(String s){
        if(s == "Start"){
            frag1 = new FragmentStart();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.frgmCont, frag1);
            fTrans.commit();
        }
        else if(s == "Quiz"){
            frag2 = new FragmentQuiz();
            fTrans = getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.frgmCont, frag2);
            fTrans.commit();
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
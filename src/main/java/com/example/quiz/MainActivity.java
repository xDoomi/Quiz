package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements onSomeEventListener{

    FragmentStart frag1;
    FragmentQuiz frag2;
    FragmentLeader frag3;
    FragmentTransaction fTrans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        myRef.child("Questions").child("1").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()){
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
        
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
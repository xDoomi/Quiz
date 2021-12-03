package com.example.quiz;

import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.app.Dialog;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogName extends DialogFragment {

    onSomeEventListener someEventListener;
    String title = "Добавить результат в таблицу";
    String message = "Введите ваш ник";
    String button1String = "Добавить результат";
    String button2String = "Начать сначала";

    int score;

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("User");

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            someEventListener = (onSomeEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);

        EditText edittext = new EditText(getActivity());
        edittext.setHint(message);
        builder.setView(edittext);

        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                User user = new User(edittext.getText().toString(), score);
                myRef.push().setValue(user);
                someEventListener.someEvent("Start");
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                someEventListener.someEvent("Start");
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }
}

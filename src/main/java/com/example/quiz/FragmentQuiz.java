package com.example.quiz;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class FragmentQuiz extends Fragment {
    private int btnsId[] = {R.id.btnAnswer1, R.id.btnAnswer2, R.id.btnAnswer3, R.id.btnAnswer4};
    private Button[] buttons = new Button[btnsId.length];
    private Random randInd = new Random();
    private QuestionGenerator generator;
    private int score = 0;
    onSomeEventListener someEventListener;

    FragmentQuiz(QuestionGenerator questionGenerator){
        this.generator = questionGenerator;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragmentquiz, null);

        TextView textView = (TextView) v.findViewById(R.id.textQuestion);
        TextView scoreView = (TextView) v.findViewById(R.id.textScore);
        scoreView.setText(String.format("Score: %d", score));
        ImageView imageView = (ImageView) v.findViewById(R.id.imageQuestion);

        final Question[] question = {setQuestion(v, textView, imageView)};
        for (int i = 0; i< buttons.length; ++i){
            int finalI = i;
            buttons[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Handler handler = new Handler();
                    Button tempBtn = (Button) view;
                    Button rightBtn = getButtonRight(question[0]);
                    if(tempBtn == rightBtn){
                        tempBtn.setBackgroundColor(Color.parseColor("Green"));
                        score++;
                        scoreView.setText(String.format("Score: %d", score));
                    }
                    else{
                        rightBtn.setBackgroundColor(Color.parseColor("Green"));
                        tempBtn.setBackgroundColor(Color.parseColor("Red"));
                    }
                    if(generator.getSizeGenerator() == 0){
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                DialogName dialogname = new DialogName();
                                dialogname.setScore(score);
                                dialogname.show(getActivity().getSupportFragmentManager(), "DialogName");;
                                //someEventListener.someEvent("Dialog");
                            }
                        }, 700);
                    }
                    else{
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                question[0] = setQuestion(v, textView, imageView);
                                tempBtn.setBackgroundColor(Color.parseColor("Blue"));
                                rightBtn.setBackgroundColor(Color.parseColor("Blue"));
                            }
                        }, 700);
                    }
                }
            });
        }
        return v;
    }

    public Question setQuestion(View v, TextView textView, ImageView imageView) {
        int index = randInd.nextInt(generator.getSizeGenerator());
        Question question = generator.getQuestion(index);

        for (int i = 0; i < btnsId.length; ++i) {
            buttons[i] = (Button) v.findViewById(btnsId[i]);
            buttons[i].setText(question.getTextAns(i));
            buttons[i].setBackgroundColor(Color.parseColor("Blue"));
        }

        String textQuestion = question.getTextQuestion();
        textView.setText(textQuestion);

        int imageQuestion = question.getImageQuestion();
        if(imageQuestion != 0){
            imageView.setImageResource(imageQuestion);
        }
        else{
            imageView.setImageResource(0);
        }
        return question;
    }

    public Button getButtonRight(Question question){
        Button rightBtn = null;
        for(int i = 0; i < btnsId.length; ++i){
            if(question.getRightAns(i) == true){
                rightBtn = buttons[i];
            }
        }
        return rightBtn;
    }
}
package com.example.quiz;

public class Answer {
    private String textAns;
    private boolean rightAns;

    public Answer() {
    }

    Answer(String textAns, boolean rightAns){
        this.textAns = textAns;
        this.rightAns = rightAns;
    };

    public String getTextAns(){
        return this.textAns;
    };

    public boolean getRightAns(){
        return this.rightAns;
    };

    public void setRight(boolean right) {
        this.rightAns = right;
    }

    public void setTextAns(String textAns) {
        this.textAns = textAns;
    }
}

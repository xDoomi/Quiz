package com.example.quiz;

public class User {
    private String userName;
    private int userScore;

    User(){

    }

    User(String userName, int userScore){
        this.userName = userName;
        this.userScore = userScore;
    }

    public String getUserName(){
        return userName;
    }
    public int getUserScore(){
        return userScore;
    }
}

package com.epicodus.athletetracker.Models;



public class Workout {

    private String mCommment;
    private String mDate;

    public Workout(String comment, String date){
        this.mCommment = comment;
        this.mDate = date;
    }

    public String getmCommment(){
        return mCommment;
    }
    public String getmDate(){
        return mDate;
    }
}

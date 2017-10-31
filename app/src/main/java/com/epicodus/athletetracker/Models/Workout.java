package com.epicodus.athletetracker.Models;


import org.parceler.Parcel;

@Parcel
public class Workout {

     String mName;
     String mDescription;
     String index;

    public Workout(){}

    public Workout(String name, String description){
        this.mName = name;
        this.mDescription = description;
        this.index = "not_specified";
    }

    public String getmName(){
        return mName;
    }
    public String getmDescription(){
        return mDescription;
    }
    public String getIndex() {
        return index;
    }
    public void setIndex(String index){
        this.index = index;
    }
}

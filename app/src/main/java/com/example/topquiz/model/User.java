package com.example.topquiz.model;

/**
 * Created by Souheil Amal on 2019-01-30
 */
public class User {
   private String mFirstName; // Shuffle the question list before storing it


    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstName='" + mFirstName + '\'' +
                '}';
    }
}

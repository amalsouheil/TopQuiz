package com.example.topquiz.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Souheil Amal on 2019-01-30
 */
public class User implements Comparator<User> ,Comparable<User>, Serializable {
   private String mFirstName; // Shuffle the question list before storing it
   private Integer mScore;

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


    public Integer getScore() {
        return mScore;
    }

    public void setScore(int mScore) {
        this.mScore = mScore;
    }



    @Override
    public int compare(User user1, User user2) {

        int result;

        result = user1.getScore().compareTo(user2.getScore());
        if (result == 0) result = user1.getFirstName().compareTo(user2.getFirstName());

        return result;
    }

    @Override
    public int compareTo(@NonNull User user) {
        return this.mFirstName.compareTo(user.getFirstName());
    }

}

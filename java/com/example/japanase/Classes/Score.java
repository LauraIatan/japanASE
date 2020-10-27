package com.example.japanase.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Score implements Parcelable {

    int level;
    int quiz;
    int score;

    public Score() {
    }

    public Score(int level, int quiz, int score) {
        this.level = level;
        this.quiz = quiz;
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getQuiz() {
        return quiz;
    }

    public void setQuiz(int quiz) {
        this.quiz = quiz;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    protected Score(Parcel in) {
        level = in.readInt();
        quiz = in.readInt();
        score = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(level);
        dest.writeInt(quiz);
        dest.writeInt(score);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };
}

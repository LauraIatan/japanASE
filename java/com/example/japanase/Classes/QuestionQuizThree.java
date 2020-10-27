package com.example.japanase.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionQuizThree implements Parcelable {

    private String cod_raspuns_corect; //reprezentat de caracterul hiragana (a, ka, wo, mi etc...)
    private int nivel;
    private String drawable_res; //reprezentat de caracterul hiragana (a, ka, wo, mi etc...)

    public QuestionQuizThree() {
    }

    public QuestionQuizThree(String cod_raspuns_corect, int nivel, String drawable_res) {
        this.cod_raspuns_corect = cod_raspuns_corect;
        this.nivel = nivel;
        this.drawable_res = drawable_res;
    }

    protected QuestionQuizThree(Parcel in) {
        cod_raspuns_corect = in.readString();
        nivel = in.readInt();
        drawable_res = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cod_raspuns_corect);
        dest.writeInt(nivel);
        dest.writeString(drawable_res);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuestionQuizOne> CREATOR = new Creator<QuestionQuizOne>() {
        @Override
        public QuestionQuizOne createFromParcel(Parcel in) {
            return new QuestionQuizOne(in);
        }

        @Override
        public QuestionQuizOne[] newArray(int size) {
            return new QuestionQuizOne[size];
        }
    };

    public String getCod_raspuns_corect() {
        return cod_raspuns_corect;
    }

    public void setCod_raspuns_corect(String cod_raspuns_corect) {
        this.cod_raspuns_corect = cod_raspuns_corect;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDrawable_res() {
        return drawable_res;
    }

    public void setDrawable_res(String drawable_res) {
        this.drawable_res = drawable_res;
    }
}

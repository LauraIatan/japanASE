package com.example.japanase.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class AnswersQuizTwo implements Parcelable {

    private String cod_raspuns;
    private int nivel;
    private String drawable_res;

    public AnswersQuizTwo() {

    }

    public AnswersQuizTwo(String cod_raspuns, int nivel, String drawable_res) {
        this.cod_raspuns = cod_raspuns;
        this.nivel = nivel;
        this.drawable_res = drawable_res;
    }

    protected AnswersQuizTwo(Parcel in) {
        cod_raspuns = in.readString();
        nivel = in.readInt();
        drawable_res = in.readString();
    }

    public static final Creator<AnswersQuizTwo> CREATOR = new Creator<AnswersQuizTwo>() {
        @Override
        public AnswersQuizTwo createFromParcel(Parcel in) {
            return new AnswersQuizTwo(in);
        }

        @Override
        public AnswersQuizTwo[] newArray(int size) {
            return new AnswersQuizTwo[size];
        }
    };

    public String getCod_raspuns() {
        return cod_raspuns;
    }

    public void setCod_raspuns(String cod_raspuns) {
        this.cod_raspuns = cod_raspuns;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cod_raspuns);
        dest.writeInt(nivel);
        dest.writeString(drawable_res);
    }
}

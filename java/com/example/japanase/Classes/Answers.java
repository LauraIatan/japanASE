package com.example.japanase.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Answers implements Parcelable {

    private String cod_raspuns; //reprezentat de caracterul hiragana (a, ka, wo, mi etc...)
    private int nivel;
    private String text_raspuns; //reprezentat de caracterul hiragana (a, ka, wo, mi etc...)

    public Answers() {
    }

    public Answers(String cod_raspuns, int nivel, String text_raspuns) {
        this.cod_raspuns = cod_raspuns;
        this.nivel = nivel;
        this.text_raspuns = text_raspuns;
    }

    protected Answers(Parcel in) {
        cod_raspuns = in.readString();
        nivel = in.readInt();
        text_raspuns = in.readString();
    }

    public static final Creator<Answers> CREATOR = new Creator<Answers>() {
        @Override
        public Answers createFromParcel(Parcel in) {
            return new Answers(in);
        }

        @Override
        public Answers[] newArray(int size) {
            return new Answers[size];
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

    public String getText_raspuns() {
        return text_raspuns;
    }

    public void setText_raspuns(String text_raspuns) {
        this.text_raspuns = text_raspuns;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cod_raspuns);
        dest.writeInt(nivel);
        dest.writeString(text_raspuns);
    }
}

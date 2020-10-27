package com.example.japanase.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class GameOne implements Parcelable {

    private String cod_raspuns_corect; //reprezentat de caracterul hiragana (a, ka, wo, mi etc...)
    private int nivel; //1-incepator, 2-mediu, 3-avansat

    public GameOne(String cod_raspuns_corect, int nivel) {
        this.cod_raspuns_corect = cod_raspuns_corect;
        this.nivel = nivel;
    }

    public GameOne() {
    }

    protected GameOne(Parcel in) {
        cod_raspuns_corect = in.readString();
        nivel = in.readInt();
    }

    public static final Creator<GameOne> CREATOR = new Creator<GameOne>() {
        @Override
        public GameOne createFromParcel(Parcel in) {
            return new GameOne(in);
        }

        @Override
        public GameOne[] newArray(int size) {
            return new GameOne[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cod_raspuns_corect);
        dest.writeInt(nivel);
    }
}

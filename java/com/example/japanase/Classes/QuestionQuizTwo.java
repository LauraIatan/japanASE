package com.example.japanase.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionQuizTwo implements Parcelable {

    private String cod_raspuns_corect;
    private int nivel;
    private String text_intrebare;

    public QuestionQuizTwo() {

    }

    public QuestionQuizTwo(String cod_raspuns_corect, int nivel, String text_intrebare) {
        this.cod_raspuns_corect = cod_raspuns_corect;
        this.nivel = nivel;
        this.text_intrebare = text_intrebare;
    }

    protected QuestionQuizTwo(Parcel in) {
        cod_raspuns_corect = in.readString();
        nivel = in.readInt();
        text_intrebare = in.readString();
    }

    public static final Creator<QuestionQuizTwo> CREATOR = new Creator<QuestionQuizTwo>() {
        @Override
        public QuestionQuizTwo createFromParcel(Parcel in) {
            return new QuestionQuizTwo(in);
        }

        @Override
        public QuestionQuizTwo[] newArray(int size) {
            return new QuestionQuizTwo[size];
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

    public String getText_intrebare() {
        return text_intrebare;
    }

    public void setText_intrebare(String text_intrebare) {
        this.text_intrebare = text_intrebare;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cod_raspuns_corect);
        dest.writeInt(nivel);
        dest.writeString(text_intrebare);
    }
}

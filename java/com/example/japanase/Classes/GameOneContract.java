package com.example.japanase.Classes;

import android.provider.BaseColumns;

public class GameOneContract {

    public GameOneContract() {
    }

    public static class GameOneTable implements BaseColumns {
        public static final String TABLE_NAME = "game_one";
        public static final String COD_RASPUNS_CORECT = "cod_raspuns_corect";
        public static final String NIVEL = "nivel";
    }
}

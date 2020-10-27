package com.example.japanase.Classes;

import android.provider.BaseColumns;

public final class QuizThreeContract {

    private QuizThreeContract() {

    }

    public static class QuestionTableQuiz3 implements BaseColumns {
        public static final String TABLE_NAME = "questions_quiz_three";
        public static final String COD_RASPUNS_CORECT = "cod_raspuns_corect";
        public static final String NIVEL = "nivel";
        public static final String DRAWABLE_RES = "drawable_res";
    }

    public static class AnswerTableQuiz3 implements BaseColumns {
        public static final String TABLE_NAME = "answers_quiz3";
        public static final String COD_RASPUNS = "cod_raspuns";
        public static final String NIVEL = "nivel";
        public static final String DRAWABLE_RES = "drawable_res";
    }
}

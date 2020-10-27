package com.example.japanase.Classes;

import android.provider.BaseColumns;

public final class QuizOneContract {

    private QuizOneContract() {

    }

    public static class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "questions_quiz_one";
        public static final String COD_RASPUNS_CORECT = "cod_raspuns_corect";
        public static final String NIVEL = "nivel";
        public static final String DRAWABLE_RES = "drawable_res";
    }

    public static class AnswerTable implements BaseColumns {
        public static final String TABLE_NAME = "answers";
        public static final String COD_RASPUNS = "cod_raspuns";
        public static final String NIVEL = "nivel";
        public static final String TEXT_RASPUNS = "text_raspuns";
    }

    public static class ScoreTable implements BaseColumns {
        public static final String TABLE_NAME = "scores";
        public static final String NIVEL = "nivel";
        public static final String SCORE = "score";
        public static final String QUIZ = "quiz";
    }

}

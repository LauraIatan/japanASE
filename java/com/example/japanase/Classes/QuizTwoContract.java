package com.example.japanase.Classes;

import android.provider.BaseColumns;

public final class QuizTwoContract {

    private QuizTwoContract() {

    }

    public static class QuestionTableQuiz2 implements BaseColumns {
        public static final String TABLE_NAME = "questions_quiz_two";
        public static final String COD_RASPUNS_CORECT = "cod_raspuns_corect";
        public static final String NIVEL = "nivel";
        public static final String TEXT_INTREBARE = "text_intrebare";
    }

    public static class AnswerTableQuiz2 implements BaseColumns {
        public static final String TABLE_NAME = "answers_quiz2";
        public static final String COD_RASPUNS = "cod_raspuns";
        public static final String NIVEL = "nivel";
        public static final String DRAWABLE_RES = "drawable_res";
    }
}

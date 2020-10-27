package com.example.japanase.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.japanase.Classes.QuizOneContract.*;
import com.example.japanase.Classes.QuizTwoContract.*;
import com.example.japanase.Classes.QuizThreeContract.*;
import com.example.japanase.Classes.GameOneContract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "hiragana2.db";
    private static final int DATABASE_VERSION = 1;

/*------------------------------------------- QUIZ ONE ------------------------------------------- */
    //query pt creare tabela questions
    public static final String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + QuestionTable.TABLE_NAME + " (" +
            QuestionTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            QuestionTable.COD_RASPUNS_CORECT+" TEXT NOT NULL, "+
            QuestionTable.DRAWABLE_RES+" TEXT NOT NULL, "+
            QuestionTable.NIVEL+" INTEGER NOT NULL);";
    //query pt stergere tabela questions
    public static final String DROP_QUESTIONS_TABLE = "DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME;

    //query pt creare tabela de raspunsuri
    public static final String CREATE_ANSWERS_TABLE = "CREATE TABLE " + AnswerTable.TABLE_NAME + " (" +
            AnswerTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            AnswerTable.COD_RASPUNS+" TEXT NOT NULL, "+
            AnswerTable.TEXT_RASPUNS+" TEXT NOT NULL, "+
            AnswerTable.NIVEL+" INTEGER NOT NULL);";
    //query pt stergere tabela de raspunsuri
    public static final String DROP_ANSWERS_TABLE = "DROP TABLE IF EXISTS " + AnswerTable.TABLE_NAME;

    //query pt creare tabela de scoruri
    public static final String CREATE_SCORES_TABLE = "CREATE TABLE " + ScoreTable.TABLE_NAME + " (" +
            ScoreTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            ScoreTable.QUIZ+" INTEGER NOT NULL, "+
            ScoreTable.SCORE+" INTEGER NOT NULL, "+
            ScoreTable.NIVEL+" INTEGER NOT NULL);";
    //query pt stergere tabela de scoruri
    public static final String DROP_SCORES_TABLE = "DROP TABLE IF EXISTS " + ScoreTable.TABLE_NAME;


/*------------------------------------------- QUIZ TWO ------------------------------------------- */
    //query pt creare tabela questions
    public static final String CREATE_QUESTIONS_TABLE_QUIZ2 = "CREATE TABLE " + QuestionTableQuiz2.TABLE_NAME + " (" +
        QuestionTableQuiz2._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
        QuestionTableQuiz2.COD_RASPUNS_CORECT+" TEXT NOT NULL, "+
        QuestionTableQuiz2.TEXT_INTREBARE+" TEXT NOT NULL, "+
        QuestionTableQuiz2.NIVEL+" INTEGER NOT NULL);";
    //query pt stergere tabela questions
    public static final String DROP_QUESTIONS_TABLE_QUIZ2 = "DROP TABLE IF EXISTS " + QuestionTableQuiz2.TABLE_NAME;

    //query pt creare tabela de raspunsuri
    public static final String CREATE_ANSWERS_TABLE_QUIZ2 = "CREATE TABLE " + AnswerTableQuiz2.TABLE_NAME + " (" +
            AnswerTableQuiz2._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            AnswerTableQuiz2.COD_RASPUNS+" TEXT NOT NULL, "+
            AnswerTableQuiz2.DRAWABLE_RES+" TEXT NOT NULL, "+
            AnswerTableQuiz2.NIVEL+" INTEGER NOT NULL);";
    //query pt stergere tabela de raspunsuri
    public static final String DROP_ANSWERS_TABLE_QUIZ2 = "DROP TABLE IF EXISTS " + AnswerTableQuiz2.TABLE_NAME;


/*------------------------------------------- QUIZ THREE ------------------------------------------- */
    //query pt creare tabela questions
    public static final String CREATE_QUESTIONS_TABLE_QUIZ3 = "CREATE TABLE " + QuestionTableQuiz3.TABLE_NAME + " (" +
            QuestionTableQuiz3._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            QuestionTableQuiz3.COD_RASPUNS_CORECT+" TEXT NOT NULL, "+
            QuestionTableQuiz3.DRAWABLE_RES+" TEXT NOT NULL, "+
            QuestionTableQuiz3.NIVEL+" INTEGER NOT NULL);";
    //query pt stergere tabela questions
    public static final String DROP_QUESTIONS_TABLE_QUIZ3 = "DROP TABLE IF EXISTS " + QuestionTableQuiz3.TABLE_NAME;

    //query pt creare tabela de raspunsuri
    public static final String CREATE_ANSWERS_TABLE_QUIZ3 = "CREATE TABLE " + AnswerTableQuiz3.TABLE_NAME + " (" +
            AnswerTableQuiz3._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            AnswerTableQuiz3.COD_RASPUNS+" TEXT NOT NULL, "+
            AnswerTableQuiz3.DRAWABLE_RES+" TEXT NOT NULL, "+
            AnswerTableQuiz3.NIVEL+" INTEGER NOT NULL);";
    //query pt stergere tabela de raspunsuri
    public static final String DROP_ANSWERS_TABLE_QUIZ3 = "DROP TABLE IF EXISTS " + AnswerTableQuiz3.TABLE_NAME;


/*------------------------------------------- GAME ONE ------------------------------------------- */
    //query pt creare tabela game
    public static final String CREATE_GAME_ONE_TABLE = "CREATE TABLE " + GameOneTable.TABLE_NAME + " (" +
        GameOneTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
        GameOneTable.COD_RASPUNS_CORECT+" TEXT NOT NULL, "+
        GameOneTable.NIVEL+" INTEGER NOT NULL);";
    //query pt stergere tabela game
    public static final String DROP_GAME_ONE_TABLE = "DROP TABLE IF EXISTS " + GameOneTable.TABLE_NAME;


    private Context context;
    private SQLiteDatabase db;

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        try {
            db.execSQL(CREATE_QUESTIONS_TABLE);
            db.execSQL(CREATE_ANSWERS_TABLE);
            db.execSQL(CREATE_SCORES_TABLE);
            db.execSQL(CREATE_QUESTIONS_TABLE_QUIZ2);
            db.execSQL(CREATE_ANSWERS_TABLE_QUIZ2);
            db.execSQL(CREATE_QUESTIONS_TABLE_QUIZ3);
            db.execSQL(CREATE_ANSWERS_TABLE_QUIZ3);
            db.execSQL(CREATE_GAME_ONE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        fillQuestionTable();
        fillAnswerTable();
        fillScoreTable();
        fillQuestionTableQuiz2();
        fillAnswerTableQuiz2();
        fillQuestionTableQuiz3();
        fillAnswerTableQuiz3();
        fillGameOneTable();
    }

    private void fillQuestionTable() {
        //level_1 questions
        QuestionQuizOne q1 = new QuestionQuizOne("a", 1, "a");
        QuestionQuizOne q2 = new QuestionQuizOne("i", 1, "i");
        QuestionQuizOne q3 = new QuestionQuizOne("u", 1, "u");
        QuestionQuizOne q4 = new QuestionQuizOne("e", 1, "e");
        QuestionQuizOne q5 = new QuestionQuizOne("o", 1, "o");
        QuestionQuizOne q6 = new QuestionQuizOne("ka", 1, "ka");
        QuestionQuizOne q7 = new QuestionQuizOne("ki", 1, "ki");
        QuestionQuizOne q8 = new QuestionQuizOne("ku", 1, "ku");
        QuestionQuizOne q9 = new QuestionQuizOne("ke", 1, "ke");
        QuestionQuizOne q10 = new QuestionQuizOne("ko", 1, "ko");

        //level_2 questions
        QuestionQuizOne q11 = new QuestionQuizOne("sa", 2, "sa");
        QuestionQuizOne q12 = new QuestionQuizOne("shi", 2, "shi");
        QuestionQuizOne q13 = new QuestionQuizOne("su", 2, "su");
        QuestionQuizOne q14 = new QuestionQuizOne("se", 2, "se");
        QuestionQuizOne q15 = new QuestionQuizOne("so", 2, "so");
        QuestionQuizOne q16 = new QuestionQuizOne("ta", 2, "ta");
        QuestionQuizOne q17 = new QuestionQuizOne("chi", 2, "chi");
        QuestionQuizOne q18 = new QuestionQuizOne("tsu", 2, "tsu");
        QuestionQuizOne q19 = new QuestionQuizOne("te", 2, "te");
        QuestionQuizOne q20 = new QuestionQuizOne("to", 2, "to");

        //level_3 questions
        QuestionQuizOne q21 = new QuestionQuizOne("na", 3, "na");
        QuestionQuizOne q22 = new QuestionQuizOne("ni", 3, "ni");
        QuestionQuizOne q23 = new QuestionQuizOne("nu", 3, "nu");
        QuestionQuizOne q24 = new QuestionQuizOne("ne", 3, "ne");
        QuestionQuizOne q25 = new QuestionQuizOne("no", 3, "no");
        QuestionQuizOne q26 = new QuestionQuizOne("ha", 3, "ha");
        QuestionQuizOne q27 = new QuestionQuizOne("hi", 3, "hi");
        QuestionQuizOne q28 = new QuestionQuizOne("fu", 3, "fu");
        QuestionQuizOne q29 = new QuestionQuizOne("he", 3, "he");
        QuestionQuizOne q30 = new QuestionQuizOne("ho", 3, "ho");

        //level_4 questions
        QuestionQuizOne q31 = new QuestionQuizOne("ma", 4, "ma");
        QuestionQuizOne q32 = new QuestionQuizOne("mi", 4, "mi");
        QuestionQuizOne q33 = new QuestionQuizOne("mu", 4, "mu");
        QuestionQuizOne q34 = new QuestionQuizOne("me", 4, "me");
        QuestionQuizOne q35 = new QuestionQuizOne("mo", 4, "mo");
        QuestionQuizOne q36 = new QuestionQuizOne("ya", 4, "ya");
        QuestionQuizOne q37 = new QuestionQuizOne("yu", 4, "yu");
        QuestionQuizOne q38 = new QuestionQuizOne("yo", 4, "yo");

        //level_5 questions
        QuestionQuizOne q39 = new QuestionQuizOne("ra", 5, "ra");
        QuestionQuizOne q40 = new QuestionQuizOne("ri", 5, "ri");
        QuestionQuizOne q41 = new QuestionQuizOne("ru", 5, "ru");
        QuestionQuizOne q42 = new QuestionQuizOne("re", 5, "re");
        QuestionQuizOne q43 = new QuestionQuizOne("ro", 5, "ro");
        QuestionQuizOne q44 = new QuestionQuizOne("wa", 5, "wa");
        QuestionQuizOne q45 = new QuestionQuizOne("wo", 5, "wo");
        QuestionQuizOne q46 = new QuestionQuizOne("n", 5, "n");

        insertQuestions(q1); insertQuestions(q2); insertQuestions(q3); insertQuestions(q4); insertQuestions(q5);
        insertQuestions(q6); insertQuestions(q7); insertQuestions(q8); insertQuestions(q9); insertQuestions(q10);
        insertQuestions(q11); insertQuestions(q12); insertQuestions(q13); insertQuestions(q14); insertQuestions(q15);
        insertQuestions(q16); insertQuestions(q17); insertQuestions(q18); insertQuestions(q19); insertQuestions(q20);
        insertQuestions(q21); insertQuestions(q22); insertQuestions(q23); insertQuestions(q24); insertQuestions(q25);
        insertQuestions(q26); insertQuestions(q27); insertQuestions(q28); insertQuestions(q29); insertQuestions(q30);
        insertQuestions(q31); insertQuestions(q32); insertQuestions(q33); insertQuestions(q34); insertQuestions(q35);
        insertQuestions(q36); insertQuestions(q37); insertQuestions(q38); insertQuestions(q39); insertQuestions(q40);
        insertQuestions(q41); insertQuestions(q42); insertQuestions(q43); insertQuestions(q44); insertQuestions(q45);
        insertQuestions(q46);
    }

    private void fillQuestionTableQuiz2() {
        //level_1 questions
        QuestionQuizTwo q1 = new QuestionQuizTwo("a", 1, "a");
        QuestionQuizTwo q2 = new QuestionQuizTwo("i", 1, "i");
        QuestionQuizTwo q3 = new QuestionQuizTwo("u", 1, "u");
        QuestionQuizTwo q4 = new QuestionQuizTwo("e", 1, "e");
        QuestionQuizTwo q5 = new QuestionQuizTwo("o", 1, "o");
        QuestionQuizTwo q6 = new QuestionQuizTwo("ka", 1, "ka");
        QuestionQuizTwo q7 = new QuestionQuizTwo("ki", 1, "ki");
        QuestionQuizTwo q8 = new QuestionQuizTwo("ku", 1, "ku");
        QuestionQuizTwo q9 = new QuestionQuizTwo("ke", 1, "ke");
        QuestionQuizTwo q10 = new QuestionQuizTwo("ko", 1, "ko");

        //level_2 questions
        QuestionQuizTwo q11 = new QuestionQuizTwo("sa", 2, "sa");
        QuestionQuizTwo q12 = new QuestionQuizTwo("shi", 2, "shi");
        QuestionQuizTwo q13 = new QuestionQuizTwo("su", 2, "su");
        QuestionQuizTwo q14 = new QuestionQuizTwo("se", 2, "se");
        QuestionQuizTwo q15 = new QuestionQuizTwo("so", 2, "so");
        QuestionQuizTwo q16 = new QuestionQuizTwo("ta", 2, "ta");
        QuestionQuizTwo q17 = new QuestionQuizTwo("chi", 2, "chi");
        QuestionQuizTwo q18 = new QuestionQuizTwo("tsu", 2, "tsu");
        QuestionQuizTwo q19 = new QuestionQuizTwo("te", 2, "te");
        QuestionQuizTwo q20 = new QuestionQuizTwo("to", 2, "to");

        //level_3 questions
        QuestionQuizTwo q21 = new QuestionQuizTwo("na", 3, "na");
        QuestionQuizTwo q22 = new QuestionQuizTwo("ni", 3, "ni");
        QuestionQuizTwo q23 = new QuestionQuizTwo("nu", 3, "nu");
        QuestionQuizTwo q24 = new QuestionQuizTwo("ne", 3, "ne");
        QuestionQuizTwo q25 = new QuestionQuizTwo("no", 3, "no");
        QuestionQuizTwo q26 = new QuestionQuizTwo("ha", 3, "ha");
        QuestionQuizTwo q27 = new QuestionQuizTwo("hi", 3, "hi");
        QuestionQuizTwo q28 = new QuestionQuizTwo("fu", 3, "fu");
        QuestionQuizTwo q29 = new QuestionQuizTwo("he", 3, "he");
        QuestionQuizTwo q30 = new QuestionQuizTwo("ho", 3, "ho");

        //level_4 questions
        QuestionQuizTwo q31 = new QuestionQuizTwo("ma", 4, "ma");
        QuestionQuizTwo q32 = new QuestionQuizTwo("mi", 4, "mi");
        QuestionQuizTwo q33 = new QuestionQuizTwo("mu", 4, "mu");
        QuestionQuizTwo q34 = new QuestionQuizTwo("me", 4, "me");
        QuestionQuizTwo q35 = new QuestionQuizTwo("mo", 4, "mo");
        QuestionQuizTwo q36 = new QuestionQuizTwo("ya", 4, "ya");
        QuestionQuizTwo q37 = new QuestionQuizTwo("yu", 4, "yu");
        QuestionQuizTwo q38 = new QuestionQuizTwo("yo", 4, "yo");

        //level_5 questions
        QuestionQuizTwo q39 = new QuestionQuizTwo("ra", 5, "ra");
        QuestionQuizTwo q40 = new QuestionQuizTwo("ri", 5, "ri");
        QuestionQuizTwo q41 = new QuestionQuizTwo("ru", 5, "ru");
        QuestionQuizTwo q42 = new QuestionQuizTwo("re", 5, "re");
        QuestionQuizTwo q43 = new QuestionQuizTwo("ro", 5, "ro");
        QuestionQuizTwo q44 = new QuestionQuizTwo("wa", 5, "wa");
        QuestionQuizTwo q45 = new QuestionQuizTwo("wo", 5, "wo");
        QuestionQuizTwo q46 = new QuestionQuizTwo("n", 5, "n");

        insertQuestionsQuizTwo(q1); insertQuestionsQuizTwo(q2); insertQuestionsQuizTwo(q3); insertQuestionsQuizTwo(q4); insertQuestionsQuizTwo(q5);
        insertQuestionsQuizTwo(q6); insertQuestionsQuizTwo(q7); insertQuestionsQuizTwo(q8); insertQuestionsQuizTwo(q9); insertQuestionsQuizTwo(q10);
        insertQuestionsQuizTwo(q11); insertQuestionsQuizTwo(q12); insertQuestionsQuizTwo(q13); insertQuestionsQuizTwo(q14); insertQuestionsQuizTwo(q15);
        insertQuestionsQuizTwo(q16); insertQuestionsQuizTwo(q17); insertQuestionsQuizTwo(q18); insertQuestionsQuizTwo(q19); insertQuestionsQuizTwo(q20);
        insertQuestionsQuizTwo(q21); insertQuestionsQuizTwo(q22); insertQuestionsQuizTwo(q23); insertQuestionsQuizTwo(q24); insertQuestionsQuizTwo(q25);
        insertQuestionsQuizTwo(q26); insertQuestionsQuizTwo(q27); insertQuestionsQuizTwo(q28); insertQuestionsQuizTwo(q29); insertQuestionsQuizTwo(q30);
        insertQuestionsQuizTwo(q31); insertQuestionsQuizTwo(q32); insertQuestionsQuizTwo(q33); insertQuestionsQuizTwo(q34); insertQuestionsQuizTwo(q35);
        insertQuestionsQuizTwo(q36); insertQuestionsQuizTwo(q37); insertQuestionsQuizTwo(q38); insertQuestionsQuizTwo(q39); insertQuestionsQuizTwo(q40);
        insertQuestionsQuizTwo(q41); insertQuestionsQuizTwo(q42); insertQuestionsQuizTwo(q43); insertQuestionsQuizTwo(q44); insertQuestionsQuizTwo(q45);
        insertQuestionsQuizTwo(q46);
    }

    private void fillQuestionTableQuiz3() {
        //level_1 questions
        QuestionQuizThree q1 = new QuestionQuizThree("a", 1, "a");
        QuestionQuizThree q2 = new QuestionQuizThree("i", 1, "i");
        QuestionQuizThree q3 = new QuestionQuizThree("u", 1, "u");
        QuestionQuizThree q4 = new QuestionQuizThree("e", 1, "e");
        QuestionQuizThree q5 = new QuestionQuizThree("o", 1, "o");
        QuestionQuizThree q6 = new QuestionQuizThree("ka", 1, "ka");
        QuestionQuizThree q7 = new QuestionQuizThree("ki", 1, "ki");
        QuestionQuizThree q8 = new QuestionQuizThree("ku", 1, "ku");
        QuestionQuizThree q9 = new QuestionQuizThree("ke", 1, "ke");
        QuestionQuizThree q10 = new QuestionQuizThree("ko", 1, "ko");

        //level_2 questions
        QuestionQuizThree q11 = new QuestionQuizThree("sa", 2, "sa");
        QuestionQuizThree q12 = new QuestionQuizThree("shi", 2, "shi");
        QuestionQuizThree q13 = new QuestionQuizThree("su", 2, "su");
        QuestionQuizThree q14 = new QuestionQuizThree("se", 2, "se");
        QuestionQuizThree q15 = new QuestionQuizThree("so", 2, "so");
        QuestionQuizThree q16 = new QuestionQuizThree("ta", 2, "ta");
        QuestionQuizThree q17 = new QuestionQuizThree("chi", 2, "chi");
        QuestionQuizThree q18 = new QuestionQuizThree("tsu", 2, "tsu");
        QuestionQuizThree q19 = new QuestionQuizThree("te", 2, "te");
        QuestionQuizThree q20 = new QuestionQuizThree("to", 2, "to");

        //level_3 questions
        QuestionQuizThree q21 = new QuestionQuizThree("na", 3, "na");
        QuestionQuizThree q22 = new QuestionQuizThree("ni", 3, "ni");
        QuestionQuizThree q23 = new QuestionQuizThree("nu", 3, "nu");
        QuestionQuizThree q24 = new QuestionQuizThree("ne", 3, "ne");
        QuestionQuizThree q25 = new QuestionQuizThree("no", 3, "no");
        QuestionQuizThree q26 = new QuestionQuizThree("ha", 3, "ha");
        QuestionQuizThree q27 = new QuestionQuizThree("hi", 3, "hi");
        QuestionQuizThree q28 = new QuestionQuizThree("fu", 3, "fu");
        QuestionQuizThree q29 = new QuestionQuizThree("he", 3, "he");
        QuestionQuizThree q30 = new QuestionQuizThree("ho", 3, "ho");

        //level_4 questions
        QuestionQuizThree q31 = new QuestionQuizThree("ma", 4, "ma");
        QuestionQuizThree q32 = new QuestionQuizThree("mi", 4, "mi");
        QuestionQuizThree q33 = new QuestionQuizThree("mu", 4, "mu");
        QuestionQuizThree q34 = new QuestionQuizThree("me", 4, "me");
        QuestionQuizThree q35 = new QuestionQuizThree("mo", 4, "mo");
        QuestionQuizThree q36 = new QuestionQuizThree("ya", 4, "ya");
        QuestionQuizThree q37 = new QuestionQuizThree("yu", 4, "yu");
        QuestionQuizThree q38 = new QuestionQuizThree("yo", 4, "yo");

        //level_5 questions
        QuestionQuizThree q39 = new QuestionQuizThree("ra", 5, "ra");
        QuestionQuizThree q40 = new QuestionQuizThree("ri", 5, "ri");
        QuestionQuizThree q41 = new QuestionQuizThree("ru", 5, "ru");
        QuestionQuizThree q42 = new QuestionQuizThree("re", 5, "re");
        QuestionQuizThree q43 = new QuestionQuizThree("ro", 5, "ro");
        QuestionQuizThree q44 = new QuestionQuizThree("wa", 5, "wa");
        QuestionQuizThree q45 = new QuestionQuizThree("wo", 5, "wo");
        QuestionQuizThree q46 = new QuestionQuizThree("n", 5, "n");

        insertQuestionsQuizThree(q1); insertQuestionsQuizThree(q2); insertQuestionsQuizThree(q3); insertQuestionsQuizThree(q4); insertQuestionsQuizThree(q5);
        insertQuestionsQuizThree(q6); insertQuestionsQuizThree(q7); insertQuestionsQuizThree(q8); insertQuestionsQuizThree(q9); insertQuestionsQuizThree(q10);
        insertQuestionsQuizThree(q11); insertQuestionsQuizThree(q12); insertQuestionsQuizThree(q13); insertQuestionsQuizThree(q14); insertQuestionsQuizThree(q15);
        insertQuestionsQuizThree(q16); insertQuestionsQuizThree(q17); insertQuestionsQuizThree(q18); insertQuestionsQuizThree(q19); insertQuestionsQuizThree(q20);
        insertQuestionsQuizThree(q21); insertQuestionsQuizThree(q22); insertQuestionsQuizThree(q23); insertQuestionsQuizThree(q24); insertQuestionsQuizThree(q25);
        insertQuestionsQuizThree(q26); insertQuestionsQuizThree(q27); insertQuestionsQuizThree(q28); insertQuestionsQuizThree(q29); insertQuestionsQuizThree(q30);
        insertQuestionsQuizThree(q31); insertQuestionsQuizThree(q32); insertQuestionsQuizThree(q33); insertQuestionsQuizThree(q34); insertQuestionsQuizThree(q35);
        insertQuestionsQuizThree(q36); insertQuestionsQuizThree(q37); insertQuestionsQuizThree(q38); insertQuestionsQuizThree(q39); insertQuestionsQuizThree(q40);
        insertQuestionsQuizThree(q41); insertQuestionsQuizThree(q42); insertQuestionsQuizThree(q43); insertQuestionsQuizThree(q44); insertQuestionsQuizThree(q45);
        insertQuestionsQuizThree(q46);
    }

    public void fillGameOneTable() {
        //level 1 - incepator
        GameOne g1 = new GameOne("a", 1);
        GameOne g2 = new GameOne("i", 1);
        GameOne g3 = new GameOne("u", 1);
        GameOne g4 = new GameOne("e", 1);
        GameOne g5 = new GameOne("o", 1);
        GameOne g6 = new GameOne("ka", 1);
        GameOne g7 = new GameOne("ki", 1);
        GameOne g8 = new GameOne("ku", 1);
        GameOne g9 = new GameOne("ke", 1);
        GameOne g10 = new GameOne("ko", 1);
        GameOne g11 = new GameOne("sa", 1);
        GameOne g12 = new GameOne("shi", 1);
        GameOne g13 = new GameOne("su", 1);
        GameOne g14 = new GameOne("se", 1);
        GameOne g15 = new GameOne("so", 1);

        //level 2 - mediu
        GameOne g16 = new GameOne("ta", 2);
        GameOne g17 = new GameOne("chi", 2);
        GameOne g18 = new GameOne("tsu", 2);
        GameOne g19 = new GameOne("te", 2);
        GameOne g20 = new GameOne("to", 2);
        GameOne g21 = new GameOne("na", 2);
        GameOne g22 = new GameOne("ni", 2);
        GameOne g23 = new GameOne("nu", 2);
        GameOne g24 = new GameOne("ne", 2);
        GameOne g25 = new GameOne("no", 2);
        GameOne g26 = new GameOne("ha", 2);
        GameOne g27 = new GameOne("hi", 2);
        GameOne g28 = new GameOne("fu", 2);
        GameOne g29 = new GameOne("he", 2);
        GameOne g30 = new GameOne("ho", 2);

        //level 3 - avansat
        GameOne g31 = new GameOne("ma", 3);
        GameOne g32 = new GameOne("mi", 3);
        GameOne g33 = new GameOne("mu", 3);
        GameOne g34 = new GameOne("me", 3);
        GameOne g35 = new GameOne("mo", 3);
        GameOne g36 = new GameOne("ra", 3);
        GameOne g37 = new GameOne("ri", 3);
        GameOne g38 = new GameOne("ru", 3);
        GameOne g39 = new GameOne("re", 3);
        GameOne g40 = new GameOne("ro", 3);
        GameOne g41 = new GameOne("wa", 3);
        GameOne g42 = new GameOne("wo", 3);
        GameOne g43 = new GameOne("n", 3);
        GameOne g44 = new GameOne("yo", 3);
        GameOne g45 = new GameOne("yu", 3);
        GameOne g46 = new GameOne("ya", 3);

        insertGameOne(g1); insertGameOne(g2); insertGameOne(g3); insertGameOne(g4); insertGameOne(g5);
        insertGameOne(g6); insertGameOne(g7); insertGameOne(g8); insertGameOne(g9); insertGameOne(g10);
        insertGameOne(g11); insertGameOne(g12); insertGameOne(g13); insertGameOne(g14); insertGameOne(g15);
        insertGameOne(g16); insertGameOne(g17); insertGameOne(g18); insertGameOne(g19); insertGameOne(g20);
        insertGameOne(g21); insertGameOne(g22); insertGameOne(g23); insertGameOne(g24); insertGameOne(g25);
        insertGameOne(g26); insertGameOne(g27); insertGameOne(g28); insertGameOne(g29); insertGameOne(g30);
        insertGameOne(g31); insertGameOne(g32); insertGameOne(g33); insertGameOne(g34); insertGameOne(g35);
        insertGameOne(g36); insertGameOne(g37); insertGameOne(g38); insertGameOne(g39); insertGameOne(g40);
        insertGameOne(g41); insertGameOne(g42); insertGameOne(g43); insertGameOne(g44); insertGameOne(g45);
        insertGameOne(g46);

    }

    private void fillAnswerTable() {
        //level_1 answers
        Answers a1 = new Answers("a", 1, "a");
        Answers a2 = new Answers("i", 1, "i");
        Answers a3 = new Answers("u", 1, "u");
        Answers a4 = new Answers("e", 1, "e");
        Answers a5 = new Answers("o", 1, "o");
        Answers a6 = new Answers("ka", 1, "ka");
        Answers a7 = new Answers("ki", 1, "ki");
        Answers a8 = new Answers("ku", 1, "ku");
        Answers a9 = new Answers("ke", 1, "ke");
        Answers a10 = new Answers("ko", 1, "ko");

        //level_2 answers
        Answers a11 = new Answers("sa", 2, "sa");
        Answers a12 = new Answers("shi", 2, "shi");
        Answers a13 = new Answers("su", 2, "su");
        Answers a14 = new Answers("se", 2, "se");
        Answers a15 = new Answers("so", 2, "so");
        Answers a16 = new Answers("ta", 2, "ta");
        Answers a17 = new Answers("chi", 2, "chi");
        Answers a18 = new Answers("tsu", 2, "tsu");
        Answers a19 = new Answers("te", 2, "te");
        Answers a20 = new Answers("to", 2, "to");

        //level_3 answers
        Answers a21 = new Answers("na", 3, "na");
        Answers a22 = new Answers("ni", 3, "ni");
        Answers a23 = new Answers("nu", 3, "nu");
        Answers a24 = new Answers("ne", 3, "ne");
        Answers a25 = new Answers("no", 3, "no");
        Answers a26 = new Answers("ha", 3, "ha");
        Answers a27 = new Answers("hi", 3, "hi");
        Answers a28 = new Answers("fu", 3, "fu");
        Answers a29 = new Answers("he", 3, "he");
        Answers a30 = new Answers("ho", 3, "ho");

        //level_4 answers
        Answers a31 = new Answers("ma", 4, "ma");
        Answers a32 = new Answers("mi", 4, "mi");
        Answers a33 = new Answers("mu", 4, "mu");
        Answers a34 = new Answers("me", 4, "me");
        Answers a35 = new Answers("mo", 4, "mo");
        Answers a36 = new Answers("ya", 4, "ya");
        Answers a37 = new Answers("yu", 4, "yu");
        Answers a38 = new Answers("yo", 4, "yo");

        //level_5 answers
        Answers a39 = new Answers("ra", 5, "ra");
        Answers a40 = new Answers("ri", 5, "ri");
        Answers a41 = new Answers("ru", 5, "ru");
        Answers a42 = new Answers("re", 5, "re");
        Answers a43 = new Answers("ro", 5, "ro");
        Answers a44 = new Answers("wa", 5, "wa");
        Answers a45 = new Answers("wo", 5, "wo");
        Answers a46 = new Answers("n", 5, "n");

        insertAnswers(a1); insertAnswers(a2); insertAnswers(a3); insertAnswers(a4); insertAnswers(a5);
        insertAnswers(a6); insertAnswers(a7); insertAnswers(a8); insertAnswers(a9); insertAnswers(a10);
        insertAnswers(a11); insertAnswers(a12); insertAnswers(a13); insertAnswers(a14); insertAnswers(a15);
        insertAnswers(a16); insertAnswers(a17); insertAnswers(a18); insertAnswers(a19); insertAnswers(a20);
        insertAnswers(a21); insertAnswers(a22); insertAnswers(a23); insertAnswers(a24); insertAnswers(a25);
        insertAnswers(a26); insertAnswers(a27); insertAnswers(a28); insertAnswers(a29); insertAnswers(a30);
        insertAnswers(a31); insertAnswers(a32); insertAnswers(a33); insertAnswers(a34); insertAnswers(a35);
        insertAnswers(a36); insertAnswers(a37); insertAnswers(a38); insertAnswers(a39); insertAnswers(a40);
        insertAnswers(a41); insertAnswers(a42); insertAnswers(a43); insertAnswers(a44); insertAnswers(a45);
        insertAnswers(a46);
    }

    private void fillAnswerTableQuiz2() {
        //level_1 answers
        AnswersQuizTwo a1 = new AnswersQuizTwo("a", 1, "a");
        AnswersQuizTwo a2 = new AnswersQuizTwo("i", 1, "i");
        AnswersQuizTwo a3 = new AnswersQuizTwo("u", 1, "u");
        AnswersQuizTwo a4 = new AnswersQuizTwo("e", 1, "e");
        AnswersQuizTwo a5 = new AnswersQuizTwo("o", 1, "o");
        AnswersQuizTwo a6 = new AnswersQuizTwo("ka", 1, "ka");
        AnswersQuizTwo a7 = new AnswersQuizTwo("ki", 1, "ki");
        AnswersQuizTwo a8 = new AnswersQuizTwo("ku", 1, "ku");
        AnswersQuizTwo a9 = new AnswersQuizTwo("ke", 1, "ke");
        AnswersQuizTwo a10 = new AnswersQuizTwo("ko", 1, "ko");

        //level_2 AnswersQuizTwo
        AnswersQuizTwo a11 = new AnswersQuizTwo("sa", 2, "sa");
        AnswersQuizTwo a12 = new AnswersQuizTwo("shi", 2, "shi");
        AnswersQuizTwo a13 = new AnswersQuizTwo("su", 2, "su");
        AnswersQuizTwo a14 = new AnswersQuizTwo("se", 2, "se");
        AnswersQuizTwo a15 = new AnswersQuizTwo("so", 2, "so");
        AnswersQuizTwo a16 = new AnswersQuizTwo("ta", 2, "ta");
        AnswersQuizTwo a17 = new AnswersQuizTwo("chi", 2, "chi");
        AnswersQuizTwo a18 = new AnswersQuizTwo("tsu", 2, "tsu");
        AnswersQuizTwo a19 = new AnswersQuizTwo("te", 2, "te");
        AnswersQuizTwo a20 = new AnswersQuizTwo("to", 2, "to");

        //level_3 AnswersQuizTwo
        AnswersQuizTwo a21 = new AnswersQuizTwo("na", 3, "na");
        AnswersQuizTwo a22 = new AnswersQuizTwo("ni", 3, "ni");
        AnswersQuizTwo a23 = new AnswersQuizTwo("nu", 3, "nu");
        AnswersQuizTwo a24 = new AnswersQuizTwo("ne", 3, "ne");
        AnswersQuizTwo a25 = new AnswersQuizTwo("no", 3, "no");
        AnswersQuizTwo a26 = new AnswersQuizTwo("ha", 3, "ha");
        AnswersQuizTwo a27 = new AnswersQuizTwo("hi", 3, "hi");
        AnswersQuizTwo a28 = new AnswersQuizTwo("fu", 3, "fu");
        AnswersQuizTwo a29 = new AnswersQuizTwo("he", 3, "he");
        AnswersQuizTwo a30 = new AnswersQuizTwo("ho", 3, "ho");

        //level_4 AnswersQuizTwo
        AnswersQuizTwo a31 = new AnswersQuizTwo("ma", 4, "ma");
        AnswersQuizTwo a32 = new AnswersQuizTwo("mi", 4, "mi");
        AnswersQuizTwo a33 = new AnswersQuizTwo("mu", 4, "mu");
        AnswersQuizTwo a34 = new AnswersQuizTwo("me", 4, "me");
        AnswersQuizTwo a35 = new AnswersQuizTwo("mo", 4, "mo");
        AnswersQuizTwo a36 = new AnswersQuizTwo("ya", 4, "ya");
        AnswersQuizTwo a37 = new AnswersQuizTwo("yu", 4, "yu");
        AnswersQuizTwo a38 = new AnswersQuizTwo("yo", 4, "yo");

        //level_5 AnswersQuizTwo
        AnswersQuizTwo a39 = new AnswersQuizTwo("ra", 5, "ra");
        AnswersQuizTwo a40 = new AnswersQuizTwo("ri", 5, "ri");
        AnswersQuizTwo a41 = new AnswersQuizTwo("ru", 5, "ru");
        AnswersQuizTwo a42 = new AnswersQuizTwo("re", 5, "re");
        AnswersQuizTwo a43 = new AnswersQuizTwo("ro", 5, "ro");
        AnswersQuizTwo a44 = new AnswersQuizTwo("wa", 5, "wa");
        AnswersQuizTwo a45 = new AnswersQuizTwo("wo", 5, "wo");
        AnswersQuizTwo a46 = new AnswersQuizTwo("n", 5, "n");

        insertAnswersQuizTwo(a1); insertAnswersQuizTwo(a2); insertAnswersQuizTwo(a3); insertAnswersQuizTwo(a4); insertAnswersQuizTwo(a5);
        insertAnswersQuizTwo(a6); insertAnswersQuizTwo(a7); insertAnswersQuizTwo(a8); insertAnswersQuizTwo(a9); insertAnswersQuizTwo(a10);
        insertAnswersQuizTwo(a11); insertAnswersQuizTwo(a12); insertAnswersQuizTwo(a13); insertAnswersQuizTwo(a14); insertAnswersQuizTwo(a15);
        insertAnswersQuizTwo(a16); insertAnswersQuizTwo(a17); insertAnswersQuizTwo(a18); insertAnswersQuizTwo(a19); insertAnswersQuizTwo(a20);
        insertAnswersQuizTwo(a21); insertAnswersQuizTwo(a22); insertAnswersQuizTwo(a23); insertAnswersQuizTwo(a24); insertAnswersQuizTwo(a25);
        insertAnswersQuizTwo(a26); insertAnswersQuizTwo(a27); insertAnswersQuizTwo(a28); insertAnswersQuizTwo(a29); insertAnswersQuizTwo(a30);
        insertAnswersQuizTwo(a31); insertAnswersQuizTwo(a32); insertAnswersQuizTwo(a33); insertAnswersQuizTwo(a34); insertAnswersQuizTwo(a35);
        insertAnswersQuizTwo(a36); insertAnswersQuizTwo(a37); insertAnswersQuizTwo(a38); insertAnswersQuizTwo(a39); insertAnswersQuizTwo(a40);
        insertAnswersQuizTwo(a41); insertAnswersQuizTwo(a42); insertAnswersQuizTwo(a43); insertAnswersQuizTwo(a44); insertAnswersQuizTwo(a45);
        insertAnswersQuizTwo(a46);
    }

    private void fillAnswerTableQuiz3() {
        //level_1 answers
        AnswersQuizThree a1 = new AnswersQuizThree("a", 1, "a");
        AnswersQuizThree a2 = new AnswersQuizThree("i", 1, "i");
        AnswersQuizThree a3 = new AnswersQuizThree("u", 1, "u");
        AnswersQuizThree a4 = new AnswersQuizThree("e", 1, "e");
        AnswersQuizThree a5 = new AnswersQuizThree("o", 1, "o");
        AnswersQuizThree a6 = new AnswersQuizThree("ka", 1, "ka");
        AnswersQuizThree a7 = new AnswersQuizThree("ki", 1, "ki");
        AnswersQuizThree a8 = new AnswersQuizThree("ku", 1, "ku");
        AnswersQuizThree a9 = new AnswersQuizThree("ke", 1, "ke");
        AnswersQuizThree a10 = new AnswersQuizThree("ko", 1, "ko");

        //level_2 AnswersQuizThree
        AnswersQuizThree a11 = new AnswersQuizThree("sa", 2, "sa");
        AnswersQuizThree a12 = new AnswersQuizThree("shi", 2, "shi");
        AnswersQuizThree a13 = new AnswersQuizThree("su", 2, "su");
        AnswersQuizThree a14 = new AnswersQuizThree("se", 2, "se");
        AnswersQuizThree a15 = new AnswersQuizThree("so", 2, "so");
        AnswersQuizThree a16 = new AnswersQuizThree("ta", 2, "ta");
        AnswersQuizThree a17 = new AnswersQuizThree("chi", 2, "chi");
        AnswersQuizThree a18 = new AnswersQuizThree("tsu", 2, "tsu");
        AnswersQuizThree a19 = new AnswersQuizThree("te", 2, "te");
        AnswersQuizThree a20 = new AnswersQuizThree("to", 2, "to");

        //level_3 AnswersQuizThree
        AnswersQuizThree a21 = new AnswersQuizThree("na", 3, "na");
        AnswersQuizThree a22 = new AnswersQuizThree("ni", 3, "ni");
        AnswersQuizThree a23 = new AnswersQuizThree("nu", 3, "nu");
        AnswersQuizThree a24 = new AnswersQuizThree("ne", 3, "ne");
        AnswersQuizThree a25 = new AnswersQuizThree("no", 3, "no");
        AnswersQuizThree a26 = new AnswersQuizThree("ha", 3, "ha");
        AnswersQuizThree a27 = new AnswersQuizThree("hi", 3, "hi");
        AnswersQuizThree a28 = new AnswersQuizThree("fu", 3, "fu");
        AnswersQuizThree a29 = new AnswersQuizThree("he", 3, "he");
        AnswersQuizThree a30 = new AnswersQuizThree("ho", 3, "ho");

        //level_4 AnswersQuizThree
        AnswersQuizThree a31 = new AnswersQuizThree("ma", 4, "ma");
        AnswersQuizThree a32 = new AnswersQuizThree("mi", 4, "mi");
        AnswersQuizThree a33 = new AnswersQuizThree("mu", 4, "mu");
        AnswersQuizThree a34 = new AnswersQuizThree("me", 4, "me");
        AnswersQuizThree a35 = new AnswersQuizThree("mo", 4, "mo");
        AnswersQuizThree a36 = new AnswersQuizThree("ya", 4, "ya");
        AnswersQuizThree a37 = new AnswersQuizThree("yu", 4, "yu");
        AnswersQuizThree a38 = new AnswersQuizThree("yo", 4, "yo");

        //level_5 AnswersQuizThree
        AnswersQuizThree a39 = new AnswersQuizThree("ra", 5, "ra");
        AnswersQuizThree a40 = new AnswersQuizThree("ri", 5, "ri");
        AnswersQuizThree a41 = new AnswersQuizThree("ru", 5, "ru");
        AnswersQuizThree a42 = new AnswersQuizThree("re", 5, "re");
        AnswersQuizThree a43 = new AnswersQuizThree("ro", 5, "ro");
        AnswersQuizThree a44 = new AnswersQuizThree("wa", 5, "wa");
        AnswersQuizThree a45 = new AnswersQuizThree("wo", 5, "wo");
        AnswersQuizThree a46 = new AnswersQuizThree("n", 5, "n");

        insertAnswersQuizThree(a1); insertAnswersQuizThree(a2); insertAnswersQuizThree(a3); insertAnswersQuizThree(a4); insertAnswersQuizThree(a5);
        insertAnswersQuizThree(a6); insertAnswersQuizThree(a7); insertAnswersQuizThree(a8); insertAnswersQuizThree(a9); insertAnswersQuizThree(a10);
        insertAnswersQuizThree(a11); insertAnswersQuizThree(a12); insertAnswersQuizThree(a13); insertAnswersQuizThree(a14); insertAnswersQuizThree(a15);
        insertAnswersQuizThree(a16); insertAnswersQuizThree(a17); insertAnswersQuizThree(a18); insertAnswersQuizThree(a19); insertAnswersQuizThree(a20);
        insertAnswersQuizThree(a21); insertAnswersQuizThree(a22); insertAnswersQuizThree(a23); insertAnswersQuizThree(a24); insertAnswersQuizThree(a25);
        insertAnswersQuizThree(a26); insertAnswersQuizThree(a27); insertAnswersQuizThree(a28); insertAnswersQuizThree(a29); insertAnswersQuizThree(a30);
        insertAnswersQuizThree(a31); insertAnswersQuizThree(a32); insertAnswersQuizThree(a33); insertAnswersQuizThree(a34); insertAnswersQuizThree(a35);
        insertAnswersQuizThree(a36); insertAnswersQuizThree(a37); insertAnswersQuizThree(a38); insertAnswersQuizThree(a39); insertAnswersQuizThree(a40);
        insertAnswersQuizThree(a41); insertAnswersQuizThree(a42); insertAnswersQuizThree(a43); insertAnswersQuizThree(a44); insertAnswersQuizThree(a45);
        insertAnswersQuizThree(a46);
    }

    private void fillScoreTable() {
        Score s1 = new Score(1, 1, 0);
        Score s2 = new Score(2, 1, 0);
        Score s3 = new Score(3, 1, 0);
        Score s4 = new Score(4, 1, 0);
        Score s5 = new Score(5, 1, 0);

        Score s6 = new Score(1, 2, 0);
        Score s7 = new Score(2, 2, 0);
        Score s8 = new Score(3, 2, 0);
        Score s9 = new Score(4, 2, 0);
        Score s10 = new Score(5, 2, 0);

        Score s11 = new Score(1, 3, 0);
        Score s12 = new Score(2, 3, 0);
        Score s13 = new Score(3, 3, 0);
        Score s14 = new Score(4, 3, 0);
        Score s15 = new Score(5, 3, 0);

        insertScores(s1); insertScores(s2); insertScores(s3); insertScores(s4); insertScores(s5);
        insertScores(s6); insertScores(s7); insertScores(s8); insertScores(s9); insertScores(s10);
        insertScores(s11); insertScores(s12); insertScores(s13); insertScores(s14); insertScores(s15);
    }

    private void insertQuestions(QuestionQuizOne questionQuizOne) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COD_RASPUNS_CORECT, questionQuizOne.getCod_raspuns_corect());
        cv.put(QuestionTable.NIVEL, questionQuizOne.getNivel());
        cv.put(QuestionTable.DRAWABLE_RES, questionQuizOne.getDrawable_res());
        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    private void insertQuestionsQuizTwo(QuestionQuizTwo questionQuizTwo) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTableQuiz2.COD_RASPUNS_CORECT, questionQuizTwo.getCod_raspuns_corect());
        cv.put(QuestionTableQuiz2.NIVEL, questionQuizTwo.getNivel());
        cv.put(QuestionTableQuiz2.TEXT_INTREBARE, questionQuizTwo.getText_intrebare());
        db.insert(QuestionTableQuiz2.TABLE_NAME, null, cv);
    }

    private void insertQuestionsQuizThree(QuestionQuizThree questionQuizThree) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTableQuiz3.COD_RASPUNS_CORECT, questionQuizThree.getCod_raspuns_corect());
        cv.put(QuestionTableQuiz3.NIVEL, questionQuizThree.getNivel());
        cv.put(QuestionTableQuiz3.DRAWABLE_RES, questionQuizThree.getDrawable_res());
        db.insert(QuestionTableQuiz3.TABLE_NAME, null, cv);
    }

    private void insertGameOne(GameOne game) {
        ContentValues cv = new ContentValues();
        cv.put(GameOneTable.COD_RASPUNS_CORECT, game.getCod_raspuns_corect());
        cv.put(GameOneTable.NIVEL, game.getNivel());
        db.insert(GameOneTable.TABLE_NAME, null, cv);
    }

    private void insertAnswers(Answers answers){
        ContentValues cv = new ContentValues();
        cv.put(AnswerTable.COD_RASPUNS, answers.getCod_raspuns());
        cv.put(AnswerTable.NIVEL, answers.getNivel());
        cv.put(AnswerTable.TEXT_RASPUNS, answers.getText_raspuns());
        db.insert(AnswerTable.TABLE_NAME, null, cv);
    }

    private void insertAnswersQuizTwo(AnswersQuizTwo answers){
        ContentValues cv = new ContentValues();
        cv.put(AnswerTableQuiz2.COD_RASPUNS, answers.getCod_raspuns());
        cv.put(AnswerTableQuiz2.NIVEL, answers.getNivel());
        cv.put(AnswerTableQuiz2.DRAWABLE_RES, answers.getDrawable_res());
        db.insert(AnswerTableQuiz2.TABLE_NAME, null, cv);
    }

    private void insertAnswersQuizThree(AnswersQuizThree answers){
        ContentValues cv = new ContentValues();
        cv.put(AnswerTableQuiz3.COD_RASPUNS, answers.getCod_raspuns());
        cv.put(AnswerTableQuiz3.NIVEL, answers.getNivel());
        cv.put(AnswerTableQuiz3.DRAWABLE_RES, answers.getDrawable_res());
        db.insert(AnswerTableQuiz3.TABLE_NAME, null, cv);
    }

    public void insertScores(Score score) {
        ContentValues cv = new ContentValues();

        cv.put(ScoreTable.NIVEL, score.getLevel());
        cv.put(ScoreTable.QUIZ, score.getQuiz());
        cv.put(ScoreTable.SCORE, score.getScore());

        db.insert(ScoreTable.TABLE_NAME, null, cv);
    }

    public int getScoreID (int level, int quiz) {
        db = this.getReadableDatabase();
        int id = 0;
        String query = "SELECT " + ScoreTable.QUIZ + ", " + ScoreTable.NIVEL + ", " + ScoreTable._ID + " FROM " +
                ScoreTable.TABLE_NAME + ";";
        int l, q;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                l = cursor.getInt(cursor.getColumnIndex(ScoreTable.NIVEL));
                q = cursor.getInt(cursor.getColumnIndex(ScoreTable.QUIZ));

                if(l == level && q == quiz) {
                    id = cursor.getInt(cursor.getColumnIndex(ScoreTable._ID));
                    break;
                }
            } while(cursor.moveToNext());
        }

        return id;
    }

    public int updateScore(Score score, String id) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ScoreTable.SCORE, score.getScore());

        return db.update(ScoreTable.TABLE_NAME, cv, ScoreTable._ID+"=?", new String[]{id});
    }

    public int getScore(int level, int quiz) {
        int score = 0;
        db =this.getReadableDatabase();
        String query = "SELECT " + ScoreTable.SCORE + " FROM " + ScoreTable.TABLE_NAME + " WHERE " +
                AnswerTable.NIVEL + "=" + level + " AND " + ScoreTable.QUIZ + " = " + quiz + ";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToNext()){
            do{
                score = cursor.getInt(cursor.getColumnIndex(ScoreTable.SCORE));
            } while (cursor.moveToNext());
        }

        return score;
    }

    public ArrayList<QuestionQuizOne> getAllQuestionQuizOne_levelOne() {
        ArrayList<QuestionQuizOne> questionQuizOneList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTable.TABLE_NAME + " WHERE " + QuestionTable.NIVEL + "=" + 1 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizOne questionQuizOne = new QuestionQuizOne();
                questionQuizOne.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTable.COD_RASPUNS_CORECT)));
                questionQuizOne.setDrawable_res(cursor.getString(cursor.getColumnIndex(QuestionTable.DRAWABLE_RES)));
                questionQuizOne.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTable.NIVEL)));

                questionQuizOneList.add(questionQuizOne);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizOneList;
    }

    public ArrayList<QuestionQuizOne> getAllQuestionQuizOne_levelTwo() {
        ArrayList<QuestionQuizOne> questionQuizOneList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTable.TABLE_NAME + " WHERE " + QuestionTable.NIVEL + "=" + 2 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizOne questionQuizOne = new QuestionQuizOne();
                questionQuizOne.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTable.COD_RASPUNS_CORECT)));
                questionQuizOne.setDrawable_res(cursor.getString(cursor.getColumnIndex(QuestionTable.DRAWABLE_RES)));
                questionQuizOne.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTable.NIVEL)));

                questionQuizOneList.add(questionQuizOne);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizOneList;
    }

    public ArrayList<QuestionQuizOne> getAllQuestionQuizOne_levelThree() {
        ArrayList<QuestionQuizOne> questionQuizOneList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTable.TABLE_NAME + " WHERE " + QuestionTable.NIVEL + "=" + 3 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizOne questionQuizOne = new QuestionQuizOne();
                questionQuizOne.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTable.COD_RASPUNS_CORECT)));
                questionQuizOne.setDrawable_res(cursor.getString(cursor.getColumnIndex(QuestionTable.DRAWABLE_RES)));
                questionQuizOne.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTable.NIVEL)));

                questionQuizOneList.add(questionQuizOne);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizOneList;
    }

    public ArrayList<QuestionQuizOne> getAllQuestionQuizOne_levelFour() {
        ArrayList<QuestionQuizOne> questionQuizOneList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTable.TABLE_NAME + " WHERE " + QuestionTable.NIVEL + "=" + 4 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizOne questionQuizOne = new QuestionQuizOne();
                questionQuizOne.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTable.COD_RASPUNS_CORECT)));
                questionQuizOne.setDrawable_res(cursor.getString(cursor.getColumnIndex(QuestionTable.DRAWABLE_RES)));
                questionQuizOne.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTable.NIVEL)));

                questionQuizOneList.add(questionQuizOne);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizOneList;
    }

    public ArrayList<QuestionQuizOne> getAllQuestionQuizOne_levelFive() {
        ArrayList<QuestionQuizOne> questionQuizOneList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTable.TABLE_NAME + " WHERE " + QuestionTable.NIVEL + "=" + 5 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizOne questionQuizOne = new QuestionQuizOne();
                questionQuizOne.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTable.COD_RASPUNS_CORECT)));
                questionQuizOne.setDrawable_res(cursor.getString(cursor.getColumnIndex(QuestionTable.DRAWABLE_RES)));
                questionQuizOne.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTable.NIVEL)));

                questionQuizOneList.add(questionQuizOne);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizOneList;
    }

//-----------------------------------------------------------------------------------
    public ArrayList<QuestionQuizTwo> getAllQuestionQuizTwo_levelOne() {
        ArrayList<QuestionQuizTwo> questionQuizTwoList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTableQuiz2.TABLE_NAME + " WHERE " + QuestionTableQuiz2.NIVEL + "=" + 1 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizTwo questionQuizTwo = new QuestionQuizTwo();
                questionQuizTwo.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTableQuiz2.COD_RASPUNS_CORECT)));
                questionQuizTwo.setText_intrebare(cursor.getString(cursor.getColumnIndex(QuestionTableQuiz2.TEXT_INTREBARE)));
                questionQuizTwo.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTableQuiz2.NIVEL)));

                questionQuizTwoList.add(questionQuizTwo);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizTwoList;
    }

    public ArrayList<QuestionQuizTwo> getAllQuestionQuizTwo_levelTwo() {
        ArrayList<QuestionQuizTwo> questionQuizTwoList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTableQuiz2.TABLE_NAME + " WHERE " + QuestionTableQuiz2.NIVEL + "=" + 2 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizTwo questionQuizTwo = new QuestionQuizTwo();
                questionQuizTwo.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTableQuiz2.COD_RASPUNS_CORECT)));
                questionQuizTwo.setText_intrebare(cursor.getString(cursor.getColumnIndex(QuestionTableQuiz2.TEXT_INTREBARE)));
                questionQuizTwo.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTableQuiz2.NIVEL)));

                questionQuizTwoList.add(questionQuizTwo);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizTwoList;
    }

    public ArrayList<QuestionQuizTwo> getAllQuestionQuizTwo_levelThree() {
        ArrayList<QuestionQuizTwo> questionQuizTwoList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTableQuiz2.TABLE_NAME + " WHERE " + QuestionTableQuiz2.NIVEL + "=" + 3 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizTwo questionQuizTwo = new QuestionQuizTwo();
                questionQuizTwo.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTableQuiz2.COD_RASPUNS_CORECT)));
                questionQuizTwo.setText_intrebare(cursor.getString(cursor.getColumnIndex(QuestionTableQuiz2.TEXT_INTREBARE)));
                questionQuizTwo.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTableQuiz2.NIVEL)));

                questionQuizTwoList.add(questionQuizTwo);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizTwoList;
    }

    public ArrayList<QuestionQuizTwo> getAllQuestionQuizTwo_levelFour() {
        ArrayList<QuestionQuizTwo> questionQuizTwoList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTableQuiz2.TABLE_NAME + " WHERE " + QuestionTableQuiz2.NIVEL + "=" + 4 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizTwo questionQuizTwo = new QuestionQuizTwo();
                questionQuizTwo.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTableQuiz2.COD_RASPUNS_CORECT)));
                questionQuizTwo.setText_intrebare(cursor.getString(cursor.getColumnIndex(QuestionTableQuiz2.TEXT_INTREBARE)));
                questionQuizTwo.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTableQuiz2.NIVEL)));

                questionQuizTwoList.add(questionQuizTwo);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizTwoList;
    }

    public ArrayList<QuestionQuizTwo> getAllQuestionQuizTwo_levelFive() {
        ArrayList<QuestionQuizTwo> questionQuizTwoList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTableQuiz2.TABLE_NAME + " WHERE " + QuestionTableQuiz2.NIVEL + "=" + 5 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizTwo questionQuizTwo = new QuestionQuizTwo();
                questionQuizTwo.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTableQuiz2.COD_RASPUNS_CORECT)));
                questionQuizTwo.setText_intrebare(cursor.getString(cursor.getColumnIndex(QuestionTableQuiz2.TEXT_INTREBARE)));
                questionQuizTwo.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTableQuiz2.NIVEL)));

                questionQuizTwoList.add(questionQuizTwo);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizTwoList;
    }

//----------------------------------------------------------------------------------
    public ArrayList<QuestionQuizThree> getAllQuestionQuizThree_levelOne() {
        ArrayList<QuestionQuizThree> questionQuizThreeList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTableQuiz3.TABLE_NAME + " WHERE " + QuestionTableQuiz3.NIVEL + "=" + 1 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizThree questionQuizThree = new QuestionQuizThree();
                questionQuizThree.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTable.COD_RASPUNS_CORECT)));
                questionQuizThree.setDrawable_res(cursor.getString(cursor.getColumnIndex(QuestionTable.DRAWABLE_RES)));
                questionQuizThree.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTable.NIVEL)));

                questionQuizThreeList.add(questionQuizThree);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizThreeList;
    }

    public ArrayList<QuestionQuizThree> getAllQuestionQuizThree_levelTwo() {
        ArrayList<QuestionQuizThree> questionQuizThreeList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTableQuiz3.TABLE_NAME + " WHERE " + QuestionTableQuiz3.NIVEL + "=" + 2 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizThree questionQuizThree = new QuestionQuizThree();
                questionQuizThree.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTable.COD_RASPUNS_CORECT)));
                questionQuizThree.setDrawable_res(cursor.getString(cursor.getColumnIndex(QuestionTable.DRAWABLE_RES)));
                questionQuizThree.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTable.NIVEL)));

                questionQuizThreeList.add(questionQuizThree);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizThreeList;
    }

    public ArrayList<QuestionQuizThree> getAllQuestionQuizThree_levelThree() {
        ArrayList<QuestionQuizThree> questionQuizThreeList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTableQuiz3.TABLE_NAME + " WHERE " + QuestionTableQuiz3.NIVEL + "=" + 3 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizThree questionQuizThree = new QuestionQuizThree();
                questionQuizThree.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTable.COD_RASPUNS_CORECT)));
                questionQuizThree.setDrawable_res(cursor.getString(cursor.getColumnIndex(QuestionTable.DRAWABLE_RES)));
                questionQuizThree.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTable.NIVEL)));

                questionQuizThreeList.add(questionQuizThree);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizThreeList;
    }

    public ArrayList<QuestionQuizThree> getAllQuestionQuizThree_levelFour() {
        ArrayList<QuestionQuizThree> questionQuizThreeList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTableQuiz3.TABLE_NAME + " WHERE " + QuestionTableQuiz3.NIVEL + "=" + 4 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizThree questionQuizThree = new QuestionQuizThree();
                questionQuizThree.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTable.COD_RASPUNS_CORECT)));
                questionQuizThree.setDrawable_res(cursor.getString(cursor.getColumnIndex(QuestionTable.DRAWABLE_RES)));
                questionQuizThree.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTable.NIVEL)));

                questionQuizThreeList.add(questionQuizThree);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizThreeList;
    }

    public ArrayList<QuestionQuizThree> getAllQuestionQuizThree_levelFive() {
        ArrayList<QuestionQuizThree> questionQuizThreeList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + QuestionTableQuiz3.TABLE_NAME + " WHERE " + QuestionTableQuiz3.NIVEL + "=" + 5 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                QuestionQuizThree questionQuizThree = new QuestionQuizThree();
                questionQuizThree.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(QuestionTable.COD_RASPUNS_CORECT)));
                questionQuizThree.setDrawable_res(cursor.getString(cursor.getColumnIndex(QuestionTable.DRAWABLE_RES)));
                questionQuizThree.setNivel(cursor.getInt(cursor.getColumnIndex(QuestionTable.NIVEL)));

                questionQuizThreeList.add(questionQuizThree);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return questionQuizThreeList;
    }

//-----------------------------------------------------------------------------------

    public ArrayList<GameOne> getAllGameOne_incepator() {
        ArrayList<GameOne> gameOneList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + GameOneTable.TABLE_NAME + " WHERE " + GameOneTable.NIVEL + " = " + 1 + ";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                GameOne game = new GameOne();
                game.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(GameOneTable.COD_RASPUNS_CORECT)));
                game.setNivel(cursor.getInt(cursor.getColumnIndex(GameOneTable.NIVEL)));

                gameOneList.add(game);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return gameOneList;
    }

//-----------------------------------------------------------------------------------

    public ArrayList<GameOne> getAllGameOne_mediu() {
        ArrayList<GameOne> gameOneList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + GameOneTable.TABLE_NAME + " WHERE " + GameOneTable.NIVEL + " = " + 2 + ";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                GameOne game = new GameOne();
                game.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(GameOneTable.COD_RASPUNS_CORECT)));
                game.setNivel(cursor.getInt(cursor.getColumnIndex(GameOneTable.NIVEL)));

                gameOneList.add(game);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return gameOneList;
    }

//-----------------------------------------------------------------------------------

    public ArrayList<GameOne> getAllGameOne_avansat() {
        ArrayList<GameOne> gameOneList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + GameOneTable.TABLE_NAME + " WHERE " + GameOneTable.NIVEL + " = " + 3 + ";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            do {
                GameOne game = new GameOne();
                game.setCod_raspuns_corect(cursor.getString(cursor.getColumnIndex(GameOneTable.COD_RASPUNS_CORECT)));
                game.setNivel(cursor.getInt(cursor.getColumnIndex(GameOneTable.NIVEL)));

                gameOneList.add(game);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return gameOneList;
    }

//-----------------------------------------------------------------------------------

    public ArrayList<Answers> getAllAnswers_levelOne() {
        ArrayList<Answers> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTable.TABLE_NAME + " WHERE " + AnswerTable.NIVEL + "=" + 1 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                Answers answers = new Answers();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTable.COD_RASPUNS)));
                answers.setText_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTable.TEXT_RASPUNS)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTable.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<Answers> getAllAnswers_levelTwo() {
        ArrayList<Answers> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTable.TABLE_NAME + " WHERE " + AnswerTable.NIVEL + "=" + 2 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                Answers answers = new Answers();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTable.COD_RASPUNS)));
                answers.setText_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTable.TEXT_RASPUNS)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTable.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<Answers> getAllAnswers_levelThree() {
        ArrayList<Answers> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTable.TABLE_NAME + " WHERE " + AnswerTable.NIVEL + "=" + 3 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                Answers answers = new Answers();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTable.COD_RASPUNS)));
                answers.setText_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTable.TEXT_RASPUNS)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTable.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<Answers> getAllAnswers_levelFour() {
        ArrayList<Answers> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTable.TABLE_NAME + " WHERE " + AnswerTable.NIVEL + "=" + 4 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                Answers answers = new Answers();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTable.COD_RASPUNS)));
                answers.setText_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTable.TEXT_RASPUNS)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTable.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<Answers> getAllAnswers_levelFive() {
        ArrayList<Answers> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTable.TABLE_NAME + " WHERE " + AnswerTable.NIVEL + "=" + 5 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                Answers answers = new Answers();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTable.COD_RASPUNS)));
                answers.setText_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTable.TEXT_RASPUNS)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTable.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

//----------------------------------------------------------------------------------------
    public ArrayList<AnswersQuizTwo> getAllAnswersQ2_levelOne() {
        ArrayList<AnswersQuizTwo> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTableQuiz2.TABLE_NAME + " WHERE " + AnswerTableQuiz2.NIVEL + "=" + 1 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                AnswersQuizTwo answers = new AnswersQuizTwo();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz2.COD_RASPUNS)));
                answers.setDrawable_res(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz2.DRAWABLE_RES)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTableQuiz2.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<AnswersQuizTwo> getAllAnswersQ2_levelTwo() {
        ArrayList<AnswersQuizTwo> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTableQuiz2.TABLE_NAME + " WHERE " + AnswerTableQuiz2.NIVEL + "=" + 2 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                AnswersQuizTwo answers = new AnswersQuizTwo();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz2.COD_RASPUNS)));
                answers.setDrawable_res(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz2.DRAWABLE_RES)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTableQuiz2.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<AnswersQuizTwo> getAllAnswersQ2_levelThree() {ArrayList<AnswersQuizTwo> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTableQuiz2.TABLE_NAME + " WHERE " + AnswerTableQuiz2.NIVEL + "=" + 3 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                AnswersQuizTwo answers = new AnswersQuizTwo();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz2.COD_RASPUNS)));
                answers.setDrawable_res(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz2.DRAWABLE_RES)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTableQuiz2.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<AnswersQuizTwo> getAllAnswersQ2_levelFour() {
        ArrayList<AnswersQuizTwo> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTableQuiz2.TABLE_NAME + " WHERE " + AnswerTableQuiz2.NIVEL + "=" + 4 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                AnswersQuizTwo answers = new AnswersQuizTwo();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz2.COD_RASPUNS)));
                answers.setDrawable_res(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz2.DRAWABLE_RES)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTableQuiz2.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<AnswersQuizTwo> getAllAnswersQ2_levelFive() {
        ArrayList<AnswersQuizTwo> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTableQuiz2.TABLE_NAME + " WHERE " + AnswerTableQuiz2.NIVEL + "=" + 5 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                AnswersQuizTwo answers = new AnswersQuizTwo();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz2.COD_RASPUNS)));
                answers.setDrawable_res(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz2.DRAWABLE_RES)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTableQuiz2.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

//-----------------------------------------------------------------------
    public ArrayList<AnswersQuizThree> getAllAnswersQ3_levelOne() {
        ArrayList<AnswersQuizThree> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTableQuiz3.TABLE_NAME + " WHERE " + AnswerTableQuiz3.NIVEL + "=" + 1 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                AnswersQuizThree answers = new AnswersQuizThree();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz3.COD_RASPUNS)));
                answers.setDrawable_res(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz3.DRAWABLE_RES)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTableQuiz3.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<AnswersQuizThree> getAllAnswersQ3_levelTwo() {
        ArrayList<AnswersQuizThree> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTableQuiz3.TABLE_NAME + " WHERE " + AnswerTableQuiz3.NIVEL + "=" + 2 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                AnswersQuizThree answers = new AnswersQuizThree();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz3.COD_RASPUNS)));
                answers.setDrawable_res(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz3.DRAWABLE_RES)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTableQuiz3.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<AnswersQuizThree> getAllAnswersQ3_levelThree() {
        ArrayList<AnswersQuizThree> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTableQuiz3.TABLE_NAME + " WHERE " + AnswerTableQuiz3.NIVEL + "=" + 3 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                AnswersQuizThree answers = new AnswersQuizThree();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz3.COD_RASPUNS)));
                answers.setDrawable_res(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz3.DRAWABLE_RES)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTableQuiz3.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<AnswersQuizThree> getAllAnswersQ3_levelFour() {
        ArrayList<AnswersQuizThree> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTableQuiz3.TABLE_NAME + " WHERE " + AnswerTableQuiz3.NIVEL + "=" + 4 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                AnswersQuizThree answers = new AnswersQuizThree();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz3.COD_RASPUNS)));
                answers.setDrawable_res(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz3.DRAWABLE_RES)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTableQuiz3.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    public ArrayList<AnswersQuizThree> getAllAnswersQ3_levelFive() {
        ArrayList<AnswersQuizThree> answersList = new ArrayList<>();
        db = getReadableDatabase();
        String query = "SELECT * FROM " + AnswerTableQuiz3.TABLE_NAME + " WHERE " + AnswerTableQuiz3.NIVEL + "=" + 5 +";";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                AnswersQuizThree answers = new AnswersQuizThree();
                answers.setCod_raspuns(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz3.COD_RASPUNS)));
                answers.setDrawable_res(cursor.getString(cursor.getColumnIndex(AnswerTableQuiz3.DRAWABLE_RES)));
                answers.setNivel(cursor.getInt(cursor.getColumnIndex(AnswerTableQuiz3.NIVEL)));

                answersList.add(answers);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return answersList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_QUESTIONS_TABLE);
            db.execSQL(DROP_ANSWERS_TABLE);
            db.execSQL(DROP_SCORES_TABLE);
            db.execSQL(DROP_QUESTIONS_TABLE_QUIZ2);
            db.execSQL(DROP_ANSWERS_TABLE_QUIZ2);
            db.execSQL(DROP_QUESTIONS_TABLE_QUIZ3);
            db.execSQL(DROP_ANSWERS_TABLE_QUIZ3);
            db.execSQL(DROP_GAME_ONE_TABLE);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

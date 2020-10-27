package com.example.japanase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.japanase.Classes.Answers;
import com.example.japanase.Classes.DBHelper;
import com.example.japanase.Classes.QuestionQuizOne;
import com.example.japanase.Classes.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class LevelQuizOneActivity extends AppCompatActivity{

    Button btn_back_to_q1_from_level1, btn_next;
    TextView txt_time, txt_score, txt_questionCount;
    TextView txt_op1, txt_op2, txt_op3, txt_op4;
    public static CircleImageView img_quizHiragana;
    ImageView img_answer;

    private ArrayList<QuestionQuizOne> questionQuizOneList_levelOne, questionQuizOneList_levelTwo, questionQuizOneList_levelThree, questionQuizOneList_levelFour, questionQuizOneList_levelFive;
    private ArrayList<Answers> answersList_levelOne, answersList_levelTwo, answersList_levelThree, answersList_levelFour, answersList_levelFive;
    DBHelper dbHelper;
    private int questionCounter = 0;
    private int questionCountTotal = 0;
    private QuestionQuizOne currentQuestion;
    private int score = 0;

    private static final long COUNTDOWN_IN_MILLIS_LEVEL_ONE_QUIZ_ONE = 60000;
    private static final long COUNTDOWN_IN_MILLIS_LEVEL_TWO_QUIZ_ONE = 90000;
    private static final long COUNTDOWN_IN_MILLIS_LEVEL_THREE_QUIZ_ONE = 120000;
    private static final long COUNTDOWN_IN_MILLIS_LEVEL_FOUR_QUIZ_ONE = 150000;
    private static final long COUNTDOWN_IN_MILLIS_LEVEL_FIVE_QUIZ_ONE = 180000;
    private ColorStateList txtColor;

    private CountDownTimer countDownTimer;
    private long timeRemaining;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LevelQuizOneActivity.this);
        builder.setMessage("Ești sigur că vrei să renunți? Tot progresul tău se va pierde");
        builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                timeRemaining = 0;
                if(countDownTimer != null)
                    countDownTimer.cancel();
                Intent intent1 = new Intent(LevelQuizOneActivity.this, LevelListActivity.class);
                startActivity(intent1);
            }
        });

        builder.setNegativeButton("NU", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Renunț");
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level_quiz_one);

        btn_back_to_q1_from_level1 = (Button)findViewById(R.id.btn_back_to_q1_from_level1);
        txt_time = (TextView)findViewById(R.id.txt_time);
        txt_questionCount = (TextView)findViewById(R.id.txt_questionCount);
        txt_score = (TextView)findViewById(R.id.txt_score);
        txt_op1 = (TextView)findViewById(R.id.txt_optionOne);
        txt_op2 = (TextView)findViewById(R.id.txt_optionTwo);
        txt_op3 = (TextView)findViewById(R.id.txt_optionThree);
        txt_op4 = (TextView)findViewById(R.id.txt_optionFour);
        img_quizHiragana = (CircleImageView)findViewById(R.id.img_quizHiragana);
        img_answer = (ImageView)findViewById(R.id.img_answer);
        btn_next = (Button)findViewById(R.id.btn_next);

        txt_score.setText("Punctaj: " + score);

        txtColor = txt_time.getTextColors();

        dbHelper = new DBHelper(LevelQuizOneActivity.this);
        //lista de intrebari pe nivele
        questionQuizOneList_levelOne = dbHelper.getAllQuestionQuizOne_levelOne();
        questionQuizOneList_levelTwo = dbHelper.getAllQuestionQuizOne_levelTwo();
        questionQuizOneList_levelThree = dbHelper.getAllQuestionQuizOne_levelThree();
        questionQuizOneList_levelFour = dbHelper.getAllQuestionQuizOne_levelFour();
        questionQuizOneList_levelFive = dbHelper.getAllQuestionQuizOne_levelFive();
        //lista de raspunsuri pe nivele
        answersList_levelOne = dbHelper.getAllAnswers_levelOne();
        answersList_levelTwo = dbHelper.getAllAnswers_levelTwo();
        answersList_levelThree = dbHelper.getAllAnswers_levelThree();
        answersList_levelFour = dbHelper.getAllAnswers_levelFour();
        answersList_levelFive = dbHelper.getAllAnswers_levelFive();

        btn_back_to_q1_from_level1.getBackground().setAlpha(1);
        btn_back_to_q1_from_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(LevelQuizOneActivity.this);
                builder.setMessage("Ești sigur că vrei să renunți? Tot progresul tău se va pierde");
                builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        timeRemaining = 0;
                        if(countDownTimer != null)
                            countDownTimer.cancel();
                        Intent intent1 = new Intent(LevelQuizOneActivity.this, LevelListActivity.class);
                        startActivity(intent1);
                    }
                });

                builder.setNegativeButton("NU", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Renunț");
                alertDialog.setCancelable(false);
                alertDialog.show();
            }

        });

        img_answer.setAlpha(0f);
        btn_next.getBackground().setAlpha(1);
        btn_next.setVisibility(View.GONE);
        img_answer.setVisibility(View.VISIBLE);


/* ================================== QUIZ 1 LEVEL 1 ================================== */
        if (LevelListActivity.level_type.equals("level1") && GameActivity.quiz_type.equals("quiz1")) {


            questionCountTotal = questionQuizOneList_levelOne.size();
            Collections.shuffle(questionQuizOneList_levelOne); //amestecam interbarile inter ele ca sa nu fie mereu aceleasi
            Collections.shuffle(answersList_levelOne);

            showNextQuestion_levelOne();

            startCountdown();

            btn_next.setVisibility(View.GONE);
            txt_op1.setClickable(true);
            txt_op2.setClickable(true);
            txt_op3.setClickable(true);
            txt_op4.setClickable(true);
            checkAnswers();
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNextQuestion_levelOne();
                    timeRemaining = 0;
                    if(btn_next.getText().equals("Finish")){
                        countDownTimer.cancel();
                    }
                }
            });
        }

/* ================================== QUIZ 1 LEVEL 2 ================================== */
        if (LevelListActivity.level_type.equals("level2") && GameActivity.quiz_type.equals("quiz1")) {

            Collections.shuffle(questionQuizOneList_levelOne);
            for (int i = 0; i<5; i++)
                questionQuizOneList_levelTwo.add(questionQuizOneList_levelOne.get(i));

            questionCountTotal = questionQuizOneList_levelTwo.size();
            Collections.shuffle(questionQuizOneList_levelTwo); //amestecam interbarile inter ele ca sa nu fie mereu aceleasi

            Collections.shuffle(answersList_levelTwo);

            showNextQuestion_levelTwo();

            startCountdown();

            btn_next.setVisibility(View.GONE);
            txt_op1.setClickable(true);
            txt_op2.setClickable(true);
            txt_op3.setClickable(true);
            txt_op4.setClickable(true);
            checkAnswers();
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNextQuestion_levelTwo();
                    timeRemaining = 0;
                    if(btn_next.getText().equals("Finish")){
                        countDownTimer.cancel();
                    }
                }
            });
        }

/* ================================== QUIZ 1 LEVEL 3 ================================== */
        if (LevelListActivity.level_type.equals("level3") && GameActivity.quiz_type.equals("quiz1")) {

            Collections.shuffle(questionQuizOneList_levelOne);
            for (int i = 0; i<5; i++)
                questionQuizOneList_levelThree.add(questionQuizOneList_levelOne.get(i));
            Collections.shuffle(questionQuizOneList_levelTwo);
            for (int i = 0; i<5; i++)
                questionQuizOneList_levelThree.add(questionQuizOneList_levelTwo.get(i));

            questionCountTotal = questionQuizOneList_levelThree.size();
            Collections.shuffle(questionQuizOneList_levelThree); //amestecam interbarile inter ele ca sa nu fie mereu aceleasi

            Collections.shuffle(answersList_levelThree);

            showNextQuestion_levelThree();

            startCountdown();

            btn_next.setVisibility(View.GONE);
            txt_op1.setClickable(true);
            txt_op2.setClickable(true);
            txt_op3.setClickable(true);
            txt_op4.setClickable(true);
            checkAnswers();
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNextQuestion_levelThree();
                    timeRemaining = 0;
                    if(btn_next.getText().equals("Finish")){
                        countDownTimer.cancel();
                    }
                }
            });
        }

/* ================================== QUIZ 1 LEVEL 4 ================================== */
        if (LevelListActivity.level_type.equals("level4") && GameActivity.quiz_type.equals("quiz1")) {

            Collections.shuffle(questionQuizOneList_levelOne);
            for (int i = 0; i<5; i++)
                questionQuizOneList_levelFour.add(questionQuizOneList_levelOne.get(i));
            Collections.shuffle(questionQuizOneList_levelTwo);
            for (int i = 0; i<6; i++)
                questionQuizOneList_levelFour.add(questionQuizOneList_levelTwo.get(i));
            Collections.shuffle(questionQuizOneList_levelThree);
            for (int i = 0; i<6; i++)
                questionQuizOneList_levelFour.add(questionQuizOneList_levelThree.get(i));

            questionCountTotal = questionQuizOneList_levelFour.size();
            Collections.shuffle(questionQuizOneList_levelFour); //amestecam interbarile inter ele ca sa nu fie mereu aceleasi

            Collections.shuffle(answersList_levelFour);

            showNextQuestion_levelFour();

            startCountdown();

            btn_next.setVisibility(View.GONE);
            txt_op1.setClickable(true);
            txt_op2.setClickable(true);
            txt_op3.setClickable(true);
            txt_op4.setClickable(true);
            checkAnswers();
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNextQuestion_levelFour();
                    timeRemaining = 0;
                    if(btn_next.getText().equals("Finish")){
                        countDownTimer.cancel();
                    }
                }
            });
        }

/* ================================== QUIZ 1 LEVEL 5 ================================== */
        if (LevelListActivity.level_type.equals("level5") && GameActivity.quiz_type.equals("quiz1")) {

            Collections.shuffle(questionQuizOneList_levelOne);
            for (int i = 0; i<5; i++)
                questionQuizOneList_levelFive.add(questionQuizOneList_levelOne.get(i));
            Collections.shuffle(questionQuizOneList_levelTwo);
            for (int i = 0; i<6; i++)
                questionQuizOneList_levelFive.add(questionQuizOneList_levelTwo.get(i));
            Collections.shuffle(questionQuizOneList_levelThree);
            for (int i = 0; i<6; i++)
                questionQuizOneList_levelFive.add(questionQuizOneList_levelThree.get(i));
            Collections.shuffle(questionQuizOneList_levelFour);
            for (int i = 0; i<5; i++)
                questionQuizOneList_levelFive.add(questionQuizOneList_levelFour.get(i));

            questionCountTotal = questionQuizOneList_levelFive.size();
            Collections.shuffle(questionQuizOneList_levelFive); //amestecam interbarile inter ele ca sa nu fie mereu aceleasi

            Collections.shuffle(answersList_levelFive);

            showNextQuestion_levelFive();

            startCountdown();

            btn_next.setVisibility(View.GONE);
            txt_op1.setClickable(true);
            txt_op2.setClickable(true);
            txt_op3.setClickable(true);
            txt_op4.setClickable(true);
            checkAnswers();
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNextQuestion_levelFive();
                    timeRemaining = 0;
                    if(btn_next.getText().equals("Finish")){
                        countDownTimer.cancel();
                    }
                }
            });
        }


    }

    private void showNextQuestion_levelOne() {

        timeRemaining = COUNTDOWN_IN_MILLIS_LEVEL_ONE_QUIZ_ONE;

        txt_op1.setClickable(true);
        txt_op2.setClickable(true);
        txt_op3.setClickable(true);
        txt_op4.setClickable(true);
        btn_next.setVisibility(View.VISIBLE);
        img_answer.setAlpha(0f);

        ArrayList<String> posibleAnswers = new ArrayList<>();

        if (questionCounter < questionCountTotal) {
            btn_next.setVisibility(View.GONE);
            currentQuestion = questionQuizOneList_levelOne.get(questionCounter);

            ArrayList<String> list = new ArrayList<>();
            for (Answers item : answersList_levelOne) {
                list.add(item.getText_raspuns());
            }
            list.remove(questionQuizOneList_levelOne.get(questionCounter).getCod_raspuns_corect());

            Collections.shuffle(list);

            posibleAnswers.add(list.get(0));
            posibleAnswers.add(list.get(1));
            posibleAnswers.add(list.get(2));
            posibleAnswers.add(questionQuizOneList_levelOne.get(questionCounter).getCod_raspuns_corect());
            Collections.shuffle(posibleAnswers);

            if(currentQuestion.getDrawable_res().equals("o")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("i")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res() + "1", "drawable", LevelQuizOneActivity.this.getPackageName()));

            txt_op1.setText(posibleAnswers.get(0));
            txt_op2.setText(posibleAnswers.get(1));
            txt_op3.setText(posibleAnswers.get(2));
            txt_op4.setText(posibleAnswers.get(3));

            questionCounter++;
            txt_questionCount.setText("Întrebare: " + questionCounter + "/" + questionCountTotal);

        } else {
            finishQuiz();
        }
    }

    private void showNextQuestion_levelTwo() {

        timeRemaining = COUNTDOWN_IN_MILLIS_LEVEL_TWO_QUIZ_ONE;

        txt_op1.setClickable(true);
        txt_op2.setClickable(true);
        txt_op3.setClickable(true);
        txt_op4.setClickable(true);
        btn_next.setVisibility(View.VISIBLE);
        img_answer.setAlpha(0f);

        ArrayList<String> posibleAnswers = new ArrayList<>();

        if (questionCounter < questionCountTotal) {
            btn_next.setVisibility(View.GONE);
            currentQuestion = questionQuizOneList_levelTwo.get(questionCounter);

            ArrayList<String> list = new ArrayList<>();
            Collections.shuffle(answersList_levelOne);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelOne.get(i).getText_raspuns());

            for (Answers item : answersList_levelTwo) {
                list.add(item.getText_raspuns());
            }
            list.remove(questionQuizOneList_levelTwo.get(questionCounter).getCod_raspuns_corect());

            Collections.shuffle(list);

            posibleAnswers.add(list.get(0));
            posibleAnswers.add(list.get(1));
            posibleAnswers.add(list.get(2));
            posibleAnswers.add(questionQuizOneList_levelTwo.get(questionCounter).getCod_raspuns_corect());
            Collections.shuffle(posibleAnswers);

            if(currentQuestion.getDrawable_res().equals("o")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("i")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("so")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res() + "1", "drawable", LevelQuizOneActivity.this.getPackageName()));

            txt_op1.setText(posibleAnswers.get(0));
            txt_op2.setText(posibleAnswers.get(1));
            txt_op3.setText(posibleAnswers.get(2));
            txt_op4.setText(posibleAnswers.get(3));

            questionCounter++;
            txt_questionCount.setText("Întrebare: " + questionCounter + "/" + questionCountTotal);

        } else {
            finishQuiz();
        }
    }

    private void showNextQuestion_levelThree() {

        timeRemaining = COUNTDOWN_IN_MILLIS_LEVEL_THREE_QUIZ_ONE;

        txt_op1.setClickable(true);
        txt_op2.setClickable(true);
        txt_op3.setClickable(true);
        txt_op4.setClickable(true);
        btn_next.setVisibility(View.VISIBLE);
        img_answer.setAlpha(0f);

        ArrayList<String> posibleAnswers = new ArrayList<>();

        if (questionCounter < questionCountTotal) {
            btn_next.setVisibility(View.GONE);
            currentQuestion = questionQuizOneList_levelThree.get(questionCounter);

            ArrayList<String> list = new ArrayList<>();
            Collections.shuffle(answersList_levelOne);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelOne.get(i).getText_raspuns());
            Collections.shuffle(answersList_levelTwo);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelTwo.get(i).getText_raspuns());

            for (Answers item : answersList_levelThree) {
                list.add(item.getText_raspuns());
            }
            list.remove(questionQuizOneList_levelThree.get(questionCounter).getCod_raspuns_corect());

            Collections.shuffle(list);

            posibleAnswers.add(list.get(0));
            posibleAnswers.add(list.get(1));
            posibleAnswers.add(list.get(2));
            posibleAnswers.add(questionQuizOneList_levelThree.get(questionCounter).getCod_raspuns_corect());
            Collections.shuffle(posibleAnswers);

            if(currentQuestion.getDrawable_res().equals("o")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("i")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("so")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("ne")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("hi")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res() + "1", "drawable", LevelQuizOneActivity.this.getPackageName()));

            txt_op1.setText(posibleAnswers.get(0));
            txt_op2.setText(posibleAnswers.get(1));
            txt_op3.setText(posibleAnswers.get(2));
            txt_op4.setText(posibleAnswers.get(3));

            questionCounter++;
            txt_questionCount.setText("Întrebare: " + questionCounter + "/" + questionCountTotal);

        } else {
            finishQuiz();
        }
    }

    private void showNextQuestion_levelFour() {

        timeRemaining = COUNTDOWN_IN_MILLIS_LEVEL_FOUR_QUIZ_ONE;

        txt_op1.setClickable(true);
        txt_op2.setClickable(true);
        txt_op3.setClickable(true);
        txt_op4.setClickable(true);
        btn_next.setVisibility(View.VISIBLE);
        img_answer.setAlpha(0f);

        ArrayList<String> posibleAnswers = new ArrayList<>();

        if (questionCounter < questionCountTotal) {
            btn_next.setVisibility(View.GONE);
            currentQuestion = questionQuizOneList_levelFour.get(questionCounter);

            ArrayList<String> list = new ArrayList<>();
            Collections.shuffle(answersList_levelOne);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelOne.get(i).getText_raspuns());
            Collections.shuffle(answersList_levelTwo);
            for (int i = 0; i< 6; i++)
                list.add(answersList_levelTwo.get(i).getText_raspuns());
            Collections.shuffle(answersList_levelThree);
            for (int i = 0; i< 6; i++)
                list.add(answersList_levelThree.get(i).getText_raspuns());

            for (Answers item : answersList_levelFour) {
                list.add(item.getText_raspuns());
            }
            list.remove(questionQuizOneList_levelFour.get(questionCounter).getCod_raspuns_corect());

            Collections.shuffle(list);

            posibleAnswers.add(list.get(0));
            posibleAnswers.add(list.get(1));
            posibleAnswers.add(list.get(2));
            posibleAnswers.add(questionQuizOneList_levelFour.get(questionCounter).getCod_raspuns_corect());
            Collections.shuffle(posibleAnswers);

            if(currentQuestion.getDrawable_res().equals("o")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("i")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("so")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("ne")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("hi")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("mo")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res() + "1", "drawable", LevelQuizOneActivity.this.getPackageName()));

            txt_op1.setText(posibleAnswers.get(0));
            txt_op2.setText(posibleAnswers.get(1));
            txt_op3.setText(posibleAnswers.get(2));
            txt_op4.setText(posibleAnswers.get(3));

            questionCounter++;
            txt_questionCount.setText("Întrebare: " + questionCounter + "/" + questionCountTotal);

        } else {
            finishQuiz();
        }
    }

    private void showNextQuestion_levelFive() {

        timeRemaining = COUNTDOWN_IN_MILLIS_LEVEL_FIVE_QUIZ_ONE;

        txt_op1.setClickable(true);
        txt_op2.setClickable(true);
        txt_op3.setClickable(true);
        txt_op4.setClickable(true);
        btn_next.setVisibility(View.VISIBLE);
        img_answer.setAlpha(0f);

        ArrayList<String> posibleAnswers = new ArrayList<>();

        if (questionCounter < questionCountTotal) {
            btn_next.setVisibility(View.GONE);
            currentQuestion = questionQuizOneList_levelFive.get(questionCounter);

            ArrayList<String> list = new ArrayList<>();
            Collections.shuffle(answersList_levelOne);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelOne.get(i).getText_raspuns());
            Collections.shuffle(answersList_levelTwo);
            for (int i = 0; i< 6; i++)
                list.add(answersList_levelTwo.get(i).getText_raspuns());
            Collections.shuffle(answersList_levelThree);
            for (int i = 0; i< 6; i++)
                list.add(answersList_levelThree.get(i).getText_raspuns());
            Collections.shuffle(answersList_levelFour);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelFour.get(i).getText_raspuns());

            for (Answers item : answersList_levelFive) {
                list.add(item.getText_raspuns());
            }
            list.remove(questionQuizOneList_levelFive.get(questionCounter).getCod_raspuns_corect());

            Collections.shuffle(list);

            posibleAnswers.add(list.get(0));
            posibleAnswers.add(list.get(1));
            posibleAnswers.add(list.get(2));
            posibleAnswers.add(questionQuizOneList_levelFive.get(questionCounter).getCod_raspuns_corect());
            Collections.shuffle(posibleAnswers);

            if(currentQuestion.getDrawable_res().equals("o")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("i")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("so")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("ne")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("hi")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else if(currentQuestion.getDrawable_res().equals("mo")){
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res(), "drawable", LevelQuizOneActivity.this.getPackageName()));
            } else
                img_quizHiragana.setImageResource(LevelQuizOneActivity.this.getResources().getIdentifier
                        (currentQuestion.getDrawable_res() + "1", "drawable", LevelQuizOneActivity.this.getPackageName()));

            txt_op1.setText(posibleAnswers.get(0));
            txt_op2.setText(posibleAnswers.get(1));
            txt_op3.setText(posibleAnswers.get(2));
            txt_op4.setText(posibleAnswers.get(3));

            questionCounter++;
            txt_questionCount.setText("Întrebare: " + questionCounter + "/" + questionCountTotal);

        } else {
            finishQuiz();
        }
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(timeRemaining, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {

                final AlertDialog.Builder builder = new AlertDialog.Builder(LevelQuizOneActivity.this);
                builder.setMessage("Timpul s-a scurs. Din păcate, progresul tău nu va fi salvat. Încearcă și altă dată");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        timeRemaining = 0;
                        if(countDownTimer != null)
                            countDownTimer.cancel();
                        Intent intent1 = new Intent(LevelQuizOneActivity.this, LevelListActivity.class);
                        startActivity(intent1);
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.setTitle("Timp expirat");
                alertDialog.setCancelable(false);
                alertDialog.show();

            }
        };

        countDownTimer.start();
    }


    private void updateCountdownText(){

        int minutes = (int)(timeRemaining / 1000) / 60;
        int seconds = (int) (timeRemaining / 1000) % 60;

        String timeFormated = String.format(Locale.getDefault(),
                "%02d:%02d", minutes, seconds);

        txt_time.setText(timeFormated);

        if(timeRemaining < 10000) {
            txt_time.setTextColor(Color.RED);
        } else {
            txt_time.setTextColor(txtColor);
        }
    }

    private void finishQuiz(){
        int id;

        if(LevelListActivity.level_type.equals("level1") && GameActivity.quiz_type.equals("quiz1")) {

            int scoreDB = dbHelper.getScore(1, 1);

            if (btn_next.getText().equals("Finish")) {
                if (score > scoreDB) {
                    id = dbHelper.getScoreID(1, 1);
                    Score s = new Score(1, 1, score);
                    dbHelper.updateScore(s, String.valueOf(id));

                }
            }

            timeRemaining = 0;
            countDownTimer.cancel();

            Intent intent = new Intent(LevelQuizOneActivity.this, LevelListActivity.class);
            startActivity(intent);
        }

        if(LevelListActivity.level_type.equals("level2") && GameActivity.quiz_type.equals("quiz1")) {

            int scoreDB = dbHelper.getScore(2, 1);

            if (btn_next.getText().equals("Finish")) {
                if (score > scoreDB) {
                    id = dbHelper.getScoreID(2, 1);
                    Score s = new Score(2, 1, score);
                    dbHelper.updateScore(s, String.valueOf(id));

                }
            }

            timeRemaining = 0;
            countDownTimer.cancel();

            Intent intent = new Intent(LevelQuizOneActivity.this, LevelListActivity.class);
            startActivity(intent);
        }

        if(LevelListActivity.level_type.equals("level3") && GameActivity.quiz_type.equals("quiz1")) {

            int scoreDB = dbHelper.getScore(3, 1);

            if (btn_next.getText().equals("Finish")) {
                if (score > scoreDB) {
                    id = dbHelper.getScoreID(3, 1);
                    Score s = new Score(3, 1, score);
                    dbHelper.updateScore(s, String.valueOf(id));

                }
            }

            timeRemaining = 0;
            countDownTimer.cancel();

            Intent intent = new Intent(LevelQuizOneActivity.this, LevelListActivity.class);
            startActivity(intent);
        }

        if(LevelListActivity.level_type.equals("level4") && GameActivity.quiz_type.equals("quiz1")) {

            int scoreDB = dbHelper.getScore(4, 1);

            if (btn_next.getText().equals("Finish")) {
                if (score > scoreDB) {
                    id = dbHelper.getScoreID(4, 1);
                    Score s = new Score(4, 1, score);
                    dbHelper.updateScore(s, String.valueOf(id));

                }
            }

            timeRemaining = 0;
            countDownTimer.cancel();

            Intent intent = new Intent(LevelQuizOneActivity.this, LevelListActivity.class);
            startActivity(intent);
        }

        if(LevelListActivity.level_type.equals("level5") && GameActivity.quiz_type.equals("quiz1")) {

            int scoreDB = dbHelper.getScore(5, 1);

            if (btn_next.getText().equals("Finish")) {
                if (score > scoreDB) {
                    id = dbHelper.getScoreID(5, 1);
                    Score s = new Score(5, 1, score);
                    dbHelper.updateScore(s, String.valueOf(id));

                }
            }

            timeRemaining = 0;
            countDownTimer.cancel();

            Intent intent = new Intent(LevelQuizOneActivity.this, LevelListActivity.class);
            startActivity(intent);
        }
    }

    private void checkAnswers() {

        txt_op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                if (txt_op1.getText().toString().equals(currentQuestion.getCod_raspuns_corect())) {
                    img_answer.setImageResource(R.drawable.correct);
                    img_answer.setAlpha(1f);
                    txt_op1.setClickable(false);
                    txt_op2.setClickable(false);
                    txt_op3.setClickable(false);
                    txt_op4.setClickable(false);

                    score += 10;
                    txt_score.setText("Punctaj: " + score);

                } else {
                    img_answer.setImageResource(R.drawable.incorrect);
                    img_answer.setAlpha(1f);
                    txt_op1.setClickable(false);
                    txt_op2.setClickable(false);
                    txt_op3.setClickable(false);
                    txt_op4.setClickable(false);
                }

                showSolution();
            }
        });
        txt_op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                if (txt_op2.getText().toString().equals(currentQuestion.getCod_raspuns_corect())) {
                    img_answer.setImageResource(R.drawable.correct);
                    img_answer.setAlpha(1f);
                    txt_op1.setClickable(false);
                    txt_op2.setClickable(false);
                    txt_op3.setClickable(false);
                    txt_op4.setClickable(false);
//
                    score += 10;
                    txt_score.setText("Punctaj: " + score);

                } else {
                    img_answer.setImageResource(R.drawable.incorrect);
                    img_answer.setAlpha(1f);
                    txt_op1.setClickable(false);
                    txt_op2.setClickable(false);
                    txt_op3.setClickable(false);
                    txt_op4.setClickable(false);
                }

                showSolution();
            }
        });
        txt_op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                if (txt_op3.getText().toString().equals(currentQuestion.getCod_raspuns_corect())) {
                    img_answer.setImageResource(R.drawable.correct);
                    img_answer.setAlpha(1f);
                    txt_op1.setClickable(false);
                    txt_op2.setClickable(false);
                    txt_op3.setClickable(false);
                    txt_op4.setClickable(false);
//
                    score += 10;
                    txt_score.setText("Punctaj: " + score);

                } else {
                    img_answer.setImageResource(R.drawable.incorrect);
                    img_answer.setAlpha(1f);
                    txt_op1.setClickable(false);
                    txt_op2.setClickable(false);
                    txt_op3.setClickable(false);
                    txt_op4.setClickable(false);
                }

                showSolution();
            }
        });
        txt_op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setVisibility(View.VISIBLE);
                if (txt_op4.getText().toString().equals(currentQuestion.getCod_raspuns_corect())) {
                    img_answer.setImageResource(R.drawable.correct);
                    img_answer.setAlpha(1f);
                    txt_op1.setClickable(false);
                    txt_op2.setClickable(false);
                    txt_op3.setClickable(false);
                    txt_op4.setClickable(false);
//
                    score += 10;
                    txt_score.setText("Punctaj: " + score);

                } else {
                    img_answer.setImageResource(R.drawable.incorrect);
                    img_answer.setAlpha(1f);
                    txt_op1.setClickable(false);
                    txt_op2.setClickable(false);
                    txt_op3.setClickable(false);
                    txt_op4.setClickable(false);
                }

                showSolution();
            }
        });

    }

    private void showSolution() {

        if(questionCounter < questionCountTotal) {
            btn_next.setText("Next");
        } else {
            countDownTimer.cancel();
            btn_next.setText("Finish");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}

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
import com.example.japanase.Classes.AnswersQuizTwo;
import com.example.japanase.Classes.DBHelper;
import com.example.japanase.Classes.QuestionQuizTwo;
import com.example.japanase.Classes.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class LevelQuizTwoActivity extends AppCompatActivity {

    Button btn_back_to_q2_from_level1, btn_next_q2;
    TextView txt_time_q2, txt_score_q2, txt_questionCount_q2;
    TextView txt_question_q2;
    ImageView img_answer_q2;
    CircleImageView img_option1, img_option2, img_option3, img_option4;

    private ArrayList<QuestionQuizTwo> questionQuizTwoList_levelOne, questionQuizTwoList_levelTwo, questionQuizTwoList_levelThree, questionQuizTwoList_levelFour, questionQuizTwoList_levelFive;
    private ArrayList<AnswersQuizTwo> answersList_levelOne, answersList_levelTwo, answersList_levelThree, answersList_levelFour, answersList_levelFive;
    DBHelper dbHelper;
    private int questionCounter = 0;
    private int questionCountTotal = 0;
    private QuestionQuizTwo currentQuestion;
    private int score = 0;

    private String op1, op2, op3, op4;

    private static final long COUNTDOWN_IN_MILLIS_LEVEL_ONE_QUIZ_TWO = 60000;
    private static final long COUNTDOWN_IN_MILLIS_LEVEL_TWO_QUIZ_TWO = 90000;
    private static final long COUNTDOWN_IN_MILLIS_LEVEL_THREE_QUIZ_TWO = 120000;
    private static final long COUNTDOWN_IN_MILLIS_LEVEL_FOUR_QUIZ_TWO = 150000;
    private static final long COUNTDOWN_IN_MILLIS_LEVEL_FIVE_QUIZ_TWO = 180000;
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(LevelQuizTwoActivity.this);
        builder.setMessage("Ești sigur că vrei să renunți? Tot progresul tău se va pierde");
        builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                timeRemaining = 0;
                if (countDownTimer != null)
                    countDownTimer.cancel();
                Intent intent1 = new Intent(LevelQuizTwoActivity.this, LevelListActivity.class);
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
        setContentView(R.layout.activity_level_quiz_two);

        btn_back_to_q2_from_level1 = (Button) findViewById(R.id.btn_back_to_q2_from_level1);
        txt_score_q2 = (TextView) findViewById(R.id.txt_score_q2);
        txt_questionCount_q2 = (TextView) findViewById(R.id.txt_questionCount_q2);
        txt_time_q2 = (TextView) findViewById(R.id.txt_time_q2);
        txt_question_q2 = (TextView) findViewById(R.id.txt_question_q2);
        img_option1 = (CircleImageView) findViewById(R.id.img_optionOne);
        img_option2 = (CircleImageView) findViewById(R.id.img_optionTwo);
        img_option3 = (CircleImageView) findViewById(R.id.img_optionThree);
        img_option4 = (CircleImageView) findViewById(R.id.img_optionFour);
        img_answer_q2 = (ImageView) findViewById(R.id.img_answer_q2);
        btn_next_q2 = (Button) findViewById(R.id.btn_next_q2);

        txt_score_q2.setText("Punctaj: " + score);

        txtColor = txt_time_q2.getTextColors();

        dbHelper = new DBHelper(LevelQuizTwoActivity.this);
        //lista de intrebari pe nivele
        questionQuizTwoList_levelOne = dbHelper.getAllQuestionQuizTwo_levelOne();
        questionQuizTwoList_levelTwo = dbHelper.getAllQuestionQuizTwo_levelTwo();
        questionQuizTwoList_levelThree = dbHelper.getAllQuestionQuizTwo_levelThree();
        questionQuizTwoList_levelFour = dbHelper.getAllQuestionQuizTwo_levelFour();
        questionQuizTwoList_levelFive = dbHelper.getAllQuestionQuizTwo_levelFive();
        //lista de raspunsuri pe nivele
        answersList_levelOne = dbHelper.getAllAnswersQ2_levelOne();
        answersList_levelTwo = dbHelper.getAllAnswersQ2_levelTwo();
        answersList_levelThree = dbHelper.getAllAnswersQ2_levelThree();
        answersList_levelFour = dbHelper.getAllAnswersQ2_levelFour();
        answersList_levelFive = dbHelper.getAllAnswersQ2_levelFive();

        btn_back_to_q2_from_level1.getBackground().setAlpha(1);
        btn_back_to_q2_from_level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(LevelQuizTwoActivity.this);
                builder.setMessage("Ești sigur că vrei să renunți? Tot progresul tău se va pierde");
                builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        timeRemaining = 0;
                        if (countDownTimer != null)
                            countDownTimer.cancel();
                        Intent intent1 = new Intent(LevelQuizTwoActivity.this, LevelListActivity.class);
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

        img_answer_q2.setAlpha(0f);
        btn_next_q2.getBackground().setAlpha(1);
        btn_next_q2.setVisibility(View.GONE);
        img_answer_q2.setVisibility(View.VISIBLE);


/* ================================== QUIZ 2 LEVEL 1 ================================== */
        if (LevelListActivity.level_type.equals("level1") && GameActivity.quiz_type.equals("quiz2")) {

            questionCountTotal = questionQuizTwoList_levelOne.size();
            Collections.shuffle(questionQuizTwoList_levelOne); //amestecam interbarile inter ele ca sa nu fie mereu aceleasi
            Collections.shuffle(answersList_levelOne);

            showNextQuestion_levelOne();

            startCountdown();

            btn_next_q2.setVisibility(View.GONE);
            img_option1.setClickable(true);
            img_option2.setClickable(true);
            img_option3.setClickable(true);
            img_option4.setClickable(true);
            checkAnswers();
            btn_next_q2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNextQuestion_levelOne();
                    timeRemaining = 0;
                    if (btn_next_q2.getText().equals("Finish")) {
                        countDownTimer.cancel();
                    }
                }
            });
        }

/* ================================== QUIZ 2 LEVEL 2 ================================== */
        if (LevelListActivity.level_type.equals("level2") && GameActivity.quiz_type.equals("quiz2")) {

            Collections.shuffle(questionQuizTwoList_levelOne);
            for (int i = 0; i<5; i++)
                questionQuizTwoList_levelTwo.add(questionQuizTwoList_levelOne.get(i));

            questionCountTotal = questionQuizTwoList_levelTwo.size();
            Collections.shuffle(questionQuizTwoList_levelTwo); //amestecam interbarile inter ele ca sa nu fie mereu aceleasi
            Collections.shuffle(answersList_levelTwo);

            showNextQuestion_levelTwo();

            startCountdown();

            btn_next_q2.setVisibility(View.GONE);
            img_option1.setClickable(true);
            img_option2.setClickable(true);
            img_option3.setClickable(true);
            img_option4.setClickable(true);
            checkAnswers();
            btn_next_q2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNextQuestion_levelTwo();
                    timeRemaining = 0;
                    if (btn_next_q2.getText().equals("Finish")) {
                        countDownTimer.cancel();
                    }
                }
            });
        }

/* ================================== QUIZ 2 LEVEL 3 ================================== */
        if (LevelListActivity.level_type.equals("level3") && GameActivity.quiz_type.equals("quiz2")) {

            Collections.shuffle(questionQuizTwoList_levelOne);
            for (int i = 0; i<5; i++)
                questionQuizTwoList_levelThree.add(questionQuizTwoList_levelOne.get(i));
            Collections.shuffle(questionQuizTwoList_levelTwo);
            for (int i = 0; i<5; i++)
                questionQuizTwoList_levelThree.add(questionQuizTwoList_levelTwo.get(i));

            questionCountTotal = questionQuizTwoList_levelThree.size();
            Collections.shuffle(questionQuizTwoList_levelThree); //amestecam interbarile inter ele ca sa nu fie mereu aceleasi
            Collections.shuffle(answersList_levelThree);

            showNextQuestion_levelThree();

            startCountdown();

            btn_next_q2.setVisibility(View.GONE);
            img_option1.setClickable(true);
            img_option2.setClickable(true);
            img_option3.setClickable(true);
            img_option4.setClickable(true);
            checkAnswers();
            btn_next_q2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNextQuestion_levelThree();
                    timeRemaining = 0;
                    if (btn_next_q2.getText().equals("Finish")) {
                        countDownTimer.cancel();
                    }
                }
            });
        }

/* ================================== QUIZ 2 LEVEL 4 ================================== */
        if (LevelListActivity.level_type.equals("level4") && GameActivity.quiz_type.equals("quiz2")) {

            Collections.shuffle(questionQuizTwoList_levelOne);
            for (int i = 0; i<5; i++)
                questionQuizTwoList_levelFour.add(questionQuizTwoList_levelOne.get(i));
            Collections.shuffle(questionQuizTwoList_levelTwo);
            for (int i = 0; i<6; i++)
                questionQuizTwoList_levelFour.add(questionQuizTwoList_levelTwo.get(i));
            Collections.shuffle(questionQuizTwoList_levelThree);
            for (int i = 0; i<6; i++)
                questionQuizTwoList_levelFour.add(questionQuizTwoList_levelThree.get(i));


            questionCountTotal = questionQuizTwoList_levelFour.size();
            Collections.shuffle(questionQuizTwoList_levelFour); //amestecam interbarile inter ele ca sa nu fie mereu aceleasi
            Collections.shuffle(answersList_levelFour);

            showNextQuestion_levelFour();

            startCountdown();

            btn_next_q2.setVisibility(View.GONE);
            img_option1.setClickable(true);
            img_option2.setClickable(true);
            img_option3.setClickable(true);
            img_option4.setClickable(true);
            checkAnswers();
            btn_next_q2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNextQuestion_levelFour();
                    timeRemaining = 0;
                    if (btn_next_q2.getText().equals("Finish")) {
                        countDownTimer.cancel();
                    }
                }
            });
        }

/* ================================== QUIZ 2 LEVEL 5 ================================== */
        if (LevelListActivity.level_type.equals("level5") && GameActivity.quiz_type.equals("quiz2")) {

            Collections.shuffle(questionQuizTwoList_levelOne);
            for (int i = 0; i<5; i++)
                questionQuizTwoList_levelFive.add(questionQuizTwoList_levelOne.get(i));
            Collections.shuffle(questionQuizTwoList_levelTwo);
            for (int i = 0; i<6; i++)
                questionQuizTwoList_levelFive.add(questionQuizTwoList_levelTwo.get(i));
            Collections.shuffle(questionQuizTwoList_levelThree);
            for (int i = 0; i<6; i++)
                questionQuizTwoList_levelFive.add(questionQuizTwoList_levelThree.get(i));
            Collections.shuffle(questionQuizTwoList_levelFour);
            for (int i = 0; i<5; i++)
                questionQuizTwoList_levelFive.add(questionQuizTwoList_levelFour.get(i));


            questionCountTotal = questionQuizTwoList_levelFive.size();
            Collections.shuffle(questionQuizTwoList_levelFive); //amestecam interbarile inter ele ca sa nu fie mereu aceleasi
            Collections.shuffle(answersList_levelFive);

            showNextQuestion_levelFive();

            startCountdown();

            btn_next_q2.setVisibility(View.GONE);
            img_option1.setClickable(true);
            img_option2.setClickable(true);
            img_option3.setClickable(true);
            img_option4.setClickable(true);
            checkAnswers();
            btn_next_q2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNextQuestion_levelFive();
                    timeRemaining = 0;
                    if (btn_next_q2.getText().equals("Finish")) {
                        countDownTimer.cancel();
                    }
                }
            });
        }
    }


    private void showNextQuestion_levelOne(){
        op1 = ""; op2 = ""; op3 = ""; op4 = "";

        timeRemaining = COUNTDOWN_IN_MILLIS_LEVEL_ONE_QUIZ_TWO;

        img_option1.setClickable(true);
        img_option2.setClickable(true);
        img_option3.setClickable(true);
        img_option4.setClickable(true);
        btn_next_q2.setVisibility(View.VISIBLE);
        img_answer_q2.setAlpha(0f);

        ArrayList<String> posibleAnswers = new ArrayList<>();

        if (questionCounter < questionCountTotal) {
            btn_next_q2.setVisibility(View.GONE);
            currentQuestion = questionQuizTwoList_levelOne.get(questionCounter);

            ArrayList<String> list = new ArrayList<>();
            for (AnswersQuizTwo item : answersList_levelOne) {
                list.add(item.getDrawable_res());
            }
            list.remove(questionQuizTwoList_levelOne.get(questionCounter).getCod_raspuns_corect());

            Collections.shuffle(list);

            posibleAnswers.add(list.get(0));
            posibleAnswers.add(list.get(1));
            posibleAnswers.add(list.get(2));
            posibleAnswers.add(questionQuizTwoList_levelOne.get(questionCounter).getCod_raspuns_corect());
            Collections.shuffle(posibleAnswers);

            op1 = posibleAnswers.get(0);
            op2 = posibleAnswers.get(1);
            op3 = posibleAnswers.get(2);
            op4 = posibleAnswers.get(3);

            //caracterul romaji ce trebuie identificat in hiragana
            txt_question_q2.setText(currentQuestion.getText_intrebare());

            if(posibleAnswers.get(0).equals("o")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("i")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(1).equals("o")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("i")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(2).equals("o")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("i")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(3).equals("o")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("i")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            questionCounter++;
            txt_questionCount_q2.setText("Întrebare: " + questionCounter + "/" + questionCountTotal);

        } else {
            finishQuiz();
        }
    }

    private void showNextQuestion_levelTwo(){
        op1 = ""; op2 = ""; op3 = ""; op4 = "";

        timeRemaining = COUNTDOWN_IN_MILLIS_LEVEL_TWO_QUIZ_TWO;

        img_option1.setClickable(true);
        img_option2.setClickable(true);
        img_option3.setClickable(true);
        img_option4.setClickable(true);
        btn_next_q2.setVisibility(View.VISIBLE);
        img_answer_q2.setAlpha(0f);

        ArrayList<String> posibleAnswers = new ArrayList<>();

        if (questionCounter < questionCountTotal) {
            btn_next_q2.setVisibility(View.GONE);
            currentQuestion = questionQuizTwoList_levelTwo.get(questionCounter);

            ArrayList<String> list = new ArrayList<>();
            Collections.shuffle(answersList_levelOne);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelOne.get(i).getDrawable_res());
            for (AnswersQuizTwo item : answersList_levelTwo) {
                list.add(item.getDrawable_res());
            }
            list.remove(questionQuizTwoList_levelTwo.get(questionCounter).getCod_raspuns_corect());

            Collections.shuffle(list);

            posibleAnswers.add(list.get(0));
            posibleAnswers.add(list.get(1));
            posibleAnswers.add(list.get(2));
            posibleAnswers.add(questionQuizTwoList_levelTwo.get(questionCounter).getCod_raspuns_corect());
            Collections.shuffle(posibleAnswers);

            op1 = posibleAnswers.get(0);
            op2 = posibleAnswers.get(1);
            op3 = posibleAnswers.get(2);
            op4 = posibleAnswers.get(3);

            //caracterul romaji ce trebuie identificat in hiragana
            txt_question_q2.setText(currentQuestion.getText_intrebare());

            if(posibleAnswers.get(0).equals("o")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("i")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("so")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(1).equals("o")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("i")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("so")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }  else {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(2).equals("o")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("i")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("so")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }  else {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(3).equals("o")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("i")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("so")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }  else {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            questionCounter++;
            txt_questionCount_q2.setText("Întrebare: " + questionCounter + "/" + questionCountTotal);

        } else {
            finishQuiz();
        }
    }

    private void showNextQuestion_levelThree(){
        op1 = ""; op2 = ""; op3 = ""; op4 = "";

        timeRemaining = COUNTDOWN_IN_MILLIS_LEVEL_THREE_QUIZ_TWO;

        img_option1.setClickable(true);
        img_option2.setClickable(true);
        img_option3.setClickable(true);
        img_option4.setClickable(true);
        btn_next_q2.setVisibility(View.VISIBLE);
        img_answer_q2.setAlpha(0f);

        ArrayList<String> posibleAnswers = new ArrayList<>();

        if (questionCounter < questionCountTotal) {
            btn_next_q2.setVisibility(View.GONE);
            currentQuestion = questionQuizTwoList_levelThree.get(questionCounter);

            ArrayList<String> list = new ArrayList<>();
            Collections.shuffle(answersList_levelOne);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelOne.get(i).getDrawable_res());
            Collections.shuffle(answersList_levelTwo);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelTwo.get(i).getDrawable_res());

            for (AnswersQuizTwo item : answersList_levelThree) {
                list.add(item.getDrawable_res());
            }
            list.remove(questionQuizTwoList_levelThree.get(questionCounter).getCod_raspuns_corect());

            Collections.shuffle(list);

            posibleAnswers.add(list.get(0));
            posibleAnswers.add(list.get(1));
            posibleAnswers.add(list.get(2));
            posibleAnswers.add(questionQuizTwoList_levelThree.get(questionCounter).getCod_raspuns_corect());
            Collections.shuffle(posibleAnswers);

            op1 = posibleAnswers.get(0);
            op2 = posibleAnswers.get(1);
            op3 = posibleAnswers.get(2);
            op4 = posibleAnswers.get(3);

            //caracterul romaji ce trebuie identificat in hiragana
            txt_question_q2.setText(currentQuestion.getText_intrebare());

            if(posibleAnswers.get(0).equals("o")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("i")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("so")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("ne")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("hi")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(1).equals("o")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("i")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("so")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("ne")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("hi")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(2).equals("o")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("i")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("so")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("ne")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("hi")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(3).equals("o")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("i")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("so")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("ne")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("hi")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            questionCounter++;
            txt_questionCount_q2.setText("Întrebare: " + questionCounter + "/" + questionCountTotal);

        } else {
            finishQuiz();
        }
    }

    private void showNextQuestion_levelFour(){
        op1 = ""; op2 = ""; op3 = ""; op4 = "";

        timeRemaining = COUNTDOWN_IN_MILLIS_LEVEL_FOUR_QUIZ_TWO;

        img_option1.setClickable(true);
        img_option2.setClickable(true);
        img_option3.setClickable(true);
        img_option4.setClickable(true);
        btn_next_q2.setVisibility(View.VISIBLE);
        img_answer_q2.setAlpha(0f);

        ArrayList<String> posibleAnswers = new ArrayList<>();

        if (questionCounter < questionCountTotal) {
            btn_next_q2.setVisibility(View.GONE);
            currentQuestion = questionQuizTwoList_levelFour.get(questionCounter);

            ArrayList<String> list = new ArrayList<>();
            Collections.shuffle(answersList_levelOne);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelOne.get(i).getDrawable_res());
            Collections.shuffle(answersList_levelTwo);
            for (int i = 0; i< 6; i++)
                list.add(answersList_levelTwo.get(i).getDrawable_res());
            Collections.shuffle(answersList_levelThree);
            for (int i = 0; i< 6; i++)
                list.add(answersList_levelThree.get(i).getDrawable_res());

            for (AnswersQuizTwo item : answersList_levelFour) {
                list.add(item.getDrawable_res());
            }
            list.remove(questionQuizTwoList_levelFour.get(questionCounter).getCod_raspuns_corect());

            Collections.shuffle(list);

            posibleAnswers.add(list.get(0));
            posibleAnswers.add(list.get(1));
            posibleAnswers.add(list.get(2));
            posibleAnswers.add(questionQuizTwoList_levelFour.get(questionCounter).getCod_raspuns_corect());
            Collections.shuffle(posibleAnswers);

            op1 = posibleAnswers.get(0);
            op2 = posibleAnswers.get(1);
            op3 = posibleAnswers.get(2);
            op4 = posibleAnswers.get(3);

            //caracterul romaji ce trebuie identificat in hiragana
            txt_question_q2.setText(currentQuestion.getText_intrebare());

            if(posibleAnswers.get(0).equals("o")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("i")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("so")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("ne")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("hi")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("mo")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(1).equals("o")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("i")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("so")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("ne")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("hi")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("mo")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(2).equals("o")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("i")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("so")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("ne")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("hi")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("mo")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(3).equals("o")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("i")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("so")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("ne")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("hi")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("mo")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            questionCounter++;
            txt_questionCount_q2.setText("Întrebare: " + questionCounter + "/" + questionCountTotal);

        } else {
            finishQuiz();
        }
    }

    private void showNextQuestion_levelFive(){
        op1 = ""; op2 = ""; op3 = ""; op4 = "";

        timeRemaining = COUNTDOWN_IN_MILLIS_LEVEL_FIVE_QUIZ_TWO;

        img_option1.setClickable(true);
        img_option2.setClickable(true);
        img_option3.setClickable(true);
        img_option4.setClickable(true);
        btn_next_q2.setVisibility(View.VISIBLE);
        img_answer_q2.setAlpha(0f);

        ArrayList<String> posibleAnswers = new ArrayList<>();

        if (questionCounter < questionCountTotal) {
            btn_next_q2.setVisibility(View.GONE);
            currentQuestion = questionQuizTwoList_levelFive.get(questionCounter);

            ArrayList<String> list = new ArrayList<>();
            Collections.shuffle(answersList_levelOne);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelOne.get(i).getDrawable_res());
            Collections.shuffle(answersList_levelTwo);
            for (int i = 0; i< 6; i++)
                list.add(answersList_levelTwo.get(i).getDrawable_res());
            Collections.shuffle(answersList_levelThree);
            for (int i = 0; i< 6; i++)
                list.add(answersList_levelThree.get(i).getDrawable_res());
            Collections.shuffle(answersList_levelFour);
            for (int i = 0; i< 5; i++)
                list.add(answersList_levelFour.get(i).getDrawable_res());

            for (AnswersQuizTwo item : answersList_levelFive) {
                list.add(item.getDrawable_res());
            }
            list.remove(questionQuizTwoList_levelFive.get(questionCounter).getCod_raspuns_corect());

            Collections.shuffle(list);

            posibleAnswers.add(list.get(0));
            posibleAnswers.add(list.get(1));
            posibleAnswers.add(list.get(2));
            posibleAnswers.add(questionQuizTwoList_levelFive.get(questionCounter).getCod_raspuns_corect());
            Collections.shuffle(posibleAnswers);

            op1 = posibleAnswers.get(0);
            op2 = posibleAnswers.get(1);
            op3 = posibleAnswers.get(2);
            op4 = posibleAnswers.get(3);

            //caracterul romaji ce trebuie identificat in hiragana
            txt_question_q2.setText(currentQuestion.getText_intrebare());

            if(posibleAnswers.get(0).equals("o")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("i")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("so")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("ne")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("hi")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(0).equals("mo")) {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option1.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(0) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(1).equals("o")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("i")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("so")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("ne")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("hi")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(1).equals("mo")) {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option2.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(1) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(2).equals("o")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("i")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("so")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("ne")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("hi")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(2).equals("mo")) {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option3.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(2) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            if(posibleAnswers.get(3).equals("o")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("i")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("so")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("ne")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("hi")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else if(posibleAnswers.get(3).equals("mo")) {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3), "drawable", LevelQuizTwoActivity.this.getPackageName())));
            } else {
                img_option4.setImageResource((LevelQuizTwoActivity.this.getResources().getIdentifier(
                        posibleAnswers.get(3) + "1", "drawable", LevelQuizTwoActivity.this.getPackageName())));
            }

            questionCounter++;
            txt_questionCount_q2.setText("Întrebare: " + questionCounter + "/" + questionCountTotal);

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

                final AlertDialog.Builder builder = new AlertDialog.Builder(LevelQuizTwoActivity.this);
                builder.setMessage("Timpul s-a scurs. Din păcate, progresul tău nu va fi salvat. Încearcă și altă dată");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        timeRemaining = 0;
                        if(countDownTimer != null)
                            countDownTimer.cancel();
                        Intent intent1 = new Intent(LevelQuizTwoActivity.this, LevelListActivity.class);
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

        txt_time_q2.setText(timeFormated);

        if(timeRemaining < 10000) {
            txt_time_q2.setTextColor(Color.RED);
        } else {
            txt_time_q2.setTextColor(txtColor);
        }
    }

    private void finishQuiz(){
        int id;

        if(LevelListActivity.level_type.equals("level1") && GameActivity.quiz_type.equals("quiz2")) {

            int scoreDB = dbHelper.getScore(1, 2);

            if (btn_next_q2.getText().equals("Finish")) {
                if (score > scoreDB) {
                    id = dbHelper.getScoreID(1, 2);
                    Score s = new Score(1, 2, score);
                    dbHelper.updateScore(s, String.valueOf(id));
                }
            }

            timeRemaining = 0;
            countDownTimer.cancel();

            Intent intent = new Intent(LevelQuizTwoActivity.this, LevelListActivity.class);
            startActivity(intent);
        }

        if(LevelListActivity.level_type.equals("level2") && GameActivity.quiz_type.equals("quiz2")) {

            int scoreDB = dbHelper.getScore(2, 2);

            if (btn_next_q2.getText().equals("Finish")) {
                if (score > scoreDB) {
                    id = dbHelper.getScoreID(2, 2);
                    Score s = new Score(2, 2, score);
                    dbHelper.updateScore(s, String.valueOf(id));
                }
            }

            timeRemaining = 0;
            countDownTimer.cancel();

            Intent intent = new Intent(LevelQuizTwoActivity.this, LevelListActivity.class);
            startActivity(intent);
        }

        if(LevelListActivity.level_type.equals("level3") && GameActivity.quiz_type.equals("quiz2")) {

            int scoreDB = dbHelper.getScore(3, 2);

            if (btn_next_q2.getText().equals("Finish")) {
                if (score > scoreDB) {
                    id = dbHelper.getScoreID(3, 2);
                    Score s = new Score(3, 2, score);
                    dbHelper.updateScore(s, String.valueOf(id));
                }
            }

            timeRemaining = 0;
            countDownTimer.cancel();

            Intent intent = new Intent(LevelQuizTwoActivity.this, LevelListActivity.class);
            startActivity(intent);
        }

        if(LevelListActivity.level_type.equals("level4") && GameActivity.quiz_type.equals("quiz2")) {

            int scoreDB = dbHelper.getScore(4, 2);

            if (btn_next_q2.getText().equals("Finish")) {
                if (score > scoreDB) {
                    id = dbHelper.getScoreID(4, 2);
                    Score s = new Score(4, 2, score);
                    dbHelper.updateScore(s, String.valueOf(id));
                }
            }

            timeRemaining = 0;
            countDownTimer.cancel();

            Intent intent = new Intent(LevelQuizTwoActivity.this, LevelListActivity.class);
            startActivity(intent);
        }
        if(LevelListActivity.level_type.equals("level5") && GameActivity.quiz_type.equals("quiz2")) {

            int scoreDB = dbHelper.getScore(5, 2);

            if (btn_next_q2.getText().equals("Finish")) {
                if (score > scoreDB) {
                    id = dbHelper.getScoreID(5, 2);
                    Score s = new Score(5, 2, score);
                    dbHelper.updateScore(s, String.valueOf(id));
                }
            }

            timeRemaining = 0;
            countDownTimer.cancel();

            Intent intent = new Intent(LevelQuizTwoActivity.this, LevelListActivity.class);
            startActivity(intent);
        }


    }

    private void checkAnswers() {

        img_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next_q2.setVisibility(View.VISIBLE);
                if (op1.equals(currentQuestion.getCod_raspuns_corect())) {
                    img_answer_q2.setImageResource(R.drawable.correct);
                    img_answer_q2.setAlpha(1f);
                    img_option1.setClickable(false);
                    img_option2.setClickable(false);
                    img_option3.setClickable(false);
                    img_option4.setClickable(false);

                    score += 10;
                    txt_score_q2.setText("Punctaj: " + score);

                } else {
                    img_answer_q2.setImageResource(R.drawable.incorrect);
                    img_answer_q2.setAlpha(1f);
                    img_option1.setClickable(false);
                    img_option2.setClickable(false);
                    img_option3.setClickable(false);
                    img_option4.setClickable(false);
                }

                showSolution();
            }
        });
        img_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next_q2.setVisibility(View.VISIBLE);
                if (op2.equals(currentQuestion.getCod_raspuns_corect())) {
                    img_answer_q2.setImageResource(R.drawable.correct);
                    img_answer_q2.setAlpha(1f);
                    img_option1.setClickable(false);
                    img_option2.setClickable(false);
                    img_option3.setClickable(false);
                    img_option4.setClickable(false);
//
                    score += 10;
                    txt_score_q2.setText("Punctaj: " + score);

                } else {
                    img_answer_q2.setImageResource(R.drawable.incorrect);
                    img_answer_q2.setAlpha(1f);
                    img_option1.setClickable(false);
                    img_option2.setClickable(false);
                    img_option3.setClickable(false);
                    img_option4.setClickable(false);
                }

                showSolution();
            }
        });
        img_option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next_q2.setVisibility(View.VISIBLE);
                if (op3.equals(currentQuestion.getCod_raspuns_corect())) {
                    img_answer_q2.setImageResource(R.drawable.correct);
                    img_answer_q2.setAlpha(1f);
                    img_option1.setClickable(false);
                    img_option2.setClickable(false);
                    img_option3.setClickable(false);
                    img_option4.setClickable(false);
//
                    score += 10;
                    txt_score_q2.setText("Punctaj: " + score);

                } else {
                    img_answer_q2.setImageResource(R.drawable.incorrect);
                    img_answer_q2.setAlpha(1f);
                    img_option1.setClickable(false);
                    img_option2.setClickable(false);
                    img_option3.setClickable(false);
                    img_option4.setClickable(false);
                }

                showSolution();
            }
        });
        img_option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next_q2.setVisibility(View.VISIBLE);
                if (op4.equals(currentQuestion.getCod_raspuns_corect())) {
                    img_answer_q2.setImageResource(R.drawable.correct);
                    img_answer_q2.setAlpha(1f);
                    img_option1.setClickable(false);
                    img_option2.setClickable(false);
                    img_option3.setClickable(false);
                    img_option4.setClickable(false);
//
                    score += 10;
                    txt_score_q2.setText("Punctaj: " + score);

                } else {
                    img_answer_q2.setImageResource(R.drawable.incorrect);
                    img_answer_q2.setAlpha(1f);
                    img_option1.setClickable(false);
                    img_option2.setClickable(false);
                    img_option3.setClickable(false);
                    img_option4.setClickable(false);
                }

                showSolution();
            }
        });

    }


    private void showSolution() {

        if(questionCounter < questionCountTotal) {
            btn_next_q2.setText("Next");
        } else {
            countDownTimer.cancel();
            btn_next_q2.setText("Finish");
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

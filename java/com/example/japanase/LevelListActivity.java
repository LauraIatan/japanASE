package com.example.japanase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.japanase.Classes.DBHelper;

public class LevelListActivity extends AppCompatActivity {

    Button btn_back_to_game_from_q1;
    public static Button level1, level2, level3, level4,level5;
    String quiz_type;
    public static String level_type;
    TextView txt_lvl1, txt_lvl2, txt_lvl3, txt_lvl4, txt_lvl5;

    DBHelper dbHelper;
    int score_l1q1, score_l2q1, score_l3q1, score_l4q1, score_l5q1,
            score_l1q2, score_l2q2, score_l3q2, score_l4q2, score_l5q2,
            score_l1q3, score_l2q3, score_l3q3, score_l4q3, score_l5q3;

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
        Intent intent = new Intent(LevelListActivity.this, GameActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_level_list);

        btn_back_to_game_from_q1 = (Button)findViewById(R.id.btn_back_to_game_from_q1);
        level1 = (Button)findViewById(R.id.btn_lvlOne);
        level2 = (Button)findViewById(R.id.btn_lvlTwo);
        level3 = (Button)findViewById(R.id.btn_lvlThree);
        level4 = (Button)findViewById(R.id.btn_lvlFour);
        level5 = (Button)findViewById(R.id.btn_lvlFive);
        txt_lvl1 = (TextView)findViewById(R.id.txt_scoreLevelOne);
        txt_lvl2 = (TextView)findViewById(R.id.txt_scoreLevelTwo);
        txt_lvl3 = (TextView)findViewById(R.id.txt_scoreLevelThree);
        txt_lvl4 = (TextView)findViewById(R.id.txt_scoreLevelFour);
        txt_lvl5 = (TextView)findViewById(R.id.txt_scoreLevelFive);

        dbHelper = new DBHelper(LevelListActivity.this);

        score_l1q1 = dbHelper.getScore(1, 1);
        score_l2q1 = dbHelper.getScore(2, 1);
        score_l3q1 = dbHelper.getScore(3, 1);
        score_l4q1 = dbHelper.getScore(4, 1);
        score_l5q1 = dbHelper.getScore(5, 1);

        score_l1q2 = dbHelper.getScore(1, 2);
        score_l2q2 = dbHelper.getScore(2, 2);
        score_l3q2 = dbHelper.getScore(3, 2);
        score_l4q2 = dbHelper.getScore(4, 2);
        score_l5q2 = dbHelper.getScore(5, 2);

        score_l1q3 = dbHelper.getScore(1, 3);
        score_l2q3 = dbHelper.getScore(2, 3);
        score_l3q3 = dbHelper.getScore(3, 3);
        score_l4q3 = dbHelper.getScore(4, 3);
        score_l5q3 = dbHelper.getScore(5, 3);

        btn_back_to_game_from_q1.getBackground().setAlpha(1);
        btn_back_to_game_from_q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LevelListActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        quiz_type = GameActivity.quiz_type;

        switch (quiz_type){
            case "quiz1":

                txt_lvl1.setText("Cel mai bun punctaj: " + score_l1q1);
                txt_lvl2.setText("Cel mai bun punctaj: " + score_l2q1);
                txt_lvl3.setText("Cel mai bun punctaj: " + score_l3q1);
                txt_lvl4.setText("Cel mai bun punctaj: " + score_l4q1);
                txt_lvl5.setText("Cel mai bun punctaj: " + score_l5q1);

                if(score_l1q1 >= 70) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level2.setCompoundDrawables(img, null, null, null);
                }

                if(score_l1q1 >= 70 && score_l2q1 >= 110) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level3.setCompoundDrawables(img, null, null, null);
                }

                if(score_l1q1 >= 70 && score_l2q1 >= 110 && score_l3q1 >= 160) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level4.setCompoundDrawables(img, null, null, null);
                }

                if(score_l1q1 >= 70 && score_l2q1 >= 110 && score_l3q1 >= 160 && score_l4q1 >= 210) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level5.setCompoundDrawables(img, null, null, null);
                }


                GameActivity.quiz_type = "quiz1";
                level1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        level_type = "level1";
                        Intent intent1 = new Intent(LevelListActivity.this, LevelQuizOneActivity.class);
                        startActivity(intent1);
                    }
                });

                level2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(score_l1q1 >= 70) {
                            level_type = "level2";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizOneActivity.class);
                            startActivity(intent1);
                        } else {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 70 de puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }


                    }
                });

                level3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(score_l1q1 >= 70 && score_l2q1 >= 110) {
                            level_type = "level3";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizOneActivity.class);
                            startActivity(intent1);
                        } else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 110 puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }
                    }
                });

                level4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(score_l1q1 >= 70 && score_l2q1 >= 110 && score_l3q1 >= 160) {
                            level_type = "level4";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizOneActivity.class);
                            startActivity(intent1);
                        } else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 160 de puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }
                    }
                });

                level5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(score_l1q1 >= 70 && score_l2q1 >= 110 && score_l3q1 >= 160 && score_l4q1 >= 210) {
                            level_type = "level5";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizOneActivity.class);
                            startActivity(intent1);
                        } else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 210 puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }
                    }
                });

                break;

            case "quiz2":

                txt_lvl1.setText("Cel mai bun punctaj: " + score_l1q2);
                txt_lvl2.setText("Cel mai bun punctaj: " + score_l2q2);
                txt_lvl3.setText("Cel mai bun punctaj: " + score_l3q2);
                txt_lvl4.setText("Cel mai bun punctaj: " + score_l4q2);
                txt_lvl5.setText("Cel mai bun punctaj: " + score_l5q2);

                if(score_l1q2 >= 70) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level2.setCompoundDrawables(img, null, null, null);
                }

                if(score_l1q2 >= 70 && score_l2q2 >= 110) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level3.setCompoundDrawables(img, null, null, null);
                }

                if(score_l1q2 >= 70 && score_l2q2 >= 110 && score_l3q2 >= 160) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level4.setCompoundDrawables(img, null, null, null);
                }

                if(score_l1q2 >= 70 && score_l2q2 >= 110 && score_l3q2 >= 160 && score_l4q2 >= 210) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level5.setCompoundDrawables(img, null, null, null);
                }

                GameActivity.quiz_type = "quiz2";
                level1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        level_type = "level1";
                        Intent intent1 = new Intent(LevelListActivity.this, LevelQuizTwoActivity.class);
                        startActivity(intent1);
                    }
                });

                level2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(score_l1q2 >= 70) {
                            level_type = "level2";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizTwoActivity.class);
                            startActivity(intent1);
                        } else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 70 de puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }
                    }
                });

                level3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (score_l1q2 >= 70 && score_l2q2 >= 110) {
                            level_type = "level3";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizTwoActivity.class);
                            startActivity(intent1);
                        } else {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 110 puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }
                    }
                });

                level4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(score_l1q2 >= 70 && score_l2q2 >= 110 && score_l3q2 >= 160) {
                            level_type = "level4";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizTwoActivity.class);
                            startActivity(intent1);
                        } else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 160 de puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }
                    }
                });

                level5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (score_l1q2 >= 70 && score_l2q2 >= 110 && score_l3q2 >= 160 && score_l4q2 >= 210) {
                            level_type = "level5";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizTwoActivity.class);
                            startActivity(intent1);
                        } else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 210 puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }
                    }
                });

                break;

            case "quiz3":
                txt_lvl1.setText("Cel mai bun punctaj: " + score_l1q3);
                txt_lvl2.setText("Cel mai bun punctaj: " + score_l2q3);
                txt_lvl3.setText("Cel mai bun punctaj: " + score_l3q3);
                txt_lvl4.setText("Cel mai bun punctaj: " + score_l4q3);
                txt_lvl5.setText("Cel mai bun punctaj: " + score_l5q3);

                if(score_l1q3 >= 70) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level2.setCompoundDrawables(img, null, null, null);
                }

                if(score_l1q3 >= 70 && score_l2q3 >= 110) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level3.setCompoundDrawables(img, null, null, null);
                }

                if(score_l1q3 >= 70 && score_l2q3 >= 110 && score_l3q3 >= 160) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level4.setCompoundDrawables(img, null, null, null);
                }

                if(score_l1q3 >= 70 && score_l2q3 >= 110 && score_l3q3 >= 160 && score_l4q3 >= 210) {
                    Drawable img = LevelListActivity.this.getResources().getDrawable(R.drawable.ic_action_unlock);
                    img.setBounds(0, 0, 60, 60);
                    level5.setCompoundDrawables(img, null, null, null);
                }

                GameActivity.quiz_type = "quiz3";
                level1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        level_type = "level1";
                        Intent intent1 = new Intent(LevelListActivity.this, LevelQuizThreeActivity.class);
                        startActivity(intent1);
                    }
                });

                level2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(score_l1q3 >= 70) {
                            level_type = "level2";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizThreeActivity.class);
                            startActivity(intent1);
                        } else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 70 de puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }
                    }
                });

                level3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (score_l1q3 >= 70 && score_l2q3 >= 110) {
                            level_type = "level3";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizThreeActivity.class);
                            startActivity(intent1);
                        }else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 110 puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }
                    }
                });

                level4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (score_l1q3 >= 70 && score_l2q3 >= 110 && score_l3q3 >= 160) {
                            level_type = "level4";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizThreeActivity.class);
                            startActivity(intent1);
                        } else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 160 de puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }
                    }
                });

                level5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (score_l1q3 >= 70 && score_l2q3 >= 110 && score_l3q3 >= 160 && score_l4q3 >= 210) {
                            level_type = "level5";
                            Intent intent1 = new Intent(LevelListActivity.this, LevelQuizThreeActivity.class);
                            startActivity(intent1);
                        } else{
                            final AlertDialog.Builder builder = new AlertDialog.Builder(LevelListActivity.this);
                            builder.setMessage("Pentru a putea accesa acest nivel, este nevoie ca la nivelul anterior să ai un punctaj de minimum 210 puncte");

                            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.setTitle("Nivel blocat");
                            alertDialog.show();
                        }
                    }
                });

                break;
        }
    }
}

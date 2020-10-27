package com.example.japanase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.japanase.Classes.DBHelper;
import com.example.japanase.Classes.GameOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.logging.Level;

public class LevelGameOneActivity extends AppCompatActivity {

    Button back;
    TextView txt_time_game1;
    ImageView img_felicitari;
    ImageView img_game1_11, img_game1_12, img_game1_13, img_game1_14,
            img_game1_21, img_game1_22, img_game1_23, img_game1_24,
            img_game1_31, img_game1_32, img_game1_33, img_game1_34,
            img_game1_41, img_game1_42, img_game1_43, img_game1_44;

    private static final long COUNTDOWN_IN_MILLIS = 240000;
    private ColorStateList txtColor;

    private CountDownTimer countDownTimer;
    private long timeRemaining;

    private ArrayList<GameOne> gameOneList_incepator, gameOneList_mediu, gameOneList_avansat;
    DBHelper dbHelper;

    ArrayList<String> gameListImages;
    ArrayList<Integer> gameListImagesID;

    boolean firstClicked = false;
    boolean secondClicked = false;
    int firstImage = 0, secondImage = 0;
    int theCard;
    int cardNumber = 1;

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

        final AlertDialog.Builder builder = new AlertDialog.Builder(LevelGameOneActivity.this);
        builder.setMessage("Ești sigur că vrei să renunți?");
        builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                timeRemaining = 0;
                if(countDownTimer != null)
                    countDownTimer.cancel();
                Intent intent = new Intent(LevelGameOneActivity.this, GameOneActivity.class);
                startActivity(intent);
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
        setContentView(R.layout.activity_level_game_one);

        back = (Button)findViewById(R.id.btn_back_to_levels_game1);
        txt_time_game1 = (TextView)findViewById(R.id.txt_time_game1);
        img_game1_11 = (ImageView)findViewById(R.id.img_game1_11);
        img_game1_12 = (ImageView)findViewById(R.id.img_game1_12);
        img_game1_13 = (ImageView)findViewById(R.id.img_game1_13);
        img_game1_14 = (ImageView)findViewById(R.id.img_game1_14);
        img_game1_21 = (ImageView)findViewById(R.id.img_game1_21);
        img_game1_22 = (ImageView)findViewById(R.id.img_game1_22);
        img_game1_23 = (ImageView)findViewById(R.id.img_game1_23);
        img_game1_24 = (ImageView)findViewById(R.id.img_game1_24);
        img_game1_31 = (ImageView)findViewById(R.id.img_game1_31);
        img_game1_32 = (ImageView)findViewById(R.id.img_game1_32);
        img_game1_33 = (ImageView)findViewById(R.id.img_game1_33);
        img_game1_34 = (ImageView)findViewById(R.id.img_game1_34);
        img_game1_41 = (ImageView)findViewById(R.id.img_game1_41);
        img_game1_42 = (ImageView)findViewById(R.id.img_game1_42);
        img_game1_43 = (ImageView)findViewById(R.id.img_game1_43);
        img_game1_44 = (ImageView)findViewById(R.id.img_game1_44);
        img_felicitari = (ImageView)findViewById(R.id.img_felicitari);

        txtColor = txt_time_game1.getTextColors();
        img_felicitari.setVisibility(View.GONE);


        dbHelper = new DBHelper(LevelGameOneActivity.this);
        //lista de imagini pe nivele
        gameOneList_incepator = dbHelper.getAllGameOne_incepator();
        gameOneList_mediu = dbHelper.getAllGameOne_mediu();
        gameOneList_avansat = dbHelper.getAllGameOne_avansat();

        gameListImages = new ArrayList<String>();
        gameListImagesID = new ArrayList<Integer>();

        back.getBackground().setAlpha(1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(LevelGameOneActivity.this);
                builder.setMessage("Ești sigur că vrei să renunți?");
                builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        timeRemaining = 0;
                        if(countDownTimer != null)
                            countDownTimer.cancel();
                        Intent intent = new Intent(LevelGameOneActivity.this, GameOneActivity.class);
                        startActivity(intent);
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

/* ================================== GAME 1 INCEPATOR ================================== */
        if(GameOneActivity.level_type.equals("incepator")) {

            timeRemaining = COUNTDOWN_IN_MILLIS;

            Collections.shuffle(gameOneList_incepator);

            //Toast.makeText(LevelGameOneActivity.this, String.valueOf(gameOneList_incepator.size()), Toast.LENGTH_SHORT).show();

            gameListImages.add(gameOneList_incepator.get(0).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(1).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(2).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(3).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(4).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(5).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(6).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(7).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(0).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(1).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(2).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(3).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(4).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(5).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(6).getCod_raspuns_corect());
            gameListImages.add(gameOneList_incepator.get(7).getCod_raspuns_corect());

            Collections.shuffle(gameListImages);

            int id;
            for (String item : gameListImages) {
                id = GetImage(LevelGameOneActivity.this, item);
                gameListImagesID.add(id);
            }

            startCountdown();

            img_game1_11.setTag(gameListImagesID.get(0));
            img_game1_12.setTag(gameListImagesID.get(1));
            img_game1_13.setTag(gameListImagesID.get(2));
            img_game1_14.setTag(gameListImagesID.get(3));
            img_game1_21.setTag(gameListImagesID.get(4));
            img_game1_22.setTag(gameListImagesID.get(5));
            img_game1_23.setTag(gameListImagesID.get(6));
            img_game1_24.setTag(gameListImagesID.get(7));
            img_game1_31.setTag(gameListImagesID.get(8));
            img_game1_32.setTag(gameListImagesID.get(9));
            img_game1_33.setTag(gameListImagesID.get(10));
            img_game1_34.setTag(gameListImagesID.get(11));
            img_game1_41.setTag(gameListImagesID.get(12));
            img_game1_42.setTag(gameListImagesID.get(13));
            img_game1_43.setTag(gameListImagesID.get(14));
            img_game1_44.setTag(gameListImagesID.get(15));

            img_game1_11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_11, theCard);
                }
            });

            img_game1_12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_12, theCard);
                }
            });

            img_game1_13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_13, theCard);
                }
            });

            img_game1_14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_14, theCard);
                }
            });
            img_game1_21.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_21, theCard);
                }
            });

            img_game1_22.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_22, theCard);
                }
            });

            img_game1_23.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_23, theCard);
                }
            });

            img_game1_24.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_24, theCard);
                }
            });
            img_game1_31.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_31, theCard);
                }
            });

            img_game1_32.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_32, theCard);
                }
            });

            img_game1_33.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_33, theCard);
                }
            });

            img_game1_34.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_34, theCard);
                }
            });
            img_game1_41.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_41, theCard);
                }
            });

            img_game1_42.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_42, theCard);
                }
            });

            img_game1_43.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_43, theCard);
                }
            });

            img_game1_44.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    theCard = (int)v.getTag();
                    calculate(img_game1_44, theCard);
                }
            });


        }
    }

    public static int GetImage(Context c, String ImageName) {
        int id = 0;
        if (ImageName.equals("i") || ImageName.equals("o") || ImageName.equals("so")) {
            id = c.getResources().getIdentifier(ImageName, "drawable", c.getPackageName());
        } else {
            id = c.getResources().getIdentifier(ImageName+"1", "drawable", c.getPackageName());
        }

        return id;
    }

    private void calculate(ImageView imageView, int card) {
        imageView.setImageResource(card);

        if(cardNumber == 1) {
            firstImage = card;
            cardNumber = 2;
            firstClicked = true;

            imageView.setEnabled(false);
        } else if(cardNumber == 2){
            secondImage = card;
            cardNumber = 1;
            secondClicked = true;

            img_game1_11.setEnabled(false);
            img_game1_12.setEnabled(false);
            img_game1_13.setEnabled(false);
            img_game1_14.setEnabled(false);
            img_game1_21.setEnabled(false);
            img_game1_22.setEnabled(false);
            img_game1_23.setEnabled(false);
            img_game1_24.setEnabled(false);
            img_game1_31.setEnabled(false);
            img_game1_32.setEnabled(false);
            img_game1_33.setEnabled(false);
            img_game1_34.setEnabled(false);
            img_game1_41.setEnabled(false);
            img_game1_42.setEnabled(false);
            img_game1_43.setEnabled(false);
            img_game1_44.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    verify();
                }
            }, 1000);
        }
    }

    private void verify() {

        if(firstImage == secondImage) {

            if (firstClicked == true) {
                if (firstImage == gameListImagesID.get(0) && img_game1_11.getVisibility() != View.INVISIBLE) {
                    img_game1_11.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(1) && img_game1_12.getVisibility() != View.INVISIBLE) {
                    img_game1_12.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(2) && img_game1_13.getVisibility() != View.INVISIBLE) {
                    img_game1_13.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(3) && img_game1_14.getVisibility() != View.INVISIBLE) {
                    img_game1_14.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(4) && img_game1_21.getVisibility() != View.INVISIBLE) {
                    img_game1_21.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(5) && img_game1_22.getVisibility() != View.INVISIBLE) {
                    img_game1_22.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(6) && img_game1_23.getVisibility() != View.INVISIBLE) {
                    img_game1_23.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(7) && img_game1_24.getVisibility() != View.INVISIBLE) {
                    img_game1_24.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(8) && img_game1_31.getVisibility() != View.INVISIBLE) {
                    img_game1_31.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(9) && img_game1_32.getVisibility() != View.INVISIBLE) {
                    img_game1_32.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(10) && img_game1_33.getVisibility() != View.INVISIBLE) {
                    img_game1_33.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(11) && img_game1_34.getVisibility() != View.INVISIBLE) {
                    img_game1_34.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(12) && img_game1_41.getVisibility() != View.INVISIBLE) {
                    img_game1_41.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(13) && img_game1_42.getVisibility() != View.INVISIBLE) {
                    img_game1_42.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(14) && img_game1_43.getVisibility() != View.INVISIBLE) {
                    img_game1_43.setVisibility(View.INVISIBLE);
                } else if (firstImage == gameListImagesID.get(15) && img_game1_44.getVisibility() != View.INVISIBLE) {
                    img_game1_44.setVisibility(View.INVISIBLE);
                }
            }

            if (secondClicked == true) {
                if (secondImage == gameListImagesID.get(0) && img_game1_11.getVisibility() != View.INVISIBLE) {
                    img_game1_11.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(1) && img_game1_12.getVisibility() != View.INVISIBLE) {
                    img_game1_12.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(2) && img_game1_13.getVisibility() != View.INVISIBLE) {
                    img_game1_13.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(3) && img_game1_14.getVisibility() != View.INVISIBLE) {
                    img_game1_14.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(4) && img_game1_21.getVisibility() != View.INVISIBLE) {
                    img_game1_21.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(5) && img_game1_22.getVisibility() != View.INVISIBLE) {
                    img_game1_22.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(6) && img_game1_23.getVisibility() != View.INVISIBLE) {
                    img_game1_23.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(7) && img_game1_24.getVisibility() != View.INVISIBLE) {
                    img_game1_24.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(8) && img_game1_31.getVisibility() != View.INVISIBLE) {
                    img_game1_31.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(9) && img_game1_32.getVisibility() != View.INVISIBLE) {
                    img_game1_32.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(10) && img_game1_33.getVisibility() != View.INVISIBLE) {
                    img_game1_33.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(11) && img_game1_34.getVisibility() != View.INVISIBLE) {
                    img_game1_34.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(12) && img_game1_41.getVisibility() != View.INVISIBLE) {
                    img_game1_41.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(13) && img_game1_42.getVisibility() != View.INVISIBLE) {
                    img_game1_42.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(14) && img_game1_43.getVisibility() != View.INVISIBLE) {
                    img_game1_43.setVisibility(View.INVISIBLE);
                } else if (secondImage == gameListImagesID.get(15) && img_game1_44.getVisibility() != View.INVISIBLE) {
                    img_game1_44.setVisibility(View.INVISIBLE);
                }
            }
        } else {
            img_game1_11.setImageResource(R.drawable.mark);
            img_game1_12.setImageResource(R.drawable.mark);
            img_game1_13.setImageResource(R.drawable.mark);
            img_game1_14.setImageResource(R.drawable.mark);
            img_game1_21.setImageResource(R.drawable.mark);
            img_game1_22.setImageResource(R.drawable.mark);
            img_game1_23.setImageResource(R.drawable.mark);
            img_game1_24.setImageResource(R.drawable.mark);
            img_game1_31.setImageResource(R.drawable.mark);
            img_game1_32.setImageResource(R.drawable.mark);
            img_game1_33.setImageResource(R.drawable.mark);
            img_game1_34.setImageResource(R.drawable.mark);
            img_game1_41.setImageResource(R.drawable.mark);
            img_game1_42.setImageResource(R.drawable.mark);
            img_game1_43.setImageResource(R.drawable.mark);
            img_game1_44.setImageResource(R.drawable.mark);
        }

        img_game1_11.setEnabled(true);
        img_game1_12.setEnabled(true);
        img_game1_13.setEnabled(true);
        img_game1_14.setEnabled(true);
        img_game1_21.setEnabled(true);
        img_game1_22.setEnabled(true);
        img_game1_23.setEnabled(true);
        img_game1_24.setEnabled(true);
        img_game1_31.setEnabled(true);
        img_game1_32.setEnabled(true);
        img_game1_33.setEnabled(true);
        img_game1_34.setEnabled(true);
        img_game1_41.setEnabled(true);
        img_game1_42.setEnabled(true);
        img_game1_43.setEnabled(true);
        img_game1_44.setEnabled(true);

        checkEnd();
    }


    private void checkEnd() {
        if(img_game1_11.getVisibility() == View.INVISIBLE &&
                img_game1_12.getVisibility() == View.INVISIBLE &&
                img_game1_13.getVisibility() == View.INVISIBLE &&
                img_game1_14.getVisibility() == View.INVISIBLE &&
                img_game1_21.getVisibility() == View.INVISIBLE &&
                img_game1_22.getVisibility() == View.INVISIBLE &&
                img_game1_23.getVisibility() == View.INVISIBLE &&
                img_game1_24.getVisibility() == View.INVISIBLE &&
                img_game1_31.getVisibility() == View.INVISIBLE &&
                img_game1_32.getVisibility() == View.INVISIBLE &&
                img_game1_33.getVisibility() == View.INVISIBLE &&
                img_game1_34.getVisibility() == View.INVISIBLE &&
                img_game1_41.getVisibility() == View.INVISIBLE &&
                img_game1_42.getVisibility() == View.INVISIBLE &&
                img_game1_43.getVisibility() == View.INVISIBLE &&
                img_game1_44.getVisibility() == View.INVISIBLE) {

            timeRemaining = 0;
            if(countDownTimer != null)
                countDownTimer.cancel();

            img_felicitari.setVisibility(View.VISIBLE);

            final MediaPlayer media = MediaPlayer.create(LevelGameOneActivity.this, Uri.parse("android.resource://com.example.japanase/raw/omedetou"));
            media.start();

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(LevelGameOneActivity.this);
                    builder.setMessage("Ai reușit să termini cu succes acest nivel!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            timeRemaining = 0;
                            if(countDownTimer != null)
                                countDownTimer.cancel();
                            Intent intent = new Intent(LevelGameOneActivity.this, GameOneActivity.class);
                            startActivity(intent);
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.setTitle("Felicitări");
                    alertDialog.setCancelable(false);
                    alertDialog.show();
                }
            }, 3000);

        }
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                updateCountownTimer();
            }

            @Override
            public void onFinish() {
                final AlertDialog.Builder builder = new AlertDialog.Builder(LevelGameOneActivity.this);
                builder.setMessage("Din păcate, timpul s-a scurs");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        timeRemaining = 0;
                        if(countDownTimer != null)
                            countDownTimer.cancel();
                        Intent intent1 = new Intent(LevelGameOneActivity.this, GameOneActivity.class);
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

    private void updateCountownTimer() {
        int minutes = (int)(timeRemaining / 1000) / 60;
        int seconds = (int) (timeRemaining / 1000) % 60;

        String timeFormated = String.format(Locale.getDefault(),
                "%02d:%02d", minutes, seconds);

        txt_time_game1.setText(timeFormated);

        if(timeRemaining < 10000) {
            txt_time_game1.setTextColor(Color.RED);
        } else {
            txt_time_game1.setTextColor(txtColor);
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

package com.example.japanase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class GameOneActivity extends AppCompatActivity {

    Button back, btn_game1_incepator, btn_game1_mediu, btn_game1_avansat;

    public static String level_type;

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
        Intent intent1 = new Intent(GameOneActivity.this, GameActivity.class);
        startActivity(intent1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_one);

        back = (Button)findViewById(R.id.btn_back_to_games_from_game_one);
        btn_game1_incepator = (Button)findViewById(R.id.btn_game1_incepator);
        btn_game1_mediu = (Button)findViewById(R.id.btn_game1_mediu);
        btn_game1_avansat = (Button)findViewById(R.id.btn_game1_avansat);

        back.getBackground().setAlpha(1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GameOneActivity.this, GameActivity.class);
                startActivity(intent1);
            }
        });

        btn_game1_incepator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_type = "incepator";
                Intent intent1 = new Intent(GameOneActivity.this, LevelGameOneActivity.class);
                startActivity(intent1);
            }
        });

        btn_game1_mediu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_type = "mediu";
                Intent intent2 = new Intent(GameOneActivity.this, LevelGameOneActivity.class);
                startActivity(intent2);
            }
        });

        btn_game1_avansat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level_type = "avansat";
                Intent intent3 = new Intent(GameOneActivity.this, LevelGameOneActivity.class);
                startActivity(intent3);
            }
        });
    }

}

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.japanase.Classes.DBHelper;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_back_to_menu_from_games, btn_quiz1, btn_quiz2, btn_quiz3, btn_game1;
    public static String quiz_type;

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
        Intent intent = new Intent(GameActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        btn_back_to_menu_from_games = (Button)findViewById(R.id.btn_back_to_menu_from_games);
        btn_quiz1 = (Button)findViewById(R.id.btn_quiz1);
        btn_quiz2 = (Button)findViewById(R.id.btn_quiz2);
        btn_quiz3 = (Button)findViewById(R.id.btn_quiz3);
        btn_game1 = (Button)findViewById(R.id.btn_game1);

        btn_back_to_menu_from_games.getBackground().setAlpha(1);
        btn_back_to_menu_from_games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        btn_quiz1.setOnClickListener(GameActivity.this);
        btn_quiz2.setOnClickListener(GameActivity.this);
        btn_quiz3.setOnClickListener(GameActivity.this);
        btn_game1.setOnClickListener(GameActivity.this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_quiz1:
                quiz_type = "quiz1";
                Intent intent1 = new Intent(GameActivity.this, LevelListActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_quiz2:
                quiz_type = "quiz2";
                Intent intent2 = new Intent(GameActivity.this, LevelListActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_quiz3:
                quiz_type = "quiz3";
                Intent intent3 = new Intent(GameActivity.this, LevelListActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_game1:
                Intent intent4 = new Intent(GameActivity.this, GameOneActivity.class);
                startActivity(intent4);
                break;
        }
    }
}

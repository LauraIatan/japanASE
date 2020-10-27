package com.example.japanase;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MenuActivity extends AppCompatActivity {

    Button btn_like;
    CircleMenu circleMenu;

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
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ești sigur că vrei să ieși din aplicație?");
        builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        builder.setNegativeButton("NU", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Exit");
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        btn_like = (Button)findViewById(R.id.btn_info);
        circleMenu = (CircleMenu)findViewById(R.id.btn_main);

        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFacebookPage("685356491671345");
            }
        });

        circleMenu.setMainMenu(Color.parseColor("#f2f2f2"), R.drawable.ase, R.mipmap.ic_action_cancel)
                .addSubMenu(Color.parseColor("#8d0019"), R.drawable.learn)
                .addSubMenu(Color.parseColor("#fde5c7"), R.drawable.game)
                .addSubMenu(Color.parseColor("#172652"), R.drawable.ml)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        switch (index) {
                            case(0):
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(MenuActivity.this, HiraganaActivity.class);
                                        startActivity(intent);
                                    }
                                }, 555);
                                break;
                            case(1):
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(MenuActivity.this, GameActivity.class);
                                        startActivity(intent);
                                    }
                                }, 555);
                                break;
                            case(2):
                                //About section --- to be developed
                                break;
                        }

                    }
                });
    }

    private void goToFacebookPage(String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + id));
            startActivity(intent);
        } catch (ActivityNotFoundException exc) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + id));
            startActivity(intent);
        }
    }
}

package com.example.japanase;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class LearnActivity extends AppCompatActivity {

    Button btn_back_to_hiraganaTable, btn_voice, btn_attention, btn_showWriting;
    TextView tv_romaji, tv_word;
    public static CircleImageView img1, img2;
    private static int ANIM;
    Animation fadeInAnim;

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
        Intent intent = new Intent(LearnActivity.this, HiraganaActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_learn);

        //Animations
        fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);

        btn_back_to_hiraganaTable = (Button)findViewById(R.id.btn_back_to_hiraganaTable);
        tv_romaji = (TextView)findViewById(R.id.tv_romaji);
        img1 = (CircleImageView)findViewById(R.id.img_firstImg);
        img2 = (CircleImageView)findViewById(R.id.img_secondImg);
        btn_voice = (Button)findViewById(R.id.btn_voiceHiragana);
        tv_word = (TextView)findViewById(R.id.tv_word);
        btn_attention = (Button)findViewById(R.id.btn_similarHirahana);
        btn_showWriting = (Button)findViewById(R.id.btn_showWriting);

        tv_romaji.setAnimation(fadeInAnim);
        img2.setAnimation(fadeInAnim);
        img1.setAnimation(fadeInAnim);
        btn_attention.setAnimation(fadeInAnim);
        btn_showWriting.setAnimation(fadeInAnim);
        btn_voice.setAnimation(fadeInAnim);

        tv_romaji.setText((HiraganaActivity.romaji).toUpperCase());

        String formatedText = "<u>"+HiraganaActivity.hiragana+"</u>"+HiraganaActivity.word;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            tv_word.setText(Html.fromHtml(formatedText, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tv_word.setText(Html.fromHtml(formatedText));
        }
        tv_word.setVisibility(View.GONE);

        btn_back_to_hiraganaTable.getBackground().setAlpha(1);
        btn_back_to_hiraganaTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnActivity.this, HiraganaActivity.class);
                startActivity(intent);
            }
        });

        final MediaPlayer media = MediaPlayer.create(LearnActivity.this, Uri.parse("android.resource://com.example.japanase/raw/" + HiraganaActivity.romaji));

        btn_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                media.start();
            }
        });

        btn_showWriting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(LearnActivity.this, "Ne va arata cum se scrie intr-un pop-up", Toast.LENGTH_LONG).show();
                showDlg(LearnActivity.this, HiraganaActivity.romaji+"_writing");
            }
        });

        btn_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";

                if(HiraganaActivity.romaji.equals("ki")){
                    text = "ki き≠さ sa\n" +
                            "ki き≠ち chi\n";
                } else if(HiraganaActivity.romaji.equals("chi")){
                    text = "chi ち≠き ki\n" +
                            "chi ち≠さ sa\n" +
                            "chi ち≠ら ra\n";
                } else if(HiraganaActivity.romaji.equals("sa")){
                    text = "sa さ≠き ki\n" +
                            "sa さ≠ち chi\n";
                } else if(HiraganaActivity.romaji.equals("ra")){
                    text = "ra ら≠ち chi\n" +
                            "ra ら≠う u";
                } else if(HiraganaActivity.romaji.equals("o")){
                    text = "o お≠む mu\n" +
                            "o お≠あ a";
                } else if(HiraganaActivity.romaji.equals("a")){
                    text = "a あ≠お o\n" +
                            "a あ≠め me";
                } else if(HiraganaActivity.romaji.equals("me")){
                    text = "me め≠あ a\n" +
                            "me め≠ぬ nu";
                } else if(HiraganaActivity.romaji.equals("mu")){
                    text = "mu む≠お o\n" +
                            "mu む≠す su";
                } else if(HiraganaActivity.romaji.equals("ko")){
                    text = "ko こ≠て te\n" +
                            "ko こ≠た ta\n" +
                            "ko こ≠に ni\n" +
                            "ko こ≠い i";
                } else if(HiraganaActivity.romaji.equals("te")){
                    text = "te て≠こ ko\n" +
                            "te て≠と te";
                } else if(HiraganaActivity.romaji.equals("ta")){
                    text = "ta た≠こ ko\n" +
                            "ta た≠に ni\n" +
                            "ta た≠な na\n";
                } else if(HiraganaActivity.romaji.equals("ni")){
                    text = "ni に≠た ta\n" +
                            "ni に≠こ ko\n";
                } else if(HiraganaActivity.romaji.equals("na")){
                    text = "na な≠た ta";
                } else if(HiraganaActivity.romaji.equals("wa")){
                    text = "wa わ≠れ re\n" +
                            "wa わ≠ね ne\n";
                } else if(HiraganaActivity.romaji.equals("re")){
                    text = "re れ≠わ wa\n" +
                            "re れ≠ね ne\n";
                } else if(HiraganaActivity.romaji.equals("ne")){
                    text = "ne ね≠わ wa\n" +
                            "ne ね≠れ re\n" +
                            "ne ね≠ぬ nu";
                } else if(HiraganaActivity.romaji.equals("ru")){
                    text = "ru る≠ろ ro\n" +
                            "ru る≠そ so\n";
                } else if(HiraganaActivity.romaji.equals("ro")){
                    text = "ro ろ≠る ru\n" +
                            "ro ろ≠そ so\n";
                } else if(HiraganaActivity.romaji.equals("so")){
                    text = "so そ≠ろ ro\n" +
                            "so そ≠る ru\n";
                } else if(HiraganaActivity.romaji.equals("tsu")){
                    text = "tsu つ≠う u\n" +
                            "tsu つ≠の no\n" +
                            "tsu つ≠へ he";
                } else if(HiraganaActivity.romaji.equals("u")){
                    text = "u う≠つ tsu\n" +
                            "u う≠ら ra";
                } else if(HiraganaActivity.romaji.equals("i")){
                    text = "i い≠り ri\n" +
                            "i い≠こ ko";
                } else if(HiraganaActivity.romaji.equals("ri")){
                    text = "ri り≠い i";
                } else if(HiraganaActivity.romaji.equals("to")){
                    text = "to と≠て te";
                } else if(HiraganaActivity.romaji.equals("ku")){
                    text = "ku く≠へ he";
                } else if(HiraganaActivity.romaji.equals("he")){
                    text = "he へ≠く ku\n" +
                            "he へ≠つ tsu";
                } else if(HiraganaActivity.romaji.equals("ke")){
                    text = "ke け≠は ha";
                } else if(HiraganaActivity.romaji.equals("ha")){
                    text = "ha は≠け ke\n" +
                            "ha は≠ほ ho\n" +
                            "ha は≠ま ma\n" +
                            "ha は≠よ yo";
                } else if(HiraganaActivity.romaji.equals("ho")){
                    text = "ho ほ≠は ha\n" +
                            "ho ほ≠まma";
                } else if(HiraganaActivity.romaji.equals("no")){
                    text = "no の≠つ tsu";
                } else if(HiraganaActivity.romaji.equals("nu")){
                    text = "nu ぬ≠ね ne\n" +
                            "nu ぬ≠め me";
                } else if(HiraganaActivity.romaji.equals("shi")){
                    text = "shi し≠も mo";
                } else if(HiraganaActivity.romaji.equals("mo")){
                    text = "mo も≠し shi\n" +
                            "mo も≠ま ma";
                } else if(HiraganaActivity.romaji.equals("su")){
                    text = "su す≠む mu";
                } else if(HiraganaActivity.romaji.equals("se")){
                    text = "se せ≠や ya";
                } else if(HiraganaActivity.romaji.equals("ya")){
                    text = "ya や≠せ se\n" +
                            "ya や≠ゆ yu";
                } else if(HiraganaActivity.romaji.equals("ma")){
                    text = "ma ま≠は ha\n" +
                            "ma ま≠ほ ho\n" +
                            "ma ま≠よ yo\n" +
                            "ma ま≠も mo";
                } else if(HiraganaActivity.romaji.equals("yo")){
                    text = "yo よ≠は ha\n" +
                            "yo よ≠ま ma";
                } else if(HiraganaActivity.romaji.equals("yu")){
                    text = "yu ゆ≠や ya";
                } else if(HiraganaActivity.romaji.equals("e")){
                    text = "e え≠ん n";
                } else if(HiraganaActivity.romaji.equals("n")){
                    text = "n ん≠え e";
                } else
                    text="";

                if (!text.equals(""))
                    showDlgAttention(LearnActivity.this, text, "Aveți grijă să NU încurcați următoarele semne hiragana!");
                else
                    showDlgAttention(LearnActivity.this, "\uD83D\uDE0A", "Nu există hiragana similare!");
            }
        });

        img1.setVisibility(View.GONE);

        if (HiraganaActivity.romaji.equals("o")) {
            img2.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji, "drawable", this.getPackageName()));
            img1.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji + "2", "drawable", this.getPackageName()));
        } else if (HiraganaActivity.romaji.equals("i")) {
            img2.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji, "drawable", this.getPackageName()));
            img1.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji + "2", "drawable", this.getPackageName()));
        } else if (HiraganaActivity.romaji.equals("so")) {
            img2.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji, "drawable", this.getPackageName()));
            img1.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji + "2", "drawable", this.getPackageName()));
        } else if (HiraganaActivity.romaji.equals("ne")) {
            img2.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji, "drawable", this.getPackageName()));
            img1.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji + "2", "drawable", this.getPackageName()));
        } else if (HiraganaActivity.romaji.equals("mo")) {
            img2.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji, "drawable", this.getPackageName()));
            img1.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji + "2", "drawable", this.getPackageName()));
        } else if (HiraganaActivity.romaji.equals("hi")) {
            img2.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji, "drawable", this.getPackageName()));
            img1.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji + "2", "drawable", this.getPackageName()));
        } else {
            img2.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji + "1", "drawable", this.getPackageName()));
            img1.setImageResource(this.getResources().getIdentifier(HiraganaActivity.romaji + "2", "drawable", this.getPackageName()));
        }

        img2.setOnClickListener(new View.OnClickListener() {
//
            @Override
            public void onClick(View v) {

                ANIM = 3000;

                if (HiraganaActivity.romaji.equals("o")) {
                    img2.setImageResource(R.drawable.o1);
                } else if (HiraganaActivity.romaji.equals("i")) {
                    img2.setImageResource(R.drawable.i1);
                } else if (HiraganaActivity.romaji.equals("so")) {
                    img2.setImageResource(R.drawable.so1);
                }  else if (HiraganaActivity.romaji.equals("ne")) {
                    img2.setImageResource(R.drawable.ne1);
                } else if (HiraganaActivity.romaji.equals("mo")) {
                    img2.setImageResource(R.drawable.mo1);
                } else if (HiraganaActivity.romaji.equals("hi")) {
                    img2.setImageResource(R.drawable.hi1);
                }

                img1.setAlpha(1f);
                img1.setVisibility(View.VISIBLE);
                img2.setAlpha(0f);
                img2.setVisibility(View.VISIBLE);
                tv_word.setVisibility(View.VISIBLE);

                img2.setClickable(false);
                img1.setClickable(false);

                img2.animate().alpha(1f).setDuration(ANIM).setListener(null);

                img1.animate().alpha(0f).setDuration(ANIM).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        img1.setVisibility(View.GONE);
                        tv_word.setVisibility(View.GONE);

                        img2.setClickable(true);
                        img1.setClickable(true);

                        if(HiraganaActivity.romaji.equals("o")) {
                            img2.setImageResource(R.drawable.o);
                        } else  if(HiraganaActivity.romaji.equals("i")) {
                            img2.setImageResource(R.drawable.i);
                        } else  if(HiraganaActivity.romaji.equals("so")) {
                            img2.setImageResource(R.drawable.so);
                        } else  if(HiraganaActivity.romaji.equals("ne")) {
                            img2.setImageResource(R.drawable.ne);
                        } else  if(HiraganaActivity.romaji.equals("mo")) {
                            img2.setImageResource(R.drawable.mo);
                        } else  if(HiraganaActivity.romaji.equals("hi")) {
                            img2.setImageResource(R.drawable.hi);
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }
        });
    }

    public void showDlg(Activity activity, String imgSource) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_layout);

        ImageView img = (ImageView)dialog.findViewById(R.id.img_hiraganaWriting);
        img.setImageResource(this.getResources().getIdentifier(imgSource, "drawable", this.getPackageName()));

        dialog.show();
    }

    public void showDlgAttention(Activity activity, String text, String text2) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.attention_layout);

        TextView txt = (TextView)dialog.findViewById(R.id.txt_attention);
        TextView txt2 = (TextView)dialog.findViewById(R.id.txt_info);
        txt.setText(text);
        txt2.setText(text2);

        dialog.show();
    }

}

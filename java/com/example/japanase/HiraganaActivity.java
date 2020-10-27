package com.example.japanase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HiraganaActivity extends AppCompatActivity implements View.OnClickListener{

    public static String romaji;
    public static String hiragana;
    public static String word;

    Button btn_a, btn_i, btn_u, btn_e, btn_o, btn_n,
            btn_ka, btn_ki, btn_ku, btn_ke, btn_ko,
            btn_sa, btn_shi, btn_su, btn_se, btn_so,
            btn_ta, btn_chi, btn_tsu, btn_te, btn_to,
            btn_na, btn_ni, btn_nu, btn_ne, btn_no,
            btn_ha, btn_hi, btn_fu, btn_he, btn_ho,
            btn_ma, btn_mi, btn_mu, btn_me, btn_mo,
            btn_ra, btn_ri, btn_ru, btn_re, btn_ro,
            btn_ya, btn_wa, btn_yu, btn_wo, btn_yo, btn_back_to_menu;

    TextView tv_rotateButton;
    Animation alpha;

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
        Intent intent = new Intent(HiraganaActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hiragana);

        alpha = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        btn_a = (Button)findViewById(R.id.btn_a);
        btn_i = (Button)findViewById(R.id.btn_i);
        btn_u = (Button)findViewById(R.id.btn_u);
        btn_e = (Button)findViewById(R.id.btn_e);
        btn_o = (Button)findViewById(R.id.btn_o);
        btn_n = (Button)findViewById(R.id.btn_n);

        btn_a.setOnClickListener(HiraganaActivity.this);
        btn_i.setOnClickListener(HiraganaActivity.this);
        btn_u.setOnClickListener(HiraganaActivity.this);
        btn_e.setOnClickListener(HiraganaActivity.this);
        btn_o.setOnClickListener(HiraganaActivity.this);
        btn_n.setOnClickListener(HiraganaActivity.this);


        btn_ka = (Button)findViewById(R.id.btn_ka);
        btn_ki = (Button)findViewById(R.id.btn_ki);
        btn_ku = (Button)findViewById(R.id.btn_ku);
        btn_ke = (Button)findViewById(R.id.btn_ke);
        btn_ko = (Button)findViewById(R.id.btn_ko);

        btn_ka.setOnClickListener(HiraganaActivity.this);
        btn_ki.setOnClickListener(HiraganaActivity.this);
        btn_ku.setOnClickListener(HiraganaActivity.this);
        btn_ke.setOnClickListener(HiraganaActivity.this);
        btn_ko.setOnClickListener(HiraganaActivity.this);


        btn_sa = (Button)findViewById(R.id.btn_sa);
        btn_shi = (Button)findViewById(R.id.btn_shi);
        btn_su = (Button)findViewById(R.id.btn_su);
        btn_se = (Button)findViewById(R.id.btn_se);
        btn_so = (Button)findViewById(R.id.btn_so);

        btn_sa.setOnClickListener(HiraganaActivity.this);
        btn_shi.setOnClickListener(HiraganaActivity.this);
        btn_su.setOnClickListener(HiraganaActivity.this);
        btn_se.setOnClickListener(HiraganaActivity.this);
        btn_so.setOnClickListener(HiraganaActivity.this);

        btn_ta = (Button)findViewById(R.id.btn_ta);
        btn_chi = (Button)findViewById(R.id.btn_chi);
        btn_tsu = (Button)findViewById(R.id.btn_tsu);
        btn_te = (Button)findViewById(R.id.btn_te);
        btn_to = (Button)findViewById(R.id.btn_to);

        btn_ta.setOnClickListener(HiraganaActivity.this);
        btn_chi.setOnClickListener(HiraganaActivity.this);
        btn_tsu.setOnClickListener(HiraganaActivity.this);
        btn_te.setOnClickListener(HiraganaActivity.this);
        btn_to.setOnClickListener(HiraganaActivity.this);

        btn_na = (Button)findViewById(R.id.btn_na);
        btn_ni = (Button)findViewById(R.id.btn_ni);
        btn_nu = (Button)findViewById(R.id.btn_nu);
        btn_ne = (Button)findViewById(R.id.btn_ne);
        btn_no = (Button)findViewById(R.id.btn_no);

        btn_na.setOnClickListener(HiraganaActivity.this);
        btn_ni.setOnClickListener(HiraganaActivity.this);
        btn_nu.setOnClickListener(HiraganaActivity.this);
        btn_ne.setOnClickListener(HiraganaActivity.this);
        btn_no.setOnClickListener(HiraganaActivity.this);

        btn_ha = (Button)findViewById(R.id.btn_ha);
        btn_hi = (Button)findViewById(R.id.btn_hi);
        btn_fu = (Button)findViewById(R.id.btn_fu);
        btn_he = (Button)findViewById(R.id.btn_he);
        btn_ho = (Button)findViewById(R.id.btn_ho);

        btn_ha.setOnClickListener(HiraganaActivity.this);
        btn_hi.setOnClickListener(HiraganaActivity.this);
        btn_fu.setOnClickListener(HiraganaActivity.this);
        btn_he.setOnClickListener(HiraganaActivity.this);
        btn_ho.setOnClickListener(HiraganaActivity.this);

        btn_ma = (Button)findViewById(R.id.btn_ma);
        btn_mi = (Button)findViewById(R.id.btn_mi);
        btn_mu = (Button)findViewById(R.id.btn_mu);
        btn_me = (Button)findViewById(R.id.btn_me);
        btn_mo = (Button)findViewById(R.id.btn_mo);

        btn_ma.setOnClickListener(HiraganaActivity.this);
        btn_mi.setOnClickListener(HiraganaActivity.this);
        btn_mu.setOnClickListener(HiraganaActivity.this);
        btn_me.setOnClickListener(HiraganaActivity.this);
        btn_mo.setOnClickListener(HiraganaActivity.this);

        btn_ra = (Button)findViewById(R.id.btn_ra);
        btn_ri = (Button)findViewById(R.id.btn_ri);
        btn_ru = (Button)findViewById(R.id.btn_ru);
        btn_re = (Button)findViewById(R.id.btn_re);
        btn_ro = (Button)findViewById(R.id.btn_ro);

        btn_ra.setOnClickListener(HiraganaActivity.this);
        btn_ri.setOnClickListener(HiraganaActivity.this);
        btn_ru.setOnClickListener(HiraganaActivity.this);
        btn_re.setOnClickListener(HiraganaActivity.this);
        btn_ro.setOnClickListener(HiraganaActivity.this);

        btn_ya = (Button)findViewById(R.id.btn_ya);
        btn_wa = (Button)findViewById(R.id.btn_wa);
        btn_yu = (Button)findViewById(R.id.btn_yu);
        btn_wo = (Button)findViewById(R.id.btn_wo);
        btn_yo = (Button)findViewById(R.id.btn_yo);

        btn_ya.setOnClickListener(HiraganaActivity.this);
        btn_wa.setOnClickListener(HiraganaActivity.this);
        btn_yu.setOnClickListener(HiraganaActivity.this);
        btn_wo.setOnClickListener(HiraganaActivity.this);
        btn_yo.setOnClickListener(HiraganaActivity.this);

        btn_back_to_menu = (Button)findViewById(R.id.btn_back_to_menu);
        btn_back_to_menu.getBackground().setAlpha(1);
        btn_back_to_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HiraganaActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        tv_rotateButton = (TextView)findViewById(R.id.tv_hiraganaTable);
        tv_rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_a.startAnimation(alpha);
                btn_i.startAnimation(alpha);
                btn_u.startAnimation(alpha);
                btn_e.startAnimation(alpha);
                btn_o.startAnimation(alpha);
                btn_n.startAnimation(alpha);

                btn_ka.startAnimation(alpha);
                btn_ki.startAnimation(alpha);
                btn_ku.startAnimation(alpha);
                btn_ke.startAnimation(alpha);
                btn_ko.startAnimation(alpha);

                btn_sa.startAnimation(alpha);
                btn_shi.startAnimation(alpha);
                btn_su.startAnimation(alpha);
                btn_se.startAnimation(alpha);
                btn_so.startAnimation(alpha);

                btn_ta.startAnimation(alpha);
                btn_chi.startAnimation(alpha);
                btn_tsu.startAnimation(alpha);
                btn_te.startAnimation(alpha);
                btn_to.startAnimation(alpha);

                btn_na.startAnimation(alpha);
                btn_ni.startAnimation(alpha);
                btn_nu.startAnimation(alpha);
                btn_ne.startAnimation(alpha);
                btn_no.startAnimation(alpha);

                btn_ha.startAnimation(alpha);
                btn_hi.startAnimation(alpha);
                btn_fu.startAnimation(alpha);
                btn_he.startAnimation(alpha);
                btn_ho.startAnimation(alpha);

                btn_ma.startAnimation(alpha);
                btn_mi.startAnimation(alpha);
                btn_mu.startAnimation(alpha);
                btn_me.startAnimation(alpha);
                btn_mo.startAnimation(alpha);

                btn_ra.startAnimation(alpha);
                btn_ri.startAnimation(alpha);
                btn_ru.startAnimation(alpha);
                btn_re.startAnimation(alpha);
                btn_ro.startAnimation(alpha);

                btn_ya.startAnimation(alpha);
                btn_wa.startAnimation(alpha);
                btn_yu.startAnimation(alpha);
                btn_wo.startAnimation(alpha);
                btn_yo.startAnimation(alpha);
            }
        });

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_a:
                hiragana="A";
                word = "crobat";
                romaji = "a";
                Intent intent1 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_i:
                hiragana="I";
                word = "ndian";
                romaji = "i";
                Intent intent2 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_u:
                hiragana="U";
                word = "reche";
                romaji = "u";
                Intent intent3 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_e:
                hiragana="E";
                word = "cvestru";
                romaji = "e";
                Intent intent4 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_o:
                hiragana="O";
                word = "glindă";
                romaji = "o";
                Intent intent5 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_n:
                hiragana="N";
                word = "";
                romaji = "n";
                Intent intent6 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent6);
                break;
            case R.id.btn_ka:
                hiragana="Ca";
                word = "pricorn";
                romaji = "ka";
                Intent intent7 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent7);;
                break;
            case R.id.btn_ki:
                hiragana="Chi";
                word = "paros";
                romaji = "ki";
                Intent intent8 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent8);
                break;
            case R.id.btn_ku:
                hiragana="Cu";
                word = "mpănă";
                romaji = "ku";
                Intent intent9 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent9);
                break;
            case R.id.btn_ke:
                hiragana="Che";
                word = "ie";
                romaji = "ke";
                Intent intent10 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent10);
                break;
            case R.id.btn_ko:
                hiragana="Co";
                word = "lac";
                romaji = "ko";
                Intent intent11 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent11);
                break;
            case R.id.btn_sa:
                hiragana="Sa";
                word = "bie";
                romaji = "sa";
                Intent intent12 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent12);
                break;
            case R.id.btn_shi:
                hiragana="Și";
                word = "ret";
                romaji = "shi";
                Intent intent13 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent13);
                break;
            case R.id.btn_su:
                hiragana="Su";
                word = "rlă";
                romaji = "su";
                Intent intent14 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent14);
                break;
            case R.id.btn_se:
                hiragana="Se";
                word = "ceră";
                romaji = "se";
                Intent intent15 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent15);
                break;
            case R.id.btn_so:
                hiragana="So";
                word = "lz";
                romaji = "so";
                Intent intent16 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent16);
                break;
            case R.id.btn_ta:
                hiragana="Ta";
                word = "";
                romaji = "ta";
                Intent intent17 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent17);
                break;
            case R.id.btn_chi:
                hiragana="Ci";
                word = "nci";
                romaji = "chi";
                Intent intent18 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent18);
                break;
            case R.id.btn_tsu:
                hiragana="Țu";
                word = "gui";
                romaji = "tsu";
                Intent intent19 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent19);
                break;
            case R.id.btn_te:
                hiragana="Te";
                word = "legraf";
                romaji = "te";
                Intent intent20 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent20);
                break;
            case R.id.btn_to:
                hiragana="To";
                word = "bă";
                romaji = "to";
                Intent intent21 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent21);
                break;
            case R.id.btn_na:
                hiragana="Na";
                word = "sture";
                romaji = "na";
                Intent intent22 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent22);
                break;
            case R.id.btn_ni:
                hiragana="Ni";
                word = "covală";
                romaji = "ni";
                Intent intent23 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent23);
                break;
            case R.id.btn_nu:
                hiragana="Nu";
                word = "făr";
                romaji = "nu";
                Intent intent24 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent24);
                break;
            case R.id.btn_ne:
                hiragana="Ne";
                word = "on";
                romaji = "ne";
                Intent intent25 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent25);
                break;
            case R.id.btn_no:
                hiragana="No";
                word = "d";
                romaji = "no";
                Intent intent26 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent26);
                break;
            case R.id.btn_ha:
                hiragana="Ha";
                word = "lteră";
                romaji = "ha";
                Intent intent27 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent27);
                break;
            case R.id.btn_hi:
                hiragana="Hi";
                word = "lar";
                romaji = "hi";
                Intent intent28 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent28);
                break;
            case R.id.btn_fu:
                hiragana="Fu";
                word = "rnică";
                romaji = "fu";
                Intent intent29 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent29);
                break;
            case R.id.btn_he:
                hiragana="He";
                word = "xagon";
                romaji = "he";
                Intent intent30 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent30);
                break;
            case R.id.btn_ho:
                hiragana="Ho";
                word = "rn";
                romaji = "ho";
                Intent intent31 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent31);
                break;
            case R.id.btn_ma:
                hiragana="Ma";
                word = "cara";
                romaji = "ma";
                Intent intent32 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent32);
                break;
            case R.id.btn_mi:
                hiragana="Mi";
                word = "leu";
                romaji = "mi";
                Intent intent33 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent33);
                break;
            case R.id.btn_mu:
                hiragana="Mu";
                word = "gur";
                romaji = "mu";
                Intent intent34 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent34);
                break;
            case R.id.btn_me:
                hiragana="Me";
                word = "dalion";
                romaji = "me";
                Intent intent35 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent35);
                break;
            case R.id.btn_mo:
                hiragana="Mo";
                word = "meală";
                romaji = "mo";
                Intent intent36 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent36);
                break;
            case R.id.btn_ra:
                hiragana="Ra";
                word = "liu";
                romaji = "ra";
                Intent intent37 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent37);
                break;
            case R.id.btn_ri:
                hiragana="Ri";
                word = "glă";
                romaji = "ri";
                Intent intent38 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent38);
                break;
            case R.id.btn_ru:
                hiragana="Ru";
                word = "csac";
                romaji = "ru";
                Intent intent39 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent39);
                break;
            case R.id.btn_re:
                hiragana="Re";
                word = "paus";
                romaji = "re";
                Intent intent40 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent40);
                break;
            case R.id.btn_ro:
                hiragana="Ro";
                word = "ată";
                romaji = "ro";
                Intent intent41 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent41);
                break;
            case R.id.btn_ya:
                hiragana="Ia";
                word = "c";
                romaji = "ya";
                Intent intent42 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent42);
                break;
            case R.id.btn_wa:
                hiragana="Oa";
                word = "ie";
                romaji = "wa";
                Intent intent43 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent43);
                break;
            case R.id.btn_yu:
                hiragana="Iu";
                word = "reș";
                romaji = "yu";
                Intent intent44 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent44);
                break;
            case R.id.btn_wo:
                hiragana="O";
                word = "val";
                romaji = "wo";
                Intent intent45 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent45);
                break;
            case R.id.btn_yo:
                hiragana="Io";
                word = "lă";
                romaji = "yo";
                Intent intent46 = new Intent(HiraganaActivity.this, LearnActivity.class);
                startActivity(intent46);
                break;
        }
    }
}

package com.example.japanase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RecognizeActivity extends AppCompatActivity {

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
        Intent intent = new Intent(RecognizeActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    Button btn_back, btn_pickImg, btn_takePic;
    ImageView img_viewHiragana, img_pictureHiragana;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_recognize);

        btn_back = (Button) findViewById(R.id.btn_back_to_menu_from_recognize);
        btn_pickImg = (Button)findViewById(R.id.btn_pickImage);
        btn_takePic = (Button)findViewById(R.id.btn_takePic);
        img_viewHiragana = (ImageView)findViewById(R.id.img_showHiragana);
        img_pictureHiragana = (ImageView)findViewById(R.id.img_pictureHiragana);

        btn_back.getBackground().setAlpha(1);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizeActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        btn_pickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picImage();
            }
        });

        btn_takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePic();
            }
        });
    }

    public void picImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void takePic() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();

            try{

                bitmap = MediaStore.Images.Media.getBitmap(RecognizeActivity.this.getContentResolver(), uri);
                img_pictureHiragana.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

            if(bitmap != null) {

                FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
                FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();

                detector.processImage(image)
                        .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                List<FirebaseVisionText.TextBlock> blocks = firebaseVisionText.getTextBlocks();
                                if(blocks.size() == 0) {
                                    Toast.makeText(RecognizeActivity.this, "Nu s-a gasit text in imagine", Toast.LENGTH_SHORT).show();
                                } else {

                                    for(FirebaseVisionText.TextBlock block : firebaseVisionText.getTextBlocks()) {
                                        String text = block.getText();

                                        String hiragana = text.toLowerCase();
                                        String[] chars = {"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko", "sa", "shi", "su", "se", "so", "ta", "chi", "tsu", "te", "to", "n",
                                                "na", "ni", "nu", "ne", "no", "ha", "hi", "fu", "he", "ho","ma", "mi", "mu", "me", "mo", "ra", "ri", "ru", "re", "ro", "wa", "ya", "yu", "yo", "wo"};

                                        if (Arrays.asList(chars).contains(hiragana)) {

                                            if (hiragana.equals("o")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana, "drawable", RecognizeActivity.this.getPackageName()));
                                            } else if (hiragana.equals("i")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana, "drawable", RecognizeActivity.this.getPackageName()));
                                            } else if (hiragana.equals("so")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana, "drawable", RecognizeActivity.this.getPackageName()));
                                            } else if (hiragana.equals("ne")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana , "drawable", RecognizeActivity.this.getPackageName()));
                                            } else if (hiragana.equals("mo")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana, "drawable", RecognizeActivity.this.getPackageName()));
                                            } else if (hiragana.equals("hi")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana, "drawable", RecognizeActivity.this.getPackageName()));
                                            } else {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana + "1", "drawable", RecognizeActivity.this.getPackageName()));
                                            }
                                        } else
                                            img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier("incorrect", "drawable", RecognizeActivity.this.getPackageName()));
                                    }
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            } else {
                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier("incorrect", "drawable", RecognizeActivity.this.getPackageName()));
                Toast.makeText(RecognizeActivity.this, "Nu ai selectat nicio imagine", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == 2 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            bitmap = (Bitmap)extras.get("data");
            img_pictureHiragana.setImageBitmap(bitmap);

            if(bitmap != null) {

                FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(bitmap);
                FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();

                detector.processImage(image)
                        .addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                            @Override
                            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                                List<FirebaseVisionText.TextBlock> blocks = firebaseVisionText.getTextBlocks();
                                if(blocks.size() == 0) {
                                    Toast.makeText(RecognizeActivity.this, "Nu s-a gasit text in imagine", Toast.LENGTH_SHORT).show();
                                } else {

                                    for(FirebaseVisionText.TextBlock block : firebaseVisionText.getTextBlocks()) {
                                        String text = block.getText();

                                        String hiragana = text.toLowerCase();
                                        String[] chars = {"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko", "sa", "shi", "su", "se", "so", "ta", "chi", "tsu", "te", "to", "n",
                                                "na", "ni", "nu", "ne", "no", "ha", "hi", "fu", "he", "ho","ma", "mi", "mu", "me", "mo", "ra", "ri", "ru", "re", "ro", "wa", "ya", "yu", "yo", "wo"};

                                        if (Arrays.asList(chars).contains(hiragana)) {

                                            if (hiragana.equals("o")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana, "drawable", RecognizeActivity.this.getPackageName()));
                                            } else if (hiragana.equals("i")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana, "drawable", RecognizeActivity.this.getPackageName()));
                                            } else if (hiragana.equals("so")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana, "drawable", RecognizeActivity.this.getPackageName()));
                                            } else if (hiragana.equals("ne")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana , "drawable", RecognizeActivity.this.getPackageName()));
                                            } else if (hiragana.equals("mo")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana, "drawable", RecognizeActivity.this.getPackageName()));
                                            } else if (hiragana.equals("hi")) {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana, "drawable", RecognizeActivity.this.getPackageName()));
                                            } else {
                                                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier(hiragana + "1", "drawable", RecognizeActivity.this.getPackageName()));
                                            }
                                        } else
                                            img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier("incorrect", "drawable", RecognizeActivity.this.getPackageName()));
                                    }
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            } else {
                img_viewHiragana.setImageResource(RecognizeActivity.this.getResources().getIdentifier("incorrect", "drawable", RecognizeActivity.this.getPackageName()));
                Toast.makeText(RecognizeActivity.this, "Nu ai selectat nicio imagine", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

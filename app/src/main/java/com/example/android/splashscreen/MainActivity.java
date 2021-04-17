package com.example.android.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static int splashScreen = 3000;
    Animation topAnim , bottomAnim;
    ImageView imageView;
    TextView textView1 , textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topAnim = AnimationUtils.loadAnimation(this ,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this ,R.anim.bottom_animation);
        imageView = findViewById(R.id.imageView);
        textView1 = findViewById(R.id.text);
        textView2 = findViewById(R.id.text2);
        imageView.setAnimation(topAnim);
        textView1.setAnimation(bottomAnim);
        textView2.setAnimation(bottomAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this , loginActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(imageView,"logo_image");
                pairs[1] = new Pair<View,String>(textView1,"logo_text");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this , pairs);
                startActivity(intent,options.toBundle());
            }
        },splashScreen);
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }
}
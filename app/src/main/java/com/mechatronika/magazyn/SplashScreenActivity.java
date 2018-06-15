package com.mechatronika.magazyn;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {

    float rotation;
    private CountDownTimer countDownTimer;
    private static int SPLASH_TIME_OUT = 5000;

    long time;
    long duration = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final ImageView logo_agh = (ImageView) findViewById(R.id.agh_logo);
        final ImageView logo_wimir = (ImageView) findViewById(R.id.wimir_logo);
        final ImageView gear1 = (ImageView) findViewById(R.id.gear);
        final ImageView gear2 = (ImageView) findViewById(R.id.gear2);
        final ImageView gear3 = (ImageView) findViewById(R.id.gear3);

        Animation animation_start3 = AnimationUtils.loadAnimation(this, R.anim.splash_screen_start3);
        Animation animation_start = AnimationUtils.loadAnimation(this, R.anim.splash_screen_start);
        Animation animation_start2 = AnimationUtils.loadAnimation(this, R.anim.splash_screen_start2);

        //starting first animation

        gear1.startAnimation(animation_start3);
        gear2.startAnimation(animation_start3);
        gear3.startAnimation(animation_start3);
        logo_agh.startAnimation(animation_start);
        logo_wimir.startAnimation(animation_start2);


        rotation = gear1.getRotation();
        time = SPLASH_TIME_OUT;
        //splash screen during time
        countDownTimer = new CountDownTimer(SPLASH_TIME_OUT, duration) {
            @Override
            public void onTick(long millisUntilFinished) {


                time = millisUntilFinished / duration;

                long temp = 0;
                long temp2=0;
                if (time > 0) {
                    temp = time / time;
                    rotation += temp;
                }
                gear1.setRotation(rotation);
                gear2.setRotation(-rotation);
                gear3.setRotation(rotation);

            }
            @Override
            public void onFinish() {

            }
        }.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashScreenActivity.this,MainActivity.class);
               startActivity(homeIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        },SPLASH_TIME_OUT);

    }

}

package com.example.hp.bmi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashAct extends AppCompatActivity {

    ImageView imageView;
    Animation animation1,animation2,animation3;
    TextView tvAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView= (ImageView) findViewById(R.id.imageView);
        tvAppName= (TextView) findViewById(R.id.tvAppName);
        animation1= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        animation2=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        animation3=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);

        imageView.startAnimation(animation1);
    /*
        animation2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                tvAppName.startAnimation(animation3);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
*/
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tvAppName.startAnimation(animation3);

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }).start();
    }
}

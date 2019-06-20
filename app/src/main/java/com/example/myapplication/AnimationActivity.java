package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class AnimationActivity extends AppCompatActivity {


    ImageView viewImage1, viewImage2;
    TextView viewText;
    Handler handler = new Handler();
    int counter = 5;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(runnable);
            if (counter < 25) {
                viewText.setTranslationX(viewText.getTranslationX() + counter);
                viewImage2.setTranslationX(viewImage2.getTranslationX() + counter);
                counter++;
                startTranslation();
            } else {

            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        viewImage1 = findViewById(R.id.ivWinch1);
        viewImage2 = findViewById(R.id.ivWinch2);
        viewText = findViewById(R.id.tvGetATruck);
        startAnimation();
    }

    private void startAnimation() {

//        Animation anim_scale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
//        viewImage.startAnimation(anim_scale);
        final int millisec = 700;
        YoYo.with(Techniques.FadeOut)
                .delay(1000)
                .duration(millisec)
                .repeat(0)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        viewImage1.setVisibility(View.INVISIBLE);
                        YoYo.with(Techniques.FadeIn)
//                                .duration(millisec)
                                .duration(millisec / 2)
                                .repeat(0)
                                .onEnd(new YoYo.AnimatorCallback() {
                                    @Override
                                    public void call(Animator animator) {
                                        viewImage2.setVisibility(View.VISIBLE);
                                        //start translation
                                        startTranslation();
                                    }
                                })
                                .playOn(viewImage2);
                    }
                })
                .playOn(viewImage1);


    }

    private void startTranslation() {
        handler.postDelayed(runnable, 200);
    }


}

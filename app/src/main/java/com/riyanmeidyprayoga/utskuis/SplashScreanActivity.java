package com.riyanmeidyprayoga.utskuis;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.riyanmeidyprayoga.utskuis.ProgressBar_Anim;

public class SplashScreanActivity extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;

    Animation atgsc, atgsc1,atgsc2,atgsc3, atgsc4;
    TextView tvbiodata, tvs, tvs1,tvs2,tvs3;

    // progresbar
    private TextView textView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screan);

        // Proses Animasi
        atgsc = AnimationUtils.loadAnimation(this, R.anim.atgsc);
        tvbiodata = findViewById(R.id.tvbiodata);
        tvbiodata.startAnimation(atgsc);

        atgsc1 = AnimationUtils.loadAnimation(this, R.anim.atgsc1);
        tvs = findViewById(R.id.tvs);
        tvs.startAnimation(atgsc1);

        atgsc2 = AnimationUtils.loadAnimation(this, R.anim.atgsc2);
        tvs1 = findViewById(R.id.tvs1);
        tvs1.startAnimation(atgsc2);

        atgsc3 = AnimationUtils.loadAnimation(this, R.anim.atgsc3);
        tvs2 = findViewById(R.id.tvs2);
        tvs2.startAnimation(atgsc3);

        atgsc4 = AnimationUtils.loadAnimation(this, R.anim.atgsc4);
        tvs3 = findViewById(R.id.tvs3);
        tvs3.startAnimation(atgsc4);

        //Kode Loading animasi
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottieAnimationView);
        startCheckAnimation();

        // loading splas srean berapa lama
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(6000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent a = new Intent(SplashScreanActivity.this, MainActivity.class);
                    startActivity(a);
                    finish();
                }
            }
        };
        thread.start();

        //progresbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //full
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        textView = (TextView)findViewById(R.id.text_view);

        progressBar.setMax(100);
        progressBar.setScaleY(3);

        ProgressAnimation();
    }

    //mejalankan animasi loading
    private void startCheckAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f,1f).setDuration(6000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lottieAnimationView.setProgress((float)animation.getAnimatedValue());
            }
        });
        if (lottieAnimationView.getProgress() == 0f) {
            animator.setStartDelay(0);
            animator.start();
        } else {
            lottieAnimationView.setProgress(0f);
        }
    }

    // progres bar
    private void ProgressAnimation() {
        ProgressBar_Anim anim = new ProgressBar_Anim(this, progressBar, textView, 0f, 100f);
        anim.setDuration(6000);
        progressBar.setAnimation(anim);
    }
}

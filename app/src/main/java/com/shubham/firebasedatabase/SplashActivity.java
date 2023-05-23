package com.shubham.firebasedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.shubham.firebasedatabase.Login.LoginActivity;
import com.shubham.firebasedatabase.RealtimeDatabase.MainActivity;
import com.shubham.firebasedatabase.databinding.ActivitySpalshBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySpalshBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpalshBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        sharedPreferences = getApplicationContext().getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        binding.name.animate().translationY(5000).setDuration(1000).setStartDelay(4500);
        binding.lottieAnimationView.animate().translationY(5000).setDuration(1000).setStartDelay(4500);
        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.splashtextanimation2);
        binding.name.startAnimation(animation);

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {

                if (!sharedPreferences.getAll().containsKey("Email")) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                finish();
            }
        }.start();

    }
}
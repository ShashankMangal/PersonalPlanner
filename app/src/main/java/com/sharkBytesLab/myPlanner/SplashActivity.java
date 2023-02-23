package com.sharkBytesLab.myPlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (auth.getCurrentUser()!=null) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        thread.start();
    }
}
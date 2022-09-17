package com.akbar.coronavirus.splash;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.akbar.coronavirus.Home;
import com.akbar.coronavirus.R;


public class Splash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Home.class);
                startActivity(i);
                finish();
            }
        },2000 );
    }
}

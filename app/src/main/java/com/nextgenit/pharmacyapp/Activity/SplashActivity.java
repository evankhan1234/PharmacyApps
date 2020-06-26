package com.nextgenit.pharmacyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nextgenit.pharmacyapp.R;
import com.nextgenit.pharmacyapp.Utils.SharedPreferenceUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToLoginPage();
            }
        }, 3000);
    }
    private void goToLoginPage() {
        if (SharedPreferenceUtil.getUserID(SplashActivity.this)==null||SharedPreferenceUtil.getUserID(SplashActivity.this).equals("")) {
            Intent i = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        } else {
            startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
            finish();

        }

    }
}
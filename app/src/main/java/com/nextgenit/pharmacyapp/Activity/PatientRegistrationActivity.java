package com.nextgenit.pharmacyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.nextgenit.pharmacyapp.R;

public class PatientRegistrationActivity extends AppCompatActivity {

    private ImageButton btn_header_back_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);
        btn_header_back_=findViewById(R.id.btn_header_back_);
        btn_header_back_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
package com.nextgenit.pharmacyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nextgenit.pharmacyapp.R;

public class TempActivity extends AppCompatActivity {

    ImageView img_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        img_user=findViewById(R.id.img_user);
       Glide.with(this).load("https://nationaltoday.com/wp-content/uploads/2019/03/national-doctors-day.jpg").placeholder(R.mipmap.ic_launcher).into(img_user);

    }
}
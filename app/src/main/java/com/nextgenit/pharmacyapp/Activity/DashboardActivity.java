package com.nextgenit.pharmacyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nextgenit.pharmacyapp.Adapter.DashboardAdapter;
import com.nextgenit.pharmacyapp.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private RecyclerView rcv_list;
    private DashboardAdapter dashboardAdapter = null;
    private Activity mActivity;
    private FloatingActionButton btn_new;
    ArrayList<String> data= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv_list=findViewById(R.id.rcv_list);
        btn_new=findViewById(R.id.btn_new);
        mActivity=this;
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_list.setLayoutManager(lm);
        loadData();
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this,PatientRegistrationActivity.class));
            }
        });
    }
    private void loadData() {

        for (int i=0;i<10;i++){
            data.add("String"+i);
        }
        dashboardAdapter = new DashboardAdapter(mActivity, data);

        rcv_list.setAdapter(dashboardAdapter);

    }
}
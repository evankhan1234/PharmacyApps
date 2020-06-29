package com.nextgenit.pharmacyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.nextgenit.pharmacyapp.Adapter.DashboardAdapter;
import com.nextgenit.pharmacyapp.Network.IRetrofitApi;
import com.nextgenit.pharmacyapp.NetworkModel.PatientListResponses;
import com.nextgenit.pharmacyapp.R;
import com.nextgenit.pharmacyapp.Utils.Common;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DashboardActivity extends AppCompatActivity {
    private RecyclerView rcv_list;
    private DashboardAdapter dashboardAdapter = null;
    private Activity mActivity;
    private FloatingActionButton btn_new;
    ArrayList<String> data= new ArrayList<>();
    ProgressBar progress_bar;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IRetrofitApi mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService= Common.getApiXact();
        rcv_list=findViewById(R.id.rcv_list);
        btn_new=findViewById(R.id.btn_new);
        progress_bar=findViewById(R.id.progress_bar);
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
        progress_bar.setVisibility(View.VISIBLE);
        compositeDisposable.add(mService.getPatientList().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<PatientListResponses>() {
            @Override
            public void accept(PatientListResponses patientListResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(patientListResponses));
                dashboardAdapter = new DashboardAdapter(mActivity, patientListResponses.data_list);

                rcv_list.setAdapter(dashboardAdapter);
                progress_bar.setVisibility(View.GONE);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("study", "study" + throwable.getMessage());
                progress_bar.setVisibility(View.GONE);
            }
        }));

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}
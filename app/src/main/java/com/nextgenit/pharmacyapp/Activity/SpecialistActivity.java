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
import com.nextgenit.pharmacyapp.Adapter.SpecialistAdapter;
import com.nextgenit.pharmacyapp.Interface.ISpecialityViewListener;
import com.nextgenit.pharmacyapp.Network.IRetrofitApi;
import com.nextgenit.pharmacyapp.NetworkModel.PatientList;
import com.nextgenit.pharmacyapp.NetworkModel.PatientListResponses;
import com.nextgenit.pharmacyapp.NetworkModel.Specialist;
import com.nextgenit.pharmacyapp.NetworkModel.SpecialistResponses;
import com.nextgenit.pharmacyapp.R;
import com.nextgenit.pharmacyapp.Utils.Common;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SpecialistActivity extends AppCompatActivity {
    private RecyclerView rcv_list;
    private SpecialistAdapter specialistAdapter = null;
    private Activity mActivity;
    ProgressBar progress_bar;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IRetrofitApi mService;
    PatientList patientList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist);
        mService= Common.getApiXact();
        rcv_list=findViewById(R.id.rcv_list);
        progress_bar=findViewById(R.id.progress_bar);
        mActivity=this;
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_list.setLayoutManager(lm);
        patientList = getIntent().getExtras().getParcelable("patient");
    }
    private void loadData() {
        progress_bar.setVisibility(View.VISIBLE);
        compositeDisposable.add(mService.getSpecialList().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(specialistResponses -> {
            Log.e("study", "study" + new Gson().toJson(specialistResponses));
            specialistAdapter = new SpecialistAdapter(mActivity, specialistResponses.data_list,specialityViewListener);

            rcv_list.setAdapter(specialistAdapter);
            progress_bar.setVisibility(View.GONE);
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("study", "study" + throwable.getMessage());
                progress_bar.setVisibility(View.GONE);
            }
        }));

    }

    ISpecialityViewListener specialityViewListener= new ISpecialityViewListener() {
        @Override
        public void show(Specialist specialist) {
            Intent intent = new Intent(SpecialistActivity.this, DoctorListActivity.class);
            intent.putExtra("foo", specialist);
            intent.putExtra("patient", patientList);
            startActivity(intent);
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
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
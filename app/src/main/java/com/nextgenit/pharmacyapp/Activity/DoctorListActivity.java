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
import android.widget.TextView;

import com.google.gson.Gson;
import com.nextgenit.pharmacyapp.Adapter.DoctorListAdapter;
import com.nextgenit.pharmacyapp.Adapter.SpecialistAdapter;
import com.nextgenit.pharmacyapp.Interface.IDoctorViewListener;
import com.nextgenit.pharmacyapp.Interface.ISpecialityViewListener;
import com.nextgenit.pharmacyapp.Network.IRetrofitApi;
import com.nextgenit.pharmacyapp.NetworkModel.DoctorList;
import com.nextgenit.pharmacyapp.NetworkModel.DoctorListResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PatientList;
import com.nextgenit.pharmacyapp.NetworkModel.Specialist;
import com.nextgenit.pharmacyapp.NetworkModel.SpecialistResponses;
import com.nextgenit.pharmacyapp.R;
import com.nextgenit.pharmacyapp.Utils.Common;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DoctorListActivity extends AppCompatActivity {
    private RecyclerView rcv_list;
    private DoctorListAdapter doctorListAdapter = null;
    private Activity mActivity;
    ProgressBar progress_bar;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IRetrofitApi mService;
    Specialist specialist;
    TextView tv_name;
    TextView tv_count;
    PatientList patientList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        mService= Common.getApiXact();
        rcv_list=findViewById(R.id.rcv_list);
        tv_name=findViewById(R.id.tv_name);
        tv_count=findViewById(R.id.tv_count);
        progress_bar=findViewById(R.id.progress_bar);
        mActivity=this;
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_list.setLayoutManager(lm);
        specialist = getIntent().getExtras().getParcelable("foo");
        patientList = getIntent().getExtras().getParcelable("patient");
        tv_name.setText(specialist.specialization_name);
        tv_count.setText(String.valueOf(specialist.no_of_doctor));
        Log.e("users", "users" + new Gson().toJson(specialist));
        loadData(String.valueOf(specialist.specialization_id));
    }
    private void loadData(String data) {
        progress_bar.setVisibility(View.VISIBLE);
        compositeDisposable.add(mService.getDoctorList(data).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<DoctorListResponses>() {
            @Override
            public void accept(DoctorListResponses doctorListResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(doctorListResponses));
                doctorListAdapter = new DoctorListAdapter(mActivity, doctorListResponses.data_list,doctorViewListener);

                rcv_list.setAdapter(doctorListAdapter);
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

    IDoctorViewListener doctorViewListener= new IDoctorViewListener() {
        @Override
        public void show(DoctorList doctorList) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();

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
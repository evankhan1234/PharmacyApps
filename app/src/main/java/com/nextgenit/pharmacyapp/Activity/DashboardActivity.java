package com.nextgenit.pharmacyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.nextgenit.pharmacyapp.Adapter.DashboardAdapter;
import com.nextgenit.pharmacyapp.Interface.IClickListener;
import com.nextgenit.pharmacyapp.Network.IRetrofitApi;
import com.nextgenit.pharmacyapp.NetworkModel.AppointmentResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PatientList;
import com.nextgenit.pharmacyapp.NetworkModel.PatientListResponses;
import com.nextgenit.pharmacyapp.R;
import com.nextgenit.pharmacyapp.Utils.Common;
import com.nextgenit.pharmacyapp.Utils.SharedPreferenceUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    EditText edit_content;
    ImageView img_close;
    ImageView img_log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService= Common.getApiXact();
        rcv_list=findViewById(R.id.rcv_list);
        img_close=findViewById(R.id.img_close);
        img_log_out=findViewById(R.id.img_log_out);
        edit_content=findViewById(R.id.edit_content);
        btn_new=findViewById(R.id.btn_new);
        progress_bar=findViewById(R.id.progress_bar);
        mActivity=this;
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_list.setLayoutManager(lm);
        loadData();
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
        img_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
                SharedPreferenceUtil.saveShared(DashboardActivity.this, SharedPreferenceUtil.TYPE_USER_ID,  "");
                finish();
            }
        });
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this,PatientRegistrationActivity.class));
            }
        });
        edit_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!editable.toString().equals("")){
                    loadSearchData(editable.toString());
                }
                else{
                    loadData();
                }

            }
        });
    }
    private void loadSearchData(String data) {
        progress_bar.setVisibility(View.VISIBLE);
        compositeDisposable.add(mService.getSearchPatientList(data).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<PatientListResponses>() {
            @Override
            public void accept(PatientListResponses patientListResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(patientListResponses));
                dashboardAdapter = new DashboardAdapter(mActivity, patientListResponses.data_list,iClickListener);

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
    private void loadData() {
        progress_bar.setVisibility(View.VISIBLE);
        compositeDisposable.add(mService.getPatientList().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<PatientListResponses>() {
            @Override
            public void accept(PatientListResponses patientListResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(patientListResponses));
                dashboardAdapter = new DashboardAdapter(mActivity, patientListResponses.data_list,iClickListener);

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
    IClickListener iClickListener = new IClickListener() {
        @Override
        public void onView(PatientList patientList) {
            load(patientList);

        }
    };
    private void load(final PatientList patientList) {
        progress_bar.setVisibility(View.VISIBLE);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        String currentDate = formatter.format(date);
        int currentId= Integer.parseInt(SharedPreferenceUtil.getUserID(DashboardActivity.this));
        compositeDisposable.add(mService.postPatientAppointment(patientList.patient_no_pk,currentDate,currentId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<AppointmentResponses>() {
            @Override
            public void accept(AppointmentResponses appointmentResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(appointmentResponses));
                Intent intent = new Intent(DashboardActivity.this, DoctorViewActivity.class);
                intent.putExtra("patient", patientList);
                intent.putExtra("appointment_id", appointmentResponses.data_list.get(0).appointment_id);
                startActivity(intent);
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
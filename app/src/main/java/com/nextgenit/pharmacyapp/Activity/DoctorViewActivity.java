package com.nextgenit.pharmacyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nextgenit.pharmacyapp.Network.IRetrofitApi;
import com.nextgenit.pharmacyapp.NetworkModel.AppointmentDoctorLIstResponses;
import com.nextgenit.pharmacyapp.NetworkModel.AppointmentResponses;
import com.nextgenit.pharmacyapp.NetworkModel.DoctorList;
import com.nextgenit.pharmacyapp.NetworkModel.PatientList;
import com.nextgenit.pharmacyapp.NetworkModel.Specialist;
import com.nextgenit.pharmacyapp.R;
import com.nextgenit.pharmacyapp.Utils.Common;
import com.nextgenit.pharmacyapp.Utils.SharedPreferenceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DoctorViewActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IRetrofitApi mService;
    CircleImageView patient_icon;
    CircleImageView user_icon;
    TextView tv_patient_name;

    TextView tv_name;
    TextView tv_phone_number;
    TextView tv_gender;
    TextView tv_age;
    TextView tv_doctor_name;
    PatientList patientList;
    ProgressBar progress_bar;
    int appointmentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);
        mService= Common.getApiXact();
        patient_icon=findViewById(R.id.patient_icon);
        tv_doctor_name=findViewById(R.id.tv_doctor_name);
        user_icon=findViewById(R.id.user_icon);
        tv_patient_name=findViewById(R.id.tv_patient_name);
        tv_name=findViewById(R.id.tv_name);
        tv_phone_number=findViewById(R.id.tv_phone_number);
        tv_gender=findViewById(R.id.tv_gender);
        tv_age=findViewById(R.id.tv_age);
        progress_bar=findViewById(R.id.progress_bar);
        appointmentId = getIntent().getIntExtra("appointment_id",0);
       // specialist = getIntent().getExtras().getParcelable("specialist");
        patientList = getIntent().getExtras().getParcelable("patient");
       // doctorList = getIntent().getExtras().getParcelable("foo");

        tv_patient_name.setText(patientList.patient_name);
        tv_phone_number.setText(patientList.mobile1);
        tv_gender.setText(patientList.gender_txt);

        tv_age.setText("("+patientList.age+")");
        Glide.with(this).load("https://www.hardiagedcare.com.au/wp-content/uploads/2019/02/default-avatar-profile-icon-vector-18942381.jpg").placeholder(R.mipmap.ic_launcher).into(user_icon);

        Glide.with(this).load("https://www.hardiagedcare.com.au/wp-content/uploads/2019/02/default-avatar-profile-icon-vector-18942381.jpg").placeholder(R.mipmap.ic_launcher).into(patient_icon);

    }
    private void load() {
        progress_bar.setVisibility(View.VISIBLE);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        String currentDate = formatter.format(date);

        compositeDisposable.add(mService.postAppointmentDoctorList(appointmentId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<AppointmentDoctorLIstResponses>() {
            @Override
            public void accept(AppointmentDoctorLIstResponses appointmentResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(appointmentResponses));
                tv_doctor_name.setText(appointmentResponses.data_list.full_name);
                tv_name.setText(appointmentResponses.data_list.specialization);
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
    protected void onResume() {
        super.onResume();
        load();
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
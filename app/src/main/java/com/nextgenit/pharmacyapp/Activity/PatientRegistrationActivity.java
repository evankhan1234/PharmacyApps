package com.nextgenit.pharmacyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nextgenit.pharmacyapp.Adapter.DashboardAdapter;
import com.nextgenit.pharmacyapp.Network.IRetrofitApi;
import com.nextgenit.pharmacyapp.NetworkModel.APIResponses;
import com.nextgenit.pharmacyapp.NetworkModel.Occupation;
import com.nextgenit.pharmacyapp.NetworkModel.OccupationResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PatientListResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PatientRegistrationResponses;
import com.nextgenit.pharmacyapp.NetworkModel.RegistrationResponses;
import com.nextgenit.pharmacyapp.R;
import com.nextgenit.pharmacyapp.Utils.Common;
import com.nextgenit.pharmacyapp.Utils.SharedPreferenceUtil;
import com.nextgenit.pharmacyapp.Utils.Util;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PatientRegistrationActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IRetrofitApi mService;
    ProgressBar progress_bar;
    RelativeLayout rlt_root;
    EditText et_name;
    EditText et_age;
    EditText et_height;
    EditText et_weight;
    EditText et_phone;
    EditText et_description;
    RadioButton radio_men;
    RadioButton radio_women;
    RadioButton radio_married;
    RadioButton radio_unmarried;
    Button btn_ok;
    Button btn_cancel;
    Spinner spinner_occupation;
    ArrayAdapter<Occupation> occupationArrayAdapter;
    String occupationId;
    String maritalStatus="";
    String genderStatus="";
    ImageView img_close;
    ImageView img_log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_registration);
        mService= Common.getApiXact();
        spinner_occupation=findViewById(R.id.spinner_occupation);
        img_close=findViewById(R.id.img_close);
        img_log_out=findViewById(R.id.img_log_out);
        btn_cancel=findViewById(R.id.btn_cancel);
        progress_bar=findViewById(R.id.progress_bar);
        rlt_root=findViewById(R.id.rlt_root);
        et_name=findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);
        radio_men=findViewById(R.id.radio_men);
        radio_women=findViewById(R.id.radio_women);
        radio_married=findViewById(R.id.radio_married);
        radio_unmarried=findViewById(R.id.radio_unmarried);
        et_height=findViewById(R.id.et_height);
        et_weight=findViewById(R.id.et_weight);
        et_phone=findViewById(R.id.et_phone);
        et_description=findViewById(R.id.et_description);
        btn_ok=findViewById(R.id.btn_ok);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientRegistrationActivity.this,LoginActivity.class));
                SharedPreferenceUtil.saveShared(PatientRegistrationActivity.this, SharedPreferenceUtil.TYPE_USER_ID,  "");
                finish();
            }
        });
        loadData();

        et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                et_name.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule));
                et_age.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_height.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_weight.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_phone.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_description.setBackground(getResources().getDrawable(R.drawable.edit_text_description));

                et_name.setTextColor(getResources().getColor(R.color.colorPrimary));
                et_name.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                et_age.setTextColor(getResources().getColor(R.color.gray_for));
                et_age.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_height.setTextColor(getResources().getColor(R.color.gray_for));
                et_height.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_weight.setTextColor(getResources().getColor(R.color.gray_for));
                et_weight.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_phone.setTextColor(getResources().getColor(R.color.gray_for));
                et_phone.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_description.setTextColor(getResources().getColor(R.color.gray_for));
                et_description.setHintTextColor(getResources().getColor(R.color.gray_for));
            }
        });


        et_age.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                et_age.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule));
                et_name.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_height.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_weight.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_phone.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_description.setBackground(getResources().getDrawable(R.drawable.edit_text_description));

                et_age.setTextColor(getResources().getColor(R.color.colorPrimary));
                et_age.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                et_name.setTextColor(getResources().getColor(R.color.gray_for));
                et_name.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_height.setTextColor(getResources().getColor(R.color.gray_for));
                et_height.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_weight.setTextColor(getResources().getColor(R.color.gray_for));
                et_weight.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_phone.setTextColor(getResources().getColor(R.color.gray_for));
                et_phone.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_description.setTextColor(getResources().getColor(R.color.gray_for));
                et_description.setHintTextColor(getResources().getColor(R.color.gray_for));
            }
        });


        et_height.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                et_height.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule));
                et_name.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_age.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_weight.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_phone.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_description.setBackground(getResources().getDrawable(R.drawable.edit_text_description));

                et_height.setTextColor(getResources().getColor(R.color.colorPrimary));
                et_height.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                et_name.setTextColor(getResources().getColor(R.color.gray_for));
                et_name.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_age.setTextColor(getResources().getColor(R.color.gray_for));
                et_age.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_weight.setTextColor(getResources().getColor(R.color.gray_for));
                et_weight.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_phone.setTextColor(getResources().getColor(R.color.gray_for));
                et_phone.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_description.setTextColor(getResources().getColor(R.color.gray_for));
                et_description.setHintTextColor(getResources().getColor(R.color.gray_for));
            }
        });

        et_weight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                et_weight.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule));
                et_name.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_age.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_height.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_phone.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_description.setBackground(getResources().getDrawable(R.drawable.edit_text_description));

                et_weight.setTextColor(getResources().getColor(R.color.colorPrimary));
                et_weight.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                et_name.setTextColor(getResources().getColor(R.color.gray_for));
                et_name.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_age.setTextColor(getResources().getColor(R.color.gray_for));
                et_age.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_height.setTextColor(getResources().getColor(R.color.gray_for));
                et_height.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_phone.setTextColor(getResources().getColor(R.color.gray_for));
                et_phone.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_description.setTextColor(getResources().getColor(R.color.gray_for));
                et_description.setHintTextColor(getResources().getColor(R.color.gray_for));
            }
        });
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                et_phone.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule));
                et_name.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_age.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_height.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_weight.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_description.setBackground(getResources().getDrawable(R.drawable.edit_text_description));

                et_phone.setTextColor(getResources().getColor(R.color.colorPrimary));
                et_phone.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                et_name.setTextColor(getResources().getColor(R.color.gray_for));
                et_name.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_age.setTextColor(getResources().getColor(R.color.gray_for));
                et_age.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_height.setTextColor(getResources().getColor(R.color.gray_for));
                et_height.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_weight.setTextColor(getResources().getColor(R.color.gray_for));
                et_weight.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_description.setTextColor(getResources().getColor(R.color.gray_for));
                et_description.setHintTextColor(getResources().getColor(R.color.gray_for));
            }
        });
        et_description.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                et_description.setBackground(getResources().getDrawable(R.drawable.edit_text_description_color));
                et_name.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_age.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_height.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_weight.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_phone.setBackground(getResources().getDrawable(R.drawable.edit_text_description));

                et_description.setTextColor(getResources().getColor(R.color.colorPrimary));
                et_description.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                et_name.setTextColor(getResources().getColor(R.color.gray_for));
                et_name.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_age.setTextColor(getResources().getColor(R.color.gray_for));
                et_age.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_height.setTextColor(getResources().getColor(R.color.gray_for));
                et_height.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_weight.setTextColor(getResources().getColor(R.color.gray_for));
                et_weight.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_phone.setTextColor(getResources().getColor(R.color.gray_for));
                et_phone.setHintTextColor(getResources().getColor(R.color.gray_for));
            }
        });
        spinner_occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("sp_water", "" + data_list.get(position).lookupdata_no_pk);
                occupationId = String.valueOf(data_list.get(position).lookupdata_no_pk);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radio_men.isChecked()){
                    genderStatus="Male";
                }
                if (radio_women.isChecked()){
                    genderStatus="Female";
                }
                if (radio_married.isChecked()){
                    maritalStatus="Married";
                }
                if (radio_unmarried.isChecked()){
                    maritalStatus="Single";
                }
                String name=et_name.getText().toString();
                String age=et_age.getText().toString();
                String phone=et_phone.getText().toString();
                String weight=et_weight.getText().toString();
                String height=et_height.getText().toString();
                String desc=et_description.getText().toString();
                if (name.equals("") && weight.equals("") && age.equals("") && phone.equals("") && height.equals("") && desc.equals("") ){

                    Util.snackBar("All the fields are required",rlt_root);
                }
                else if (age.equals("") ){
                    Util.snackBar("Age is Empty",rlt_root);
                }
                else if (phone.equals("") ){
                    Util.snackBar("Phone is Empty",rlt_root);
                }
                else if (weight.equals("") ){
                    Util.snackBar("Weight is Empty",rlt_root);
                }
                else if (height.equals("") ){
                    Util.snackBar("Height is Empty",rlt_root);
                }
                else if (desc.equals("") ){
                    Util.snackBar("Description is Empty",rlt_root);
                }
                else{
                    Log.e("data","data"+name);
                    Log.e("data","data"+age);
                    Log.e("data","data"+genderStatus);
                    Log.e("data","data"+maritalStatus);
                    Log.e("data","data"+height);
                    Log.e("data","data"+weight);
                    Log.e("data","data"+occupationId);
                    Log.e("data","data"+phone);
                    Log.e("data","data"+desc);
                    progress_bar.setVisibility(View.VISIBLE);
                    compositeDisposable.add(mService.postPatientRegistration(name,age,genderStatus,maritalStatus,height,weight,occupationId,phone,desc,SharedPreferenceUtil.getUserID(PatientRegistrationActivity.this)).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<PatientRegistrationResponses>() {
                        @Override
                        public void accept(PatientRegistrationResponses registrationResponses) throws Exception {
                            Log.e("ff", "dgg" + new Gson().toJson(registrationResponses));

                            progress_bar.setVisibility(View.GONE);
                            if (registrationResponses.status.equals("success")) {
                                //    SharedPreferenceUtil.saveShared(RegistrationActivity.this, SharedPreferenceUtil.TYPE_USER_ID, registrationResponses.user.user_no_pk + "");
                                Toast.makeText(PatientRegistrationActivity.this, "Patient Registration Completed Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(PatientRegistrationActivity.this,DashboardActivity.class));
                                finish();

                            }
                            else if (registrationResponses.status.equals("failed")){
                                Toast.makeText(PatientRegistrationActivity.this, registrationResponses.message, Toast.LENGTH_SHORT).show();

                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            progress_bar.setVisibility(View.GONE);
                            Log.e("ff", "dgg" + throwable.getMessage());
                            Toast.makeText(PatientRegistrationActivity.this, "Unauthorized", Toast.LENGTH_SHORT).show();

                        }
                    }));
                }


            }
        });
    }
    ArrayList<Occupation> data_list;
    private void loadData() {
        progress_bar.setVisibility(View.VISIBLE);
        compositeDisposable.add(mService.getOccupation("OCCUPATION").observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<OccupationResponses>() {
            @Override
            public void accept(OccupationResponses occupationResponses) throws Exception {
                data_list=occupationResponses.data_list;
                occupationArrayAdapter = new ArrayAdapter<>(PatientRegistrationActivity.this, android.R.layout.simple_spinner_item, occupationResponses.data_list);
                occupationArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_occupation.setAdapter(occupationArrayAdapter);
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
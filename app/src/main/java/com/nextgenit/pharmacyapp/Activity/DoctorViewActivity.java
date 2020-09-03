package com.nextgenit.pharmacyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nextgenit.pharmacyapp.Adapter.DashboardAdapter;
import com.nextgenit.pharmacyapp.Adapter.DoctorListAdapter;
import com.nextgenit.pharmacyapp.Adapter.PrescriptionAdapter;
import com.nextgenit.pharmacyapp.Adapter.SpecialistAdapter;
import com.nextgenit.pharmacyapp.Network.IRetrofitApi;
import com.nextgenit.pharmacyapp.NetworkModel.AppointmentDoctorLIstResponses;
import com.nextgenit.pharmacyapp.NetworkModel.AppointmentResponses;
import com.nextgenit.pharmacyapp.NetworkModel.DoctorList;
import com.nextgenit.pharmacyapp.NetworkModel.DoctorListResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PatientList;
import com.nextgenit.pharmacyapp.NetworkModel.PresecriptionListResponses;
import com.nextgenit.pharmacyapp.NetworkModel.Specialist;
import com.nextgenit.pharmacyapp.R;
import com.nextgenit.pharmacyapp.Utils.Common;
import com.nextgenit.pharmacyapp.Utils.SharedPreferenceUtil;
import com.softbd.aamarpay.PayByAamarPay;
import com.softbd.aamarpay.interfaces.OnPaymentRequestListener;
import com.softbd.aamarpay.model.OptionalFields;
import com.softbd.aamarpay.model.PaymentResponse;
import com.softbd.aamarpay.model.RequiredFields;
import com.softbd.aamarpay.utils.Params;

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

    PrescriptionAdapter prescriptionAdapter;
    ImageView user_icon;
    ImageView img_close;
    ImageView img_log_out;
    TextView tv_patient_name;

    TextView tv_name;
    TextView tv_serial;
    TextView tv_gender;
    TextView tv_phone_number;
    TextView tv_doctor_name;
    PatientList patientList;
    ProgressBar progress_bar;
    int appointmentId;
    RecyclerView rcv_list;
    private Activity mActivity;
    private LinearLayout cart;
    private  TextView tv_degree1;
    private  TextView tv_degree2;
    private  TextView tv_degree3;
    private  TextView tv_degree4;
    Button btn_cancel;
    Button btn_ok;
    LinearLayout linear_doctor;
    RelativeLayout relative_one;
    String type="";
    String amount="";
    String doctorId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);
        mService= Common.getApiXact();
        mActivity=this;
        cart=findViewById(R.id.cart);
        btn_ok=findViewById(R.id.btn_ok);
        btn_cancel=findViewById(R.id.btn_cancel);
        relative_one=findViewById(R.id.relative_one);
        linear_doctor=findViewById(R.id.linear_doctor);
        tv_doctor_name=findViewById(R.id.tv_doctor_name);
        tv_serial=findViewById(R.id.tv_serial);
        rcv_list=findViewById(R.id.rcv_list);
        user_icon=findViewById(R.id.user_icon);
        tv_patient_name=findViewById(R.id.tv_patient_name);
        tv_name=findViewById(R.id.tv_name);
        tv_degree1=findViewById(R.id.tv_degree1);
        tv_degree2=findViewById(R.id.tv_degree2);
        tv_degree3=findViewById(R.id.tv_degree3);
        tv_degree4=findViewById(R.id.tv_degree4);

        tv_gender=findViewById(R.id.tv_gender);
        tv_phone_number=findViewById(R.id.tv_phone_number);

        progress_bar=findViewById(R.id.progress_bar);
        img_close=findViewById(R.id.img_close);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ammarpay();
            }
        });
        appointmentId = getIntent().getIntExtra("appointment_id",0);
        if (appointmentId==0){
            relative_one.setVisibility(View.GONE);
        }
       // specialist = getIntent().getExtras().getParcelable("specialist");
        patientList = getIntent().getExtras().getParcelable("patient");
       // doctorList = getIntent().getExtras().getParcelable("foo");
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_list.setLayoutManager(lm);
        tv_patient_name.setText(patientList.patient_name);
        tv_phone_number.setText(patientList.mobile1);
      //  tv_phone_number.setText(patientList.mobile1);
        tv_gender.setText("Age-"+patientList.age+","+patientList.gender_txt);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

//        tv_age.setText("("+patientList.age+")");
     //   Glide.with(this).load("https://nationaltoday.com/wp-content/uploads/2019/03/national-doctors-day.jpg").placeholder(R.mipmap.ic_launcher).into(user_icon);

        if (patientList.gender_txt.equals("Male")){
            user_icon.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.male));
        }
        else{
            user_icon.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.female));
        }
    }
    private void loadSettings() {
        progress_bar.setVisibility(View.VISIBLE);
        compositeDisposable.add(mService.getSettings().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(settingResponses -> {
            Log.e("loadSettings", "loadSettings" + new Gson().toJson(settingResponses));
            type=settingResponses.data_list.doctor_fee_type;
            if (type.equals("GLOBAL")){
                amount=settingResponses.data_list.global_doctor_fee;
            }
            else{
                loadDoctorDetails(doctorId);
            }

            progress_bar.setVisibility(View.GONE);
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("study", "study" + throwable.getMessage());
                progress_bar.setVisibility(View.GONE);
            }
        }));

    }
    private void loadDoctorDetails(String data) {
        progress_bar.setVisibility(View.VISIBLE);
        compositeDisposable.add(mService.getDoctorList(data).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<DoctorListResponses>() {
            @Override
            public void accept(DoctorListResponses doctorListResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(doctorListResponses));
                if (doctorListResponses.data_list.get(0).doctor_fee!=null){
                    amount=doctorListResponses.data_list.get(0).doctor_fee;
                }
                else{
                    amount="1";
                }

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
    private void ammarpay() {
        RequiredFields requiredFields = new RequiredFields(
                patientList.patient_name,
                "default@email.com",
                "Address 1",
                "City",
                "State",
                "1234",
                "Country",
                patientList.mobile1,
                "Description",
                amount,
                Params.CURRENCY_BDT,
                "A205220",
                "bluescareaid",
                "f16db1729e23f68380decf3fbb3c184a",
                "https://sandbox.aamarpay.com/success.php",
                "https://sandbox.aamarpay.com/failed.php",
                "https://sandbox.aamarpay.com/failed.php"
        );
        OptionalFields optionalFields = new OptionalFields(
                "cus address 2",
                "cus fax",
                "ship name",
                "ship address1",
                "ship address 2",
                "ship city",
                "ship state",
                "ship postcode",
                "ship country",
                "optional string",
                "optional string",
                "optional string",
                "optional string"
        );
        PayByAamarPay.getInstance(DoctorViewActivity.this, requiredFields, optionalFields).payNow(new OnPaymentRequestListener() {
            @Override
            public void onPaymentResponse(int paymentStatus, PaymentResponse paymentResponse) {
                Log.e("paymentStatus: " ,""+ paymentStatus);
                //Here you can view all the response in one place
                Log.e("Response: " ,""+ new Gson().toJson(paymentResponse));
            }
        });
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
                doctorId=appointmentResponses.data_list.doctor_no_fk;
                tv_doctor_name.setText(appointmentResponses.data_list.full_name);
                tv_name.setText(appointmentResponses.data_list.specialization);
                tv_serial.setText(appointmentResponses.data_list.slot_sl);
                if (appointmentResponses.data_list.degree1!=null){
                    tv_degree1.setText(appointmentResponses.data_list.degree1);
                    if (appointmentResponses.data_list.degree2!=null){
                        tv_degree2.setText(", "+appointmentResponses.data_list.degree2);
                    }
                    if (appointmentResponses.data_list.degree3!=null){
                        tv_degree3.setText(", "+appointmentResponses.data_list.degree3);
                    }
                    if (appointmentResponses.data_list.degree4!=null){
                        tv_degree4.setText(", "+appointmentResponses.data_list.degree4);
                    }
                }
                else{
                    tv_degree1.setText("NO degree included");
                }



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
    private void loadDataAll() {
        progress_bar.setVisibility(View.VISIBLE);

        compositeDisposable.add(mService.getPrescriptionList(patientList.patient_no_pk).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<PresecriptionListResponses>() {
            @Override
            public void accept(PresecriptionListResponses presecriptionListResponses) throws Exception {
                Log.e("getPrescriptionList", "getPrescriptionList" + new Gson().toJson(presecriptionListResponses));
                if (presecriptionListResponses.data_list.size()>0){
                    rcv_list.setVisibility(View.VISIBLE);
                    prescriptionAdapter = new PrescriptionAdapter(mActivity, presecriptionListResponses.data_list);
                    rcv_list.setAdapter(prescriptionAdapter);
                    cart.setVisibility(View.GONE);

                }
                else{
                    cart.setVisibility(View.VISIBLE);
                    rcv_list.setVisibility(View.GONE);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("getPrescriptionList", "getPrescriptionList" + throwable.getMessage());
                progress_bar.setVisibility(View.GONE);
            }
        }));

    }
    @Override
    protected void onResume() {
        super.onResume();
        load();
        loadDataAll();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadSettings();
            }
        }, 300);

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
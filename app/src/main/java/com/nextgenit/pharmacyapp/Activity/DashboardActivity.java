package com.nextgenit.pharmacyapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.nextgenit.pharmacyapp.Adapter.DashboardAdapter;
import com.nextgenit.pharmacyapp.Interface.IClickListener;
import com.nextgenit.pharmacyapp.Network.IRetrofitApi;
import com.nextgenit.pharmacyapp.NetworkModel.APIResponses;
import com.nextgenit.pharmacyapp.NetworkModel.AppointmentResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PatientList;
import com.nextgenit.pharmacyapp.NetworkModel.PatientListResponses;
import com.nextgenit.pharmacyapp.R;
import com.nextgenit.pharmacyapp.Utils.Common;
import com.nextgenit.pharmacyapp.Utils.SharedPreferenceUtil;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DashboardActivity extends AppCompatActivity  {

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
    SwipyRefreshLayout swipe_refresh;
    private DatabaseReference userRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mService= Common.getApiXact();
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        rcv_list=findViewById(R.id.rcv_list);
        swipe_refresh=findViewById(R.id.swipe_refresh);
        img_close=findViewById(R.id.img_close);
        img_log_out=findViewById(R.id.img_log_out);
        edit_content=findViewById(R.id.edit_content);
        btn_new=findViewById(R.id.btn_new);
        progress_bar=findViewById(R.id.progress_bar);
        mActivity=this;

        postVideoContent(FirebaseAuth.getInstance().getCurrentUser().getUid());
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
        swipe_refresh.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                loadData();
                swipe_refresh.setRefreshing(false);
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

    @Override
    protected void onStart() {
        super.onStart();
        checkForRecevingCall();
    }

    private void checkForRecevingCall() {
        userRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               Log.e("snapshot","value"+ snapshot.child("type").getValue().toString());

               String type=snapshot.child("type").getValue().toString();
               if (type.equals("Ringing")){
                   Toast.makeText(mActivity, snapshot.child("type").getValue().toString(), Toast.LENGTH_SHORT).show();
                   Intent intent= new Intent(DashboardActivity.this, CallingActivity.class);
                   startActivity(intent);
               }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void postVideoContent(String datas) {
        progress_bar.setVisibility(View.VISIBLE);
        Log.e("getUserID", "getUserID" + SharedPreferenceUtil.getUser(DashboardActivity.this));
        Log.e("getUserID", "getUserID" + datas);
        compositeDisposable.add(mService.postVideoContent(Integer.parseInt(SharedPreferenceUtil.getUser(DashboardActivity.this)),datas).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<APIResponses>() {
            @Override
            public void accept(APIResponses apiResponses) throws Exception {
                Log.e("postVideoContent", "postVideoContent" + new Gson().toJson(apiResponses));


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
    public void open(PatientList patientList){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you want to appoint Doctor Serial?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                load(patientList);
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void loadSearchData(String data) {
        progress_bar.setVisibility(View.VISIBLE);
        compositeDisposable.add(mService.getSearchPatientList(SharedPreferenceUtil.getUserID(DashboardActivity.this),data).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<PatientListResponses>() {
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
        compositeDisposable.add(mService.getPatientList(SharedPreferenceUtil.getUserID(DashboardActivity.this)).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<PatientListResponses>() {
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
        public void onView(PatientList patientList,String type) {
            if (type.equals("Appointment")){
                open(patientList);
            }
            else{
                Intent intent = new Intent(DashboardActivity.this, DoctorViewActivity.class);
                intent.putExtra("patient", patientList);
                intent.putExtra("appointment_id", patientList.appoint_no_pk);
                startActivity(intent);
            }


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
                intent.putExtra("appointment_id", appointmentResponses.data_list.appointment_id);
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
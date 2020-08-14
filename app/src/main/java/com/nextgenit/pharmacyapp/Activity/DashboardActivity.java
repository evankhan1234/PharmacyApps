package com.nextgenit.pharmacyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class DashboardActivity extends AppCompatActivity implements ServiceConnection {
    Contact contact= null;
    private MainService.MainBinder binder;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        bindService();
        mService= Common.getApiXact();
        rcv_list=findViewById(R.id.rcv_list);
        swipe_refresh=findViewById(R.id.swipe_refresh);
        img_close=findViewById(R.id.img_close);
        img_log_out=findViewById(R.id.img_log_out);
        edit_content=findViewById(R.id.edit_content);
        btn_new=findViewById(R.id.btn_new);
        progress_bar=findViewById(R.id.progress_bar);
        mActivity=this;
        requestPermission();
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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 786 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        }

    }
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 786);
        } else {

        }
    }

    String address="";
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.binder = (MainService.MainBinder) service;
        try {
            if (this.contact == null) {
                // export own contact
                this.contact = this.binder.getSettings().getOwnContact();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
             address = Contact.exportJSON(this.contact, false).toString();
            postVideoContent(address);
            Log.e("datas","datas"+address);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//          String data="{\"name\":\"Evan\",\"public_key\":\"3E22CD00AB870ECD6D1F2FF86BD49FFD837F3221D727DFA398423E013AB1DD14\",\"addresses\":[\"C0:EE:FB:F6:A2:C6\"]}";
//////
//////
//            Contact  contacts=null;
//        try {
//            JSONObject object = new JSONObject(data);
//            contacts = Contact.importJSON(object, false);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Intent intent = new Intent(MainActivity.this, CallActivity.class);
//        intent.setAction("ACTION_OUTGOING_CALL");
//        intent.putExtra("EXTRA_CONTACT", contacts);
//        startActivity(intent);
    }

    private void bindService() {
        Intent serviceIntent = new Intent(this, MainService.class);
        bindService(serviceIntent, this, Service.BIND_AUTO_CREATE);
    }
    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    @Override
    public void onBindingDied(ComponentName name) {

    }

    @Override
    public void onNullBinding(ComponentName name) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private void postVideoContent(String datas) {
        progress_bar.setVisibility(View.VISIBLE);
        Log.e("getUserID", "getUserID" + SharedPreferenceUtil.getUser(DashboardActivity.this));
        Log.e("getUserID", "getUserID" + datas);
        compositeDisposable.add(mService.postVideoContent(Integer.parseInt(SharedPreferenceUtil.getUser(DashboardActivity.this)),address).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<APIResponses>() {
            @Override
            public void accept(APIResponses apiResponses) throws Exception {
                Log.e("study", "study" + new Gson().toJson(apiResponses));

                String s="{\\\"name\\\":\\\"Unknown\\\",\\\"public_key\\\":\\\"F71FD23F2E7A41E4CBB20BA9857B26450D21B04144765F358D1E2269620B86AA\\\",\\\"addresses\\\":[\\\"C0:EE:FB:F6:A2:C6\\\"]}";
                s = s.replace("\\", "");
                Contact  contacts=null;
                try {
                    JSONObject object = new JSONObject(s);
                    contacts = Contact.importJSON(object, false);
                } catch (JSONException e) {
                    e.printStackTrace();
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
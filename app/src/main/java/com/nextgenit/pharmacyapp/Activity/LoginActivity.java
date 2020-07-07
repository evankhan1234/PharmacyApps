package com.nextgenit.pharmacyapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nextgenit.pharmacyapp.Network.IRetrofitApi;
import com.nextgenit.pharmacyapp.NetworkModel.LoginResponses;
import com.nextgenit.pharmacyapp.R;
import com.nextgenit.pharmacyapp.Utils.Common;
import com.nextgenit.pharmacyapp.Utils.SharedPreferenceUtil;
import com.nextgenit.pharmacyapp.Utils.Util;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IRetrofitApi mService;
    TextView tv_sign_up;
    Button btn_login;
    ProgressBar progress_bar;
    EditText et_email;
    EditText et_password;
    ImageView show_pass;
    RelativeLayout rlt_root;
    boolean test = true;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mService= Common.getApiXact();
        show_pass=findViewById(R.id.show_pass);
        img=findViewById(R.id.img);
        tv_sign_up=findViewById(R.id.tv_sign_up);
        progress_bar=findViewById(R.id.progress_bar);
        btn_login=findViewById(R.id.btn_login);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        rlt_root=findViewById(R.id.rlt_root);
        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });
        img.setImageDrawable(getResources().getDrawable(R.drawable.loginbg));

        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                et_email.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule));
                et_password.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_email.setTextColor(getResources().getColor(R.color.colorPrimary));
                et_email.setHintTextColor(getResources().getColor(R.color.colorPrimary));
                et_password.setTextColor(getResources().getColor(R.color.gray_for));
                et_password.setHintTextColor(getResources().getColor(R.color.gray_for));
            }
        });
        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                et_password.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule));
                et_email.setBackground(getResources().getDrawable(R.drawable.edit_text_capsule_gray));
                et_email.setTextColor(getResources().getColor(R.color.gray_for));
                et_email.setHintTextColor(getResources().getColor(R.color.gray_for));
                et_password.setTextColor(getResources().getColor(R.color.colorPrimary));
                et_password.setHintTextColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        show_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cursorPosition = et_password.getSelectionStart();

                if (test) {
                    Log.e("show", "show");

                    test = false;
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    show_pass.setImageDrawable(getResources().getDrawable(R.drawable.hidden_password));

                } else {
                    Log.e("hidden", "hidden");
                    show_pass.setImageDrawable(getResources().getDrawable(R.drawable.show_password));
                    test = true;
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                et_password.setSelection(cursorPosition);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=et_email.getText().toString();
                String password=et_password.getText().toString();
                if (email.equals("") && password.equals("") ){

                    Util.snackBar("All Data Needed",rlt_root);
                }
                else if (email.equals("") ){
                    Util.snackBar("Email is Empty",rlt_root);
                }
                else if (password.equals("") ){
                    Util.snackBar("Password is Empty",rlt_root);
                }
                else{
                    progress_bar.setVisibility(View.VISIBLE);
                    compositeDisposable.add(mService.postLogin(email,password).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Consumer<LoginResponses>() {
                        @Override
                        public void accept(LoginResponses loginEntity) throws Exception {
                            Log.e("ff", "dgg" + new Gson().toJson(loginEntity));

                            progress_bar.setVisibility(View.GONE);
                            if (loginEntity.status.equals("success")) {
                                SharedPreferenceUtil.saveShared(LoginActivity.this, SharedPreferenceUtil.TYPE_USER_ID, loginEntity.user.user_no_pk + "");
                                Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                                finish();

                            }
                            else if (loginEntity.status.equals("failed")){
                                Toast.makeText(LoginActivity.this, loginEntity.message, Toast.LENGTH_SHORT).show();

                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            progress_bar.setVisibility(View.GONE);
                            Log.e("ff", "dgg" + throwable.getMessage());
                            Toast.makeText(LoginActivity.this, "Unauthorized", Toast.LENGTH_SHORT).show();

                        }
                    }));

                }

            }
        });
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
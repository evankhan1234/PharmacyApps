package com.nextgenit.pharmacyapp.VideoCall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.nextgenit.pharmacyapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements ServiceConnection {
    Contact contact= null;
    private MainService.MainBinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService();


    }


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
            String datas = Contact.exportJSON(this.contact, false).toString();
            Log.e("datas","datas"+datas);
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
}
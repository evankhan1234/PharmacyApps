package com.nextgenit.pharmacyapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nextgenit.pharmacyapp.R;

import java.util.HashMap;

public class CallingActivity extends AppCompatActivity {
    private ImageView cancelCallBtn;
    private ImageView makeCallBtn;
    private MediaPlayer mediaPlayer;
    DatabaseReference reference;
    private DatabaseReference usersRef;
    String name="";
    TextView name_calling;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        name = getIntent().getStringExtra("call");
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        cancelCallBtn=findViewById(R.id.cancel_call);
        makeCallBtn=findViewById(R.id.make_call);
        name_calling=findViewById(R.id.name_calling);
        mediaPlayer=MediaPlayer.create(this,R.raw.ringing);
        mediaPlayer.start();
        name_calling.setText(name);
        makeCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                status(FirebaseAuth.getInstance().getCurrentUser().getUid());
                Intent intent= new Intent(CallingActivity.this, VideoChatActivity.class);
                startActivity(intent);
                finish();
            }
        });
        cancelCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                statusEnd(FirebaseAuth.getInstance().getCurrentUser().getUid());
                Intent intent= new Intent(CallingActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void status(String status){
        reference = FirebaseDatabase.getInstance().getReference("Users").child(status);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("type", "Accept");

        reference.updateChildren(hashMap);
    }
    private void statusEnd(String status){
        reference = FirebaseDatabase.getInstance().getReference("Users").child(status);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("type", "End");

        reference.updateChildren(hashMap);
    }
    private void checkForRecevingCall() {
        usersRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("snapshot","value"+ snapshot.child("type").getValue().toString());

                String type=snapshot.child("type").getValue().toString();
                if (type.equals("End")){
                    mediaPlayer.stop();
                    Intent intent= new Intent(CallingActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkForRecevingCall();
    }
}
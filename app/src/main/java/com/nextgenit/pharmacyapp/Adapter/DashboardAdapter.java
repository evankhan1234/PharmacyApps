package com.nextgenit.pharmacyapp.Adapter;

import android.app.Activity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nextgenit.pharmacyapp.Interface.IClickListener;
import com.nextgenit.pharmacyapp.NetworkModel.PatientList;
import com.nextgenit.pharmacyapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardListiewHolder> {


    private Activity mActivity = null;
    private ArrayList<PatientList> messageEntities;

    IClickListener iClickListener;
    public DashboardAdapter(Activity activity, ArrayList<PatientList> messageEntitie,IClickListener iClickListeners) {
        mActivity = activity;
        messageEntities = messageEntitie;
        iClickListener=iClickListeners;
    }


    @Override
    public DashboardAdapter.DashboardListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_dashboard_list, null);
        return new DashboardAdapter.DashboardListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DashboardAdapter.DashboardListiewHolder holder, final int position) {
        PatientList patientList=messageEntities.get(position);


        Log.e("Evan", "SDfs" + messageEntities.get(position));
         holder.tv_name.setText(messageEntities.get(position).patient_name);
         holder.tv_phone_number.setText(messageEntities.get(position).mobile1);
         holder.tv_age.setText("Age - "+messageEntities.get(position).age+","+messageEntities.get(position).gender_txt);
       //  Glide.with(mActivity).load("https://www.hardiagedcare.com.au/wp-content/uploads/2019/02/default-avatar-profile-icon-vector-18942381.jpg").placeholder(R.mipmap.ic_launcher).into(holder.user_icon);

        if (messageEntities.get(position).gender_txt.equals("Male")){
            holder.user_icon.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.male));
        }
        else{
            holder.user_icon.setImageDrawable(mActivity.getResources().getDrawable(R.drawable.female));
        }
        if (messageEntities.get(position).slot_sl != null){
            String serial = messageEntities.get(position).slot_sl != null ? messageEntities.get(position).slot_sl : "0";
            holder.tv_serial.setText(serial);
            holder.tv_serial.setVisibility(View.VISIBLE);
        }
        else{
            holder.tv_serial.setVisibility(View.INVISIBLE);
        }

        String remaining =messageEntities.get(position).remainingtime_txt != null ? messageEntities.get(position).remainingtime_txt : "00:00:00";


        if(messageEntities.get(position).appoint_no_pk!=null){
            holder.tv_remaining.setText(messageEntities.get(position).remainingtime_txt);
          //  holder.tv_remaining.setText("আর মাত্র "+ messageEntities.get(position).remainingtime_txt+"মিনিট");
        }
        else{
            holder.tv_remaining.setVisibility(View.GONE);
            holder.tv_remaining.setText("");
        }


         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 iClickListener.onView(messageEntities.get(position),"View");
             }
         });
        holder.img_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListener.onView(messageEntities.get(position),"Appointment");
            }
        });
        if (patientList.appoint_date!=null){
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(patientList.appoint_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = new Date(System.currentTimeMillis());
            String currentDate = formatter.format(date1);
            String appointDate = formatter.format(date);
            if (currentDate.equals(appointDate)){
                holder.linear.setBackgroundColor(mActivity.getResources().getColor(R.color.two));
                holder.tv_serial.setTextColor(mActivity.getResources().getColor(R.color.white));
                holder.tv_name.setTextColor(mActivity.getResources().getColor(R.color.white));
                holder.tv_phone_number.setTextColor(mActivity.getResources().getColor(R.color.white));
                holder.tv_age.setTextColor(mActivity.getResources().getColor(R.color.white));
                holder.relative_one.setVisibility(View.GONE);
            }
            else{
                holder.linear.setBackgroundColor(mActivity.getResources().getColor(R.color.one));
                holder.relative_one.setVisibility(View.VISIBLE);
                holder.tv_serial.setTextColor(mActivity.getResources().getColor(R.color.white));
                holder.tv_name.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                holder.tv_phone_number.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
                holder.tv_age.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));

            }
        }
        else{
            holder.linear.setBackgroundColor(mActivity.getResources().getColor(R.color.one));
            holder.relative_one.setVisibility(View.VISIBLE);
            holder.tv_serial.setTextColor(mActivity.getResources().getColor(R.color.white));
            holder.tv_name.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
            holder.tv_phone_number.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
            holder.tv_age.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
        }

    }

    @Override
    public int getItemCount() {
        Log.e("evan", "sd" + messageEntities.size());
        return messageEntities.size();
    }

    public class DashboardListiewHolder extends RecyclerView.ViewHolder {


        private CircleImageView user_icon;
        private TextView tv_name;
        private TextView tv_age;
        private TextView tv_phone_number;
        private TextView tv_remaining;
        private TextView tv_serial;
        private ImageView img_appointment;
        private LinearLayout linear;
        private RelativeLayout relative_one;



        public DashboardListiewHolder(View itemView) {
            super(itemView);

            user_icon = itemView.findViewById(R.id.user_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_age = itemView.findViewById(R.id.tv_age);
            tv_phone_number = itemView.findViewById(R.id.tv_phone_number);
            tv_remaining = itemView.findViewById(R.id.tv_remaining);
            tv_serial = itemView.findViewById(R.id.tv_serial);
            img_appointment = itemView.findViewById(R.id.img_appointment);
            linear = itemView.findViewById(R.id.linear);
            relative_one = itemView.findViewById(R.id.relative_one);



        }
    }
}
package com.nextgenit.pharmacyapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nextgenit.pharmacyapp.Activity.PrescriptionViewActivity;
import com.nextgenit.pharmacyapp.Interface.ISpecialityViewListener;
import com.nextgenit.pharmacyapp.NetworkModel.Prescription;
import com.nextgenit.pharmacyapp.NetworkModel.Specialist;
import com.nextgenit.pharmacyapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.SpeciaListiewHolder> {


    private Activity mActivity = null;
    private ArrayList<Prescription> messageEntities;

    public PrescriptionAdapter(Activity activity, ArrayList<Prescription> messageEntitie) {
        mActivity = activity;
        messageEntities = messageEntitie;

    }


    @Override
    public PrescriptionAdapter.SpeciaListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_prescription_list, null);
        return new PrescriptionAdapter.SpeciaListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PrescriptionAdapter.SpeciaListiewHolder holder, final int position) {


        Log.e("Evan", "SDfs" + messageEntities.get(position));
        holder.tv_name.setText(messageEntities.get(position).doctor_name);
        holder.tv_specialist.setText(messageEntities.get(position).doctor_specialization);
        holder.tv_prescription.setText("Prescription Id :"+messageEntities.get(position).prescription_code);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aaa");
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(messageEntities.get(position).prescription_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String date=  formatter.format(date1);
        holder.tv_date.setText(date);

        if (messageEntities.get(position).degree1!=null){
            holder.linear.setVisibility(View.VISIBLE);
            holder.tv_degree1.setText(messageEntities.get(position).degree1);
            if (messageEntities.get(position).degree2!=null){
                holder.tv_degree2.setText(", "+messageEntities.get(position).degree2);
            }
            if (messageEntities.get(position).degree3!=null){
                holder.tv_degree3.setText(", "+messageEntities.get(position).degree3);
            }
            if (messageEntities.get(position).degree4!=null){
                holder.tv_degree4.setText(", "+messageEntities.get(position).degree4);
            }
        }
        else{
            holder.linear.setVisibility(View.GONE);
        }
        holder.tv_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mActivity, PrescriptionViewActivity.class);
                i.putExtra("id",messageEntities.get(position).prescription_no_pk);
                mActivity.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.e("evan", "sd" + messageEntities.size());
        return messageEntities.size();
    }

    public class SpeciaListiewHolder extends RecyclerView.ViewHolder {



        private LinearLayout linear;
        private TextView tv_date;
        private TextView tv_name;
        private TextView tv_specialist;
        private TextView tv_prescription;
        private TextView tv_degree1;
        private TextView tv_degree2;
        private TextView tv_degree3;
        private TextView tv_degree4;
        private TextView tv_view;



        public SpeciaListiewHolder(View itemView) {
            super(itemView);

            //  user_icon = itemView.findViewById(R.id.user_icon);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_specialist = itemView.findViewById(R.id.tv_specialist);
            tv_prescription = itemView.findViewById(R.id.tv_prescription);
            tv_degree1 = itemView.findViewById(R.id.tv_degree1);
            tv_degree2 = itemView.findViewById(R.id.tv_degree2);
            tv_degree3 = itemView.findViewById(R.id.tv_degree3);
            tv_degree4 = itemView.findViewById(R.id.tv_degree4);
            linear = itemView.findViewById(R.id.linear);
            tv_view = itemView.findViewById(R.id.tv_view);



        }
    }
}
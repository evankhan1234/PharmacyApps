package com.nextgenit.pharmacyapp.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nextgenit.pharmacyapp.Interface.IDoctorViewListener;
import com.nextgenit.pharmacyapp.Interface.ISpecialityViewListener;
import com.nextgenit.pharmacyapp.NetworkModel.DoctorList;
import com.nextgenit.pharmacyapp.NetworkModel.Specialist;
import com.nextgenit.pharmacyapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.SpeciaListiewHolder> {


    private Activity mActivity = null;
    private ArrayList<DoctorList> messageEntities;
    IDoctorViewListener doctorViewListener;
    public DoctorListAdapter(Activity activity, ArrayList<DoctorList> messageEntitie, IDoctorViewListener doctorViewListeners) {
        mActivity = activity;
        messageEntities = messageEntitie;
        doctorViewListener=doctorViewListeners;
    }


    @Override
    public DoctorListAdapter.SpeciaListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_doctor_list, null);
        return new DoctorListAdapter.SpeciaListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DoctorListAdapter.SpeciaListiewHolder holder, final int position) {


//        Log.e("Evan", "SDfs" + messageEntities.get(position));
//        holder.tv_name.setText(messageEntities.get(position).full_name);
//        holder.tv_specialized.setText(messageEntities.get(position).specialization);

        Glide.with(mActivity).load("https://www.hardiagedcare.com.au/wp-content/uploads/2019/02/default-avatar-profile-icon-vector-18942381.jpg").placeholder(R.mipmap.ic_launcher).into(holder.user_icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorViewListener.show(messageEntities.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e("evan", "sd" + messageEntities.size());
        return messageEntities.size();
    }

    public class SpeciaListiewHolder extends RecyclerView.ViewHolder {


        private CircleImageView user_icon;
        private ImageView img_next;
        private TextView tv_name;
        private TextView tv_specialized;



        public SpeciaListiewHolder(View itemView) {
            super(itemView);

            user_icon = itemView.findViewById(R.id.user_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_specialized = itemView.findViewById(R.id.tv_specialized);
            img_next = itemView.findViewById(R.id.img_next);



        }
    }
}
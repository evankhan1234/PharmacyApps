package com.nextgenit.pharmacyapp.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nextgenit.pharmacyapp.Interface.ISpecialityViewListener;
import com.nextgenit.pharmacyapp.NetworkModel.Specialist;
import com.nextgenit.pharmacyapp.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SpecialistAdapter extends RecyclerView.Adapter<SpecialistAdapter.SpeciaListiewHolder> {


    private Activity mActivity = null;
    private ArrayList<Specialist> messageEntities;
    ISpecialityViewListener specialityViewListener;
    public SpecialistAdapter(Activity activity, ArrayList<Specialist> messageEntitie, ISpecialityViewListener specialityViewListeners) {
        mActivity = activity;
        messageEntities = messageEntitie;
        specialityViewListener=specialityViewListeners;
    }


    @Override
    public SpecialistAdapter.SpeciaListiewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_special_list, null);
        return new SpecialistAdapter.SpeciaListiewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SpecialistAdapter.SpeciaListiewHolder holder, final int position) {


        Log.e("Evan", "SDfs" + messageEntities.get(position));
        holder.tv_name.setText(messageEntities.get(position).specialization_name);
        holder.tv_count.setText("("+String.valueOf(messageEntities.get(position).no_of_doctor)+")");


        holder.img_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                specialityViewListener.show(messageEntities.get(position));
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
        private TextView tv_count;



        public SpeciaListiewHolder(View itemView) {
            super(itemView);

          //  user_icon = itemView.findViewById(R.id.user_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_count = itemView.findViewById(R.id.tv_count);
            img_next = itemView.findViewById(R.id.img_next);



        }
    }
}
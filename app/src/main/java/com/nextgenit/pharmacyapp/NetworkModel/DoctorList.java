package com.nextgenit.pharmacyapp.NetworkModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DoctorList implements Parcelable {
    @SerializedName("doctor_fee")
    public String  doctor_fee;

    protected DoctorList(Parcel in) {
        doctor_fee = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(doctor_fee);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DoctorList> CREATOR = new Creator<DoctorList>() {
        @Override
        public DoctorList createFromParcel(Parcel in) {
            return new DoctorList(in);
        }

        @Override
        public DoctorList[] newArray(int size) {
            return new DoctorList[size];
        }
    };
}


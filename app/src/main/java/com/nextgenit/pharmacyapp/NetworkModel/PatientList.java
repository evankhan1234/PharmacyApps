package com.nextgenit.pharmacyapp.NetworkModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PatientList implements Parcelable {
    @SerializedName("patient_no_pk")
    public int  patient_no_pk;
    @SerializedName("patient_code")
    public String  patient_code;
    @SerializedName("patient_name")
    public String  patient_name;
    @SerializedName("au_entry_at")
    public String  au_entry_at;
    @SerializedName("au_update_at")
    public String  au_update_at;
    @SerializedName("email")
    public String  email;
    @SerializedName("mobile1")
    public String  mobile1;
    @SerializedName("mobile2")
    public String  mobile2;
    @SerializedName("phone_tnt")
    public String  phone_tnt;
    @SerializedName("age")
    public String  age;
    @SerializedName("dob")
    public String  dob;
    @SerializedName("gender")
    public String  gender;
    @SerializedName("gender_txt")
    public String  gender_txt;
    @SerializedName("marital_status")
    public String  marital_status;
    @SerializedName("marital_status_txt")
    public String  marital_status_txt;
    @SerializedName("nationality")
    public String  nationality;
    @SerializedName("national_id")
    public String  national_id;

    protected PatientList(Parcel in) {
        patient_no_pk = in.readInt();
        patient_code = in.readString();
        patient_name = in.readString();
        au_entry_at = in.readString();
        au_update_at = in.readString();
        email = in.readString();
        mobile1 = in.readString();
        mobile2 = in.readString();
        phone_tnt = in.readString();
        age = in.readString();
        dob = in.readString();
        gender = in.readString();
        gender_txt = in.readString();
        marital_status = in.readString();
        marital_status_txt = in.readString();
        nationality = in.readString();
        national_id = in.readString();
    }

    public static final Creator<PatientList> CREATOR = new Creator<PatientList>() {
        @Override
        public PatientList createFromParcel(Parcel in) {
            return new PatientList(in);
        }

        @Override
        public PatientList[] newArray(int size) {
            return new PatientList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(patient_no_pk);
        dest.writeString(patient_code);
        dest.writeString(patient_name);
        dest.writeString(au_entry_at);
        dest.writeString(au_update_at);
        dest.writeString(email);
        dest.writeString(mobile1);
        dest.writeString(mobile2);
        dest.writeString(phone_tnt);
        dest.writeString(age);
        dest.writeString(dob);
        dest.writeString(gender);
        dest.writeString(gender_txt);
        dest.writeString(marital_status);
        dest.writeString(marital_status_txt);
        dest.writeString(nationality);
        dest.writeString(national_id);
    }
}

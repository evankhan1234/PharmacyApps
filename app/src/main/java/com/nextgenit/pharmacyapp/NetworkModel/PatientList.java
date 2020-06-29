package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class PatientList {
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
}

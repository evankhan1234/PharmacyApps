package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class PatientRegistration {
    @SerializedName("patient_code")
    public String  patient_code;
    @SerializedName("patient_name")
    public String  patient_name;
    @SerializedName("age")
    public String  age;
    @SerializedName("mobile1")
    public String  mobile1;
    @SerializedName("gender_txt")
    public String  gender_txt;
    @SerializedName("marital_status_txt")
    public String  marital_status_txt;
    @SerializedName("au_entry_by")
    public String  au_entry_by;
    @SerializedName("reg_date")
    public String  reg_date;
    @SerializedName("initial_height")
    public String  initial_height;
    @SerializedName("initial_weight")
    public String  initial_weight;
    @SerializedName("initial_cc")
    public String  initial_cc;
    @SerializedName("status")
    public String  status;
    @SerializedName("au_update_at")
    public String  au_update_at;
    @SerializedName("au_entry_at")
    public String  au_entry_at;
    @SerializedName("patient_no_pk")
    public String  patient_no_pk;
}

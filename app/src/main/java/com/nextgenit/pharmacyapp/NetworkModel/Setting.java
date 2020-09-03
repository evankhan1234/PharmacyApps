package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class Setting {
    @SerializedName("settings_no_pk")
    public int  settings_no_pk;
    @SerializedName("doctor_fee_type")
    public String  doctor_fee_type;
    @SerializedName("global_doctor_fee")
    public String  global_doctor_fee;

}

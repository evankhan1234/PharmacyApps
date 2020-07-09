package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class Prescription {
    @SerializedName("prescription_code")
    public String  prescription_code;
    @SerializedName("prescription_date")
    public String  prescription_date;
    @SerializedName("doctor_specialization")
    public String  doctor_specialization;
    @SerializedName("doctor_code")
    public String  doctor_code;
    @SerializedName("doctor_name")
    public String  doctor_name;
}

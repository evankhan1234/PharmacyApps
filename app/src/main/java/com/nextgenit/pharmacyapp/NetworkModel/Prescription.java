package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class Prescription {
    @SerializedName("prescription_no_pk")
    public int  prescription_no_pk;
    @SerializedName("prescription_code")
    public String  prescription_code;
    @SerializedName("appointment_no_fk")
    public int  appointment_no_fk;
    @SerializedName("prescription_date")
    public String  prescription_date;
    @SerializedName("doctor_specialization")
    public String  doctor_specialization;
    @SerializedName("doctor_code")
    public String  doctor_code;
    @SerializedName("doctor_name")
    public String  doctor_name;
    @SerializedName("degree1")
    public String  degree1;
    @SerializedName("degree2")
    public String  degree2;

    @SerializedName("degree3")
    public String  degree3;
    @SerializedName("degree4")
    public String  degree4;
}

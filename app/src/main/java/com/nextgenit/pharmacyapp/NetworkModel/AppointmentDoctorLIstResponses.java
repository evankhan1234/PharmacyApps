package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AppointmentDoctorLIstResponses extends APIResponses {
    @SerializedName("data_list")
    public AppointmentDoctorList data_list;
}

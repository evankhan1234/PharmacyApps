package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AppointmentResponses extends APIResponses {
    @SerializedName("data_list")
    public ArrayList<Appointment> data_list;
}

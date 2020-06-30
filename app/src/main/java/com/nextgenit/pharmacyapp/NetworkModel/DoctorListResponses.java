package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DoctorListResponses extends APIResponses {
    @SerializedName("data_list")
    public ArrayList<DoctorList> data_list;
}

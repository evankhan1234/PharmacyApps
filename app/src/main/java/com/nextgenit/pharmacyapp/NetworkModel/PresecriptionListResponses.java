package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PresecriptionListResponses extends APIResponses {
    @SerializedName("data_list")
    public ArrayList<Prescription> data_list;
}

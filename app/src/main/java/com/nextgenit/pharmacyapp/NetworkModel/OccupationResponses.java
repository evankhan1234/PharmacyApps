package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OccupationResponses extends APIResponses {
    @SerializedName("data_list")
    public ArrayList<Occupation> data_list;
}

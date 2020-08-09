package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PresecriptionViewResponses extends APIResponses {
    @SerializedName("data_list")
    public PrescriptionView data_list;
}

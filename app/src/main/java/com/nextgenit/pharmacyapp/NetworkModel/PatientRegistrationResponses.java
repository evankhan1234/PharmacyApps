package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class PatientRegistrationResponses extends APIResponses {
    @SerializedName("data_list")
    public PatientRegistration data_list;
}

package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponses extends APIResponses {
    @SerializedName("user")
    public RegistrationUser user;

}

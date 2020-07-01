package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class Occupation {
    @SerializedName("lookupdata_no_pk")
    public int  lookupdata_no_pk;
    @SerializedName("lookupdata_name")
    public String  lookupdata_name;

    @Override
    public String toString() {
        return lookupdata_name ;
    }
}

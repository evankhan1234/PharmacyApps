package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class RegistrationUser {
    @SerializedName("user_no_pk")
    public int  user_no_pk;
    @SerializedName("au_update_at")
    public String  au_update_at;
    @SerializedName("user_fullname")
    public String  user_fullname;
    @SerializedName("au_entry_at")
    public String  au_entry_at;
    @SerializedName("email")
    public String  email;
}

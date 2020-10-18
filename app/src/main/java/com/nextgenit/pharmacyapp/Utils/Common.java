package com.nextgenit.pharmacyapp.Utils;


import com.nextgenit.pharmacyapp.Network.IRetrofitApi;
import com.nextgenit.pharmacyapp.Network.RetrofitClient;

public abstract class Common {

    public static final String BASE_URL_XACT = "http://199.192.28.11/mapps/public/api/";

    public static IRetrofitApi getApiXact() {
        return RetrofitClient.getClient(BASE_URL_XACT).create(IRetrofitApi.class);
    }
}

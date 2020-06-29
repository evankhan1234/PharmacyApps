package com.nextgenit.pharmacyapp.Network;

import com.nextgenit.pharmacyapp.NetworkModel.LoginResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PatientListResponses;
import com.nextgenit.pharmacyapp.NetworkModel.RegistrationResponses;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRetrofitApi {
    @GET("auth/patient-list")
    io.reactivex.Observable<PatientListResponses> getPatientList();
//
    @FormUrlEncoded
    @POST("auth/login")
    io.reactivex.Observable<LoginResponses> postLogin(@Field("email") String email,
                                                         @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/signup")
    io.reactivex.Observable<RegistrationResponses> postRegistration(@Field("name") String name,
                                                                    @Field("email") String email,
                                                                    @Field("phone_mobile") String phone_mobile,
                                                                    @Field("password") String password,
                                                                    @Field("re-password") String re_password);
}

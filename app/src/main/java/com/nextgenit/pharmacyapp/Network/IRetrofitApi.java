package com.nextgenit.pharmacyapp.Network;

import com.nextgenit.pharmacyapp.NetworkModel.APIResponses;
import com.nextgenit.pharmacyapp.NetworkModel.DoctorListResponses;
import com.nextgenit.pharmacyapp.NetworkModel.LoginResponses;
import com.nextgenit.pharmacyapp.NetworkModel.OccupationResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PatientListResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PatientRegistrationResponses;
import com.nextgenit.pharmacyapp.NetworkModel.RegistrationResponses;
import com.nextgenit.pharmacyapp.NetworkModel.SpecialistResponses;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRetrofitApi {
    @POST("auth/patient-list")
    io.reactivex.Observable<PatientListResponses> getPatientList();
    @FormUrlEncoded
    @POST("auth/patient-list")
    io.reactivex.Observable<PatientListResponses> getSearchPatientList(@Field("search_string") String search_string);
    @GET("auth/spl-wise-doc-reg")
    io.reactivex.Observable<SpecialistResponses> getSpecialList();
//
    @FormUrlEncoded
    @POST("auth/login")
    io.reactivex.Observable<LoginResponses> postLogin(@Field("email") String email,
                                                         @Field("password") String password);
    @FormUrlEncoded
    @POST("auth/lookup-list-by-code")
    io.reactivex.Observable<OccupationResponses> getOccupation(@Field("lookup_code") String lookup_code
                                                               );
    @FormUrlEncoded
    @POST("auth/doctor-list")
    io.reactivex.Observable<DoctorListResponses> getDoctorList(@Field("specialization_id") String specialization_id
    );
    @FormUrlEncoded
    @POST("auth/signup")
    io.reactivex.Observable<RegistrationResponses> postRegistration(@Field("name") String name,
                                                                    @Field("email") String email,
                                                                    @Field("phone_mobile") String phone_mobile,
                                                                    @Field("password") String password,
                                                                    @Field("re-password") String re_password);

    @FormUrlEncoded
    @POST("auth/patient-reg")
    io.reactivex.Observable<PatientRegistrationResponses> postPatientRegistration(@Field("pat_name") String pat_name,
                                                                                  @Field("pat_age") String pat_age,
                                                                                  @Field("pat_gender") String pat_gender,
                                                                                  @Field("pat_marital_status") String pat_marital_status,
                                                                                  @Field("pat_height") String pat_height,
                                                                                  @Field("pat_weight") String pat_weight,
                                                                                  @Field("ocupation_id") String ocupation_id,
                                                                                  @Field("pat_mobile") String pat_mobile,
                                                                                  @Field("disease_desc") String disease_desc);
}

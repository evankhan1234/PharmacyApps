package com.nextgenit.pharmacyapp.Network;

import com.nextgenit.pharmacyapp.NetworkModel.APIResponses;
import com.nextgenit.pharmacyapp.NetworkModel.AppointmentDoctorLIstResponses;
import com.nextgenit.pharmacyapp.NetworkModel.AppointmentResponses;
import com.nextgenit.pharmacyapp.NetworkModel.DoctorListResponses;
import com.nextgenit.pharmacyapp.NetworkModel.LoginResponses;
import com.nextgenit.pharmacyapp.NetworkModel.OccupationResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PatientListResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PatientRegistrationResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PrescriptionListHeaderResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PresecriptionListResponses;
import com.nextgenit.pharmacyapp.NetworkModel.PresecriptionViewResponses;
import com.nextgenit.pharmacyapp.NetworkModel.RegistrationResponses;
import com.nextgenit.pharmacyapp.NetworkModel.SpecialistResponses;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRetrofitApi {
    @FormUrlEncoded
    @POST("auth/patient-list")
    io.reactivex.Observable<PatientListResponses> getPatientList(@Field("pharmacy_id") String pharmacy_id);

    @FormUrlEncoded
    @POST("auth/patient-list")
    io.reactivex.Observable<PatientListResponses> getSearchPatientList(@Field("pharmacy_id") String pharmacy_id, @Field("search_string") String search_string);

    @FormUrlEncoded
    @POST("auth/appointed-doc-info")
    io.reactivex.Observable<AppointmentDoctorLIstResponses> postAppointmentDoctorList(@Field("appointment_id") int appointment_id);

    @FormUrlEncoded
    @POST("auth/get-pat-presc-list")
    io.reactivex.Observable<PresecriptionListResponses> getPrescriptionList(@Field("patient_id") int patient_id);

    @FormUrlEncoded
    @POST("auth/get-prescription-mst")
    io.reactivex.Observable<PresecriptionViewResponses> getPrescriptionViewHeader(@Field("prescription_id") int prescription_id);

    @FormUrlEncoded
    @POST("auth/get-prescription-dtls")
    io.reactivex.Observable<PrescriptionListHeaderResponses> getPrescriptionViewHeaderDetails(@Field("prescription_id") int prescription_id);

    @FormUrlEncoded
    @POST("auth/patient-appointment")
    io.reactivex.Observable<AppointmentResponses> postPatientAppointment(@Field("patient_id") int patient_id,
                                                                         @Field("appointment_date") String appointment_date,
                                                                         @Field("created_by") int created_by);

    @GET("auth/spl-wise-doc-reg")
    io.reactivex.Observable<SpecialistResponses> getSpecialList();

    //
    @FormUrlEncoded
    @POST("auth/login")
    io.reactivex.Observable<LoginResponses> postLogin(@Field("email") String email,
                                                      @Field("password") String password,
                                                      @Field("access_point") String access_point);

    @FormUrlEncoded
    @POST("auth/store-video-content")
    io.reactivex.Observable<APIResponses> postVideoContent(@Field("pharmacy_id") int pharmacy_id,
                                                      @Field("content") String content);

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
                                                                    @Field("password_confirmation") String re_password);

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
                                                                                  @Field("disease_desc") String disease_desc,
                                                                                  @Field("created_by") String created_by);
}

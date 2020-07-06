package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class AppointmentDoctorList {
    @SerializedName("appoint_no_pk")
    public int  appoint_no_pk;
    @SerializedName("appoint_code")
    public String  appoint_code;
    @SerializedName("appoint_date")
    public String  appoint_date;
    @SerializedName("slot_sl")
    public String  slot_sl;
    @SerializedName("priority")
    public String  priority;
    @SerializedName("priority_no_fk")
    public String  priority_no_fk;
    @SerializedName("doctor_no_fk")
    public String  doctor_no_fk;
    @SerializedName("full_name")
    public String  full_name;
    @SerializedName("person_code")
    public String  person_code;
    @SerializedName("doctor_signature")
    public String  doctor_signature;
    @SerializedName("doc_gender")
    public String  doc_gender;
    @SerializedName("degree1")
    public String  degree1;
    @SerializedName("degree2")
    public String  degree2;
    @SerializedName("degree3")
    public String  degree3;
    @SerializedName("degree4")
    public String  degree4;
    @SerializedName("doc_gender_txt")
    public String  doc_gender_txt;
    @SerializedName("specialization_lookup_no_fk")
    public String  specialization_lookup_no_fk;
    @SerializedName("specialization")
    public String  specialization;

    @SerializedName("patient_no_pk")
    public String  patient_no_pk;
    @SerializedName("patient_name")
    public String  patient_name;
    @SerializedName("patient_code")
    public String  patient_code;
    @SerializedName("gender_txt")
    public String  gender_txt;

    @SerializedName("patient_dob")
    public String  patient_dob;
    @SerializedName("blood_group")
    public String  blood_group;
    @SerializedName("age")
    public String  age;
    @SerializedName("mobile1")
    public String  mobile1;

    @SerializedName("initial_weight")
    public String  initial_weight;
    @SerializedName("initial_height")
    public String  initial_height;
    @SerializedName("initial_cc")
    public String  initial_cc;
    @SerializedName("chif_complain")
    public String  chif_complain;
    @SerializedName("chif_complain_no_fk")
    public String  chif_complain_no_fk;
    @SerializedName("appoint_source")
    public String  appoint_source;
    @SerializedName("cancel_ind")
    public String  cancel_ind;
    @SerializedName("start_time")
    public String  start_time;

    @SerializedName("end_time")
    public String  end_time;
    @SerializedName("consult_by")
    public String  consult_by;
    @SerializedName("consult_ind")
    public String  consult_ind;
    @SerializedName("consult_time")
    public String  consult_time;

    @SerializedName("consult_voucher_no_pk")
    public String  consult_voucher_no_pk;
    @SerializedName("blank_pres_print_by")
    public String  blank_pres_print_by;
    @SerializedName("blank_pres_print_ind")
    public String  blank_pres_print_ind;
    @SerializedName("pat_arrival_by")
    public String  pat_arrival_by;

    @SerializedName("pat_arrival_ind")
    public String  pat_arrival_ind;
    @SerializedName("pat_arrival_time")
    public String  pat_arrival_time;
    @SerializedName("patient_photo")
    public String  patient_photo;
    @SerializedName("doctor_photo")
    public String  doctor_photo;
    @SerializedName("cancel_reason")
    public String  cancel_reason;
}

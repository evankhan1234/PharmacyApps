package com.nextgenit.pharmacyapp.NetworkModel;

import com.google.gson.annotations.SerializedName;

public class DoctorList {
    @SerializedName("person_no_pk")
    public int  person_no_pk;
    @SerializedName("person_code")
    public String  person_code;
    @SerializedName("full_name")
    public String  full_name;
    @SerializedName("dob")
    public String  dob;
    @SerializedName("doctor_signature")
    public String  doctor_signature;
    @SerializedName("phone_mobile")
    public String  phone_mobile;
    @SerializedName("email_personal")
    public String  email_personal;
    @SerializedName("specialization_lookup_no_fk")
    public String  specialization_lookup_no_fk;
    @SerializedName("specialization")
    public String  specialization;
    @SerializedName("serviceunit_no_fk")
    public String  serviceunit_no_fk;
    @SerializedName("serviceunit_name")
    public String  serviceunit_name;
    @SerializedName("gender")
    public String  gender;
    @SerializedName("gender_txt")
    public String  gender_txt;
    @SerializedName("marital_status")
    public String  marital_status;
    @SerializedName("marital_status_id")
    public String  marital_status_id;
    @SerializedName("status")
    public String  status;
    @SerializedName("au_entry_by")
    public String  au_entry_by;

    @SerializedName("au_entry_at")
    public String  au_entry_at;
    @SerializedName("au_entry_session")
    public String  au_entry_session;
    @SerializedName("au_entry_hospital_pk_no")
    public String  au_entry_hospital_pk_no;
    @SerializedName("au_update_by")
    public String  au_update_by;
    @SerializedName("au_update_at")
    public String  au_update_at;
    @SerializedName("au_update_session")
    public String  au_update_session;
    @SerializedName("join_date")
    public String  join_date;
    @SerializedName("jobtitle_lookup_no_fk")
    public String  jobtitle_lookup_no_fk;
    @SerializedName("job_title")
    public String  job_title;
    @SerializedName("job_type_lookup_no_fk")
    public String  job_type_lookup_no_fk;
    @SerializedName("job_type")
    public String  job_type;
    @SerializedName("hr_type_lookup_no_fk")
    public String  hr_type_lookup_no_fk;

    @SerializedName("hr_type")
    public String  hr_type;
    @SerializedName("emp_type_lookup_no_fk")
    public String  emp_type_lookup_no_fk;
    @SerializedName("emp_type")
    public String  emp_type;
    @SerializedName("blood_group_lookup_no_fk")
    public String  blood_group_lookup_no_fk;
    @SerializedName("blood_group")
    public String  blood_group;

    @SerializedName("religion_lookup_no_fk")
    public String  religion_lookup_no_fk;
    @SerializedName("religion")
    public String  religion;
    @SerializedName("opd_consultation_ind")
    public String  opd_consultation_ind;
    @SerializedName("avg_duration_min")
    public String  avg_duration_min;
    @SerializedName("avg_load_per_day")
    public String  avg_load_per_day;

    @SerializedName("block_load_per_day")
    public String  block_load_per_day;
    @SerializedName("ipd_consultation_ind")
    public String  ipd_consultation_ind;
    @SerializedName("emr_consultation_ind")
    public String  emr_consultation_ind;
    @SerializedName("primary_doctor_ind")
    public String  primary_doctor_ind;
    @SerializedName("duty_doctor_ind")
    public String  duty_doctor_ind;

    @SerializedName("anaestesiologis_ind")
    public String  anaestesiologis_ind;
    @SerializedName("nurse_ind")
    public String  nurse_ind;
    @SerializedName("sample_coll_ind")
    public String  sample_coll_ind;
    @SerializedName("surgeon_ind")
    public String  surgeon_ind;
    @SerializedName("room_no_fk")
    public String  room_no_fk;

    @SerializedName("daycare_ind")
    public String  daycare_ind;
    @SerializedName("offday_remarks")
    public String  offday_remarks;
    @SerializedName("qualification")
    public String  qualification;
    @SerializedName("degree1")
    public String  degree1;
    @SerializedName("degree2")
    public String  degree2;

    @SerializedName("degree3")
    public String  degree3;
    @SerializedName("degree4")
    public String  degree4;
    @SerializedName("pe_addr1")
    public String  pe_addr1;
    @SerializedName("photo_name")
    public String  photo_name;
    @SerializedName("doctor_ind")
    public String  doctor_ind;
    @SerializedName("pharmacy_ind")
    public String  pharmacy_ind;
}

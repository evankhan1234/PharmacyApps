package com.nextgenit.pharmacyapp.NetworkModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DoctorList implements Parcelable {
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

    protected DoctorList(Parcel in) {
        person_no_pk = in.readInt();
        person_code = in.readString();
        full_name = in.readString();
        dob = in.readString();
        doctor_signature = in.readString();
        phone_mobile = in.readString();
        email_personal = in.readString();
        specialization_lookup_no_fk = in.readString();
        specialization = in.readString();
        serviceunit_no_fk = in.readString();
        serviceunit_name = in.readString();
        gender = in.readString();
        gender_txt = in.readString();
        marital_status = in.readString();
        marital_status_id = in.readString();
        status = in.readString();
        au_entry_by = in.readString();
        au_entry_at = in.readString();
        au_entry_session = in.readString();
        au_entry_hospital_pk_no = in.readString();
        au_update_by = in.readString();
        au_update_at = in.readString();
        au_update_session = in.readString();
        join_date = in.readString();
        jobtitle_lookup_no_fk = in.readString();
        job_title = in.readString();
        job_type_lookup_no_fk = in.readString();
        job_type = in.readString();
        hr_type_lookup_no_fk = in.readString();
        hr_type = in.readString();
        emp_type_lookup_no_fk = in.readString();
        emp_type = in.readString();
        blood_group_lookup_no_fk = in.readString();
        blood_group = in.readString();
        religion_lookup_no_fk = in.readString();
        religion = in.readString();
        opd_consultation_ind = in.readString();
        avg_duration_min = in.readString();
        avg_load_per_day = in.readString();
        block_load_per_day = in.readString();
        ipd_consultation_ind = in.readString();
        emr_consultation_ind = in.readString();
        primary_doctor_ind = in.readString();
        duty_doctor_ind = in.readString();
        anaestesiologis_ind = in.readString();
        nurse_ind = in.readString();
        sample_coll_ind = in.readString();
        surgeon_ind = in.readString();
        room_no_fk = in.readString();
        daycare_ind = in.readString();
        offday_remarks = in.readString();
        qualification = in.readString();
        degree1 = in.readString();
        degree2 = in.readString();
        degree3 = in.readString();
        degree4 = in.readString();
        pe_addr1 = in.readString();
        photo_name = in.readString();
        doctor_ind = in.readString();
        pharmacy_ind = in.readString();
    }

    public static final Creator<DoctorList> CREATOR = new Creator<DoctorList>() {
        @Override
        public DoctorList createFromParcel(Parcel in) {
            return new DoctorList(in);
        }

        @Override
        public DoctorList[] newArray(int size) {
            return new DoctorList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(person_no_pk);
        dest.writeString(person_code);
        dest.writeString(full_name);
        dest.writeString(dob);
        dest.writeString(doctor_signature);
        dest.writeString(phone_mobile);
        dest.writeString(email_personal);
        dest.writeString(specialization_lookup_no_fk);
        dest.writeString(specialization);
        dest.writeString(serviceunit_no_fk);
        dest.writeString(serviceunit_name);
        dest.writeString(gender);
        dest.writeString(gender_txt);
        dest.writeString(marital_status);
        dest.writeString(marital_status_id);
        dest.writeString(status);
        dest.writeString(au_entry_by);
        dest.writeString(au_entry_at);
        dest.writeString(au_entry_session);
        dest.writeString(au_entry_hospital_pk_no);
        dest.writeString(au_update_by);
        dest.writeString(au_update_at);
        dest.writeString(au_update_session);
        dest.writeString(join_date);
        dest.writeString(jobtitle_lookup_no_fk);
        dest.writeString(job_title);
        dest.writeString(job_type_lookup_no_fk);
        dest.writeString(job_type);
        dest.writeString(hr_type_lookup_no_fk);
        dest.writeString(hr_type);
        dest.writeString(emp_type_lookup_no_fk);
        dest.writeString(emp_type);
        dest.writeString(blood_group_lookup_no_fk);
        dest.writeString(blood_group);
        dest.writeString(religion_lookup_no_fk);
        dest.writeString(religion);
        dest.writeString(opd_consultation_ind);
        dest.writeString(avg_duration_min);
        dest.writeString(avg_load_per_day);
        dest.writeString(block_load_per_day);
        dest.writeString(ipd_consultation_ind);
        dest.writeString(emr_consultation_ind);
        dest.writeString(primary_doctor_ind);
        dest.writeString(duty_doctor_ind);
        dest.writeString(anaestesiologis_ind);
        dest.writeString(nurse_ind);
        dest.writeString(sample_coll_ind);
        dest.writeString(surgeon_ind);
        dest.writeString(room_no_fk);
        dest.writeString(daycare_ind);
        dest.writeString(offday_remarks);
        dest.writeString(qualification);
        dest.writeString(degree1);
        dest.writeString(degree2);
        dest.writeString(degree3);
        dest.writeString(degree4);
        dest.writeString(pe_addr1);
        dest.writeString(photo_name);
        dest.writeString(doctor_ind);
        dest.writeString(pharmacy_ind);
    }
}

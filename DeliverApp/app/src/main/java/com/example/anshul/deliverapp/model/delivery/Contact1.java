package com.example.anshul.deliverapp.model.delivery;

/**
 * Created by vishwa.r on 3/6/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Success",
        "job_type",
        "job_id",
        "drop_cust_name",
        "drop_cust_no",
        "drop_address",
        "drop_pincode",
        "drop_lat_long",
        "drop_cod",
        "item_description",
        "spacial_instruction"
})
public class Contact1 implements Parcelable{

    @JsonProperty("Success")
    private Boolean success;
    @JsonProperty("job_type")
    private String jobType;
    @JsonProperty("job_id")
    private String jobId;
    @JsonProperty("drop_cust_name")
    private String dropCustName;
    @JsonProperty("drop_cust_no")
    private String dropCustNo;
    @JsonProperty("drop_address")
    private String dropAddress;
    @JsonProperty("drop_pincode")
    private String dropPincode;
    @JsonProperty("drop_lat_long")
    private String dropLatLong;
    @JsonProperty("drop_cod")
    private String dropCod;
    @JsonProperty("item_description")
    private String itemDescription;
    @JsonProperty("spacial_instruction")
    private String spacialInstruction;


    protected Contact1(Parcel in) {
        jobType = in.readString();
        jobId = in.readString();
        dropCustName = in.readString();
        dropCustNo = in.readString();
        dropAddress = in.readString();
        dropPincode = in.readString();
        dropLatLong = in.readString();
        dropCod = in.readString();
        itemDescription = in.readString();
        spacialInstruction = in.readString();
    }

    public static final Creator<Contact1> CREATOR = new Creator<Contact1>() {
        @Override
        public Contact1 createFromParcel(Parcel in) {
            return new Contact1(in);
        }

        @Override
        public Contact1[] newArray(int size) {
            return new Contact1[size];
        }
    };

    @JsonProperty("Success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("Success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("job_type")
    public String getJobType() {
        return jobType;
    }

    @JsonProperty("job_type")
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @JsonProperty("job_id")
    public String getJobId() {
        return jobId;
    }

    @JsonProperty("job_id")
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @JsonProperty("drop_cust_name")
    public String getDropCustName() {
        return dropCustName;
    }

    @JsonProperty("drop_cust_name")
    public void setDropCustName(String dropCustName) {
        this.dropCustName = dropCustName;
    }

    @JsonProperty("drop_cust_no")
    public String getDropCustNo() {
        return dropCustNo;
    }

    @JsonProperty("drop_cust_no")
    public void setDropCustNo(String dropCustNo) {
        this.dropCustNo = dropCustNo;
    }

    @JsonProperty("drop_address")
    public String getDropAddress() {
        return dropAddress;
    }

    @JsonProperty("drop_address")
    public void setDropAddress(String dropAddress) {
        this.dropAddress = dropAddress;
    }

    @JsonProperty("drop_pincode")
    public String getDropPincode() {
        return dropPincode;
    }

    @JsonProperty("drop_pincode")
    public void setDropPincode(String dropPincode) {
        this.dropPincode = dropPincode;
    }

    @JsonProperty("drop_lat_long")
    public String getDropLatLong() {
        return dropLatLong;
    }

    @JsonProperty("drop_lat_long")
    public void setDropLatLong(String dropLatLong) {
        this.dropLatLong = dropLatLong;
    }

    @JsonProperty("drop_cod")
    public String getDropCod() {
        return dropCod;
    }

    @JsonProperty("drop_cod")
    public void setDropCod(String dropCod) {
        this.dropCod = dropCod;
    }

    @JsonProperty("item_description")
    public String getItemDescription() {
        return itemDescription;
    }

    @JsonProperty("item_description")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @JsonProperty("spacial_instruction")
    public String getSpacialInstruction() {
        return spacialInstruction;
    }

    @JsonProperty("spacial_instruction")
    public void setSpacialInstruction(String spacialInstruction) {
        this.spacialInstruction = spacialInstruction;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(jobType);
        dest.writeString(jobId);
        dest.writeString(dropCustName);
        dest.writeString(dropCustNo);
        dest.writeString(dropAddress);
        dest.writeString(dropPincode);
        dest.writeString(dropLatLong);
        dest.writeString(dropCod);
        dest.writeString(itemDescription);
        dest.writeString(spacialInstruction);
    }


}
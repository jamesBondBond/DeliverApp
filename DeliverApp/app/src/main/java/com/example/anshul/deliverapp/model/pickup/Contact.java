package com.example.anshul.deliverapp.model.pickup;

/**
 * Created by Anshul on 3/5/2017.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Success",
        "job_type",
        "job_id",
        "pickup_cust_name",
        "pickup_cust_no",
        "pickup_address",
        "pickup_pincode",
        "pickup_lat_long",
        "pickup_cod",
        "item_description",
        "spacial_instruction",
        "date",
        "route_id"
})
public class Contact implements Serializable{

    @JsonProperty("Success")
    private Boolean success;
    @JsonProperty("job_type")
    private String jobType;
    @JsonProperty("job_id")
    private String jobId;
    @JsonProperty("pickup_cust_name")
    private String pickupCustName;
    @JsonProperty("pickup_cust_no")
    private String pickupCustNo;
    @JsonProperty("pickup_address")
    private String pickupAddress;
    @JsonProperty("pickup_pincode")
    private String pickupPincode;
    @JsonProperty("pickup_lat_long")
    private String pickupLatLong;
    @JsonProperty("pickup_cod")
    private String pickupCod;
    @JsonProperty("item_description")
    private String itemDescription;
    @JsonProperty("spacial_instruction")
    private String spacialInstruction;
    @JsonProperty("drop_cod")
    private String dropCod="";
    @JsonProperty("date")
    private String date;
    @JsonProperty("route_id")
    private String routeId;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDropCod() {
        return dropCod;
    }

    public void setDropCod(String dropCod) {
        this.dropCod = dropCod;
    }

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

    @JsonProperty("pickup_cust_name")
    public String getPickupCustName() {
        return pickupCustName;
    }

    @JsonProperty("pickup_cust_name")
    public void setPickupCustName(String pickupCustName) {
        this.pickupCustName = pickupCustName;
    }

    @JsonProperty("pickup_cust_no")
    public String getPickupCustNo() {
        return pickupCustNo;
    }

    @JsonProperty("pickup_cust_no")
    public void setPickupCustNo(String pickupCustNo) {
        this.pickupCustNo = pickupCustNo;
    }

    @JsonProperty("pickup_address")
    public String getPickupAddress() {
        return pickupAddress;
    }

    @JsonProperty("pickup_address")
    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    @JsonProperty("pickup_pincode")
    public String getPickupPincode() {
        return pickupPincode;
    }

    @JsonProperty("pickup_pincode")
    public void setPickupPincode(String pickupPincode) {
        this.pickupPincode = pickupPincode;
    }

    @JsonProperty("pickup_lat_long")
    public String getPickupLatLong() {
        return pickupLatLong;
    }

    @JsonProperty("pickup_lat_long")
    public void setPickupLatLong(String pickupLatLong) {
        this.pickupLatLong = pickupLatLong;
    }

    @JsonProperty("pickup_cod")
    public String getPickupCod() {
        return pickupCod;
    }

    @JsonProperty("pickup_cod")
    public void setPickupCod(String pickupCod) {
        this.pickupCod = pickupCod;
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

}
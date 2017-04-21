package com.example.anshul.deliverapp.model.login;

/**
 * Created by Anshul on 3/5/2017.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "rider_contact_no"
})
public class User {

    @JsonProperty("name")
    private String name;
    @JsonProperty("rider_contact_no")
    private String riderContactNo;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("rider_contact_no")
    public String getRiderContactNo() {
        return riderContactNo;
    }

    @JsonProperty("rider_contact_no")
    public void setRiderContactNo(String riderContactNo) {
        this.riderContactNo = riderContactNo;
    }

}


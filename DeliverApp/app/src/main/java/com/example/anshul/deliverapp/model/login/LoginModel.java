package com.example.anshul.deliverapp.model.login;

/**
 * Created by Anshul on 3/5/2017.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Success",
        "uid",
        "user"
})
public class LoginModel {

    @JsonProperty("Success")
    private Boolean success;
    @JsonProperty("uid")
    private String uid;
    @JsonProperty("user")
    private User user;

    @JsonProperty("Success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("Success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(String uid) {
        this.uid = uid;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

}
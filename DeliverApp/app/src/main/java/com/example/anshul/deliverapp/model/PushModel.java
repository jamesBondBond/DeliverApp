package com.example.anshul.deliverapp.model;

/**
 * Created by anshul.g on 3/7/2017.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "error",
        "Success",
        "job_id"
})
public class PushModel {

    @JsonProperty("error")
    private Boolean error;
    @JsonProperty("Success")
    private Boolean success;
    @JsonProperty("job_id")
    private String jobId;

    @JsonProperty("error")
    public Boolean getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Boolean error) {
        this.error = error;
    }

    @JsonProperty("Success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("Success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("job_id")
    public String getJobId() {
        return jobId;
    }

    @JsonProperty("job_id")
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

}
package com.example.anshul.deliverapp.model.delivery;

/**
 * Created by vishwa.r on 3/6/2017.
 */

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Contacts"
})
public class DeliverModel implements Serializable {

    @JsonProperty("Contacts")
    private List<Contact> contacts = null;

    @JsonProperty("Contacts")
    public List<Contact> getContacts() {
        return contacts;
    }

    @JsonProperty("Contacts")
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}
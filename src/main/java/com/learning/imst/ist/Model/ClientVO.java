package com.learning.imst.ist.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class ClientVO {
    @JsonProperty("client_id")
    Integer client_id;
    @JsonProperty("client_name")
    String client_name;
    @JsonProperty("client_address")
    String client_address;
    @JsonProperty("create_ts")
    Timestamp create_ts;

    public void setClient_id( Integer client_id) {
        this.client_id = client_id;
    }

    public void setClient_name( String client_name) {
        this.client_name = client_name;
    }

    public void setClient_address( String client_address) {
        this.client_address = client_address;
    }

    public void setCreate_ts( Timestamp create_ts) {
        this.create_ts = create_ts;
    }

    public Integer getClient_id() {
        return this.client_id;
    }

    public String getClient_name() {
        return this.client_name;
    }

    public String getClient_address() {
        return this.client_address;
    }

    public Timestamp getCreate_ts() {
        return this.create_ts;
    }

}

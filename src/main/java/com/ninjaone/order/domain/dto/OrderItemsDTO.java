package com.ninjaone.order.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

public class OrderItemsDTO {

    @JsonProperty("device_id")
    private String deviceId;
    private Set<String> services;

    public OrderItemsDTO(){
        this.services = new HashSet<>();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Set<String> getServices() {
        return services;
    }

    public void addServices(Set<String> services) {
        this.services = services;
    }

}

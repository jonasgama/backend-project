package com.ninjaone.backendinterviewproject.core.dto;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PurchaseItemsDTO {

    private String deviceId;
    private Set<String> services;

    public PurchaseItemsDTO(){
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

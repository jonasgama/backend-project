package com.ninjaone.backendinterviewproject.dto;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseItemsDTO that = (PurchaseItemsDTO) o;
        return Objects.equals(deviceId, that.deviceId) && Objects.equals(services, that.services);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, services);
    }
}

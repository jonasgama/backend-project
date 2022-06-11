package com.ninjaone.backendinterviewproject.dto;

public class PurchaseDTO {

    private String customerId;
    private String deviceId;
    private String serviceForDevice;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getServiceForDevice() {
        return serviceForDevice;
    }

    public void setServiceForDevice(String serviceForDevice) {
        this.serviceForDevice = serviceForDevice;
    }
}

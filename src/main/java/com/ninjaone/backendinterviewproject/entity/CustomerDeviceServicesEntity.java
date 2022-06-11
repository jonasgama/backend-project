package com.ninjaone.backendinterviewproject.entity;

import javax.persistence.*;

@Entity
@Table(name="CUSTOMER_DEVICE_SERVICES")
public class CustomerDeviceServicesEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private CustomerDevicesEntity devicePurchased;
    @OneToOne
    private ServiceEntity hiredService;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerDevicesEntity getDevicePurchased() {
        return devicePurchased;
    }

    public void setDevicePurchased(CustomerDevicesEntity devicePurchased) {
        this.devicePurchased = devicePurchased;
    }

    public ServiceEntity getHiredService() {
        return hiredService;
    }

    public void setHiredService(ServiceEntity hiredService) {
        this.hiredService = hiredService;
    }

}

package com.ninjaone.customer.infra.entity;

import com.ninjaone.catalog.infra.entity.ServiceEntity;

import javax.persistence.*;
import java.util.UUID;

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
    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

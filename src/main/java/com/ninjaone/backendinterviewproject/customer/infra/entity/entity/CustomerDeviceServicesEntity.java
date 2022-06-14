package com.ninjaone.backendinterviewproject.customer.infra.entity.entity;

import com.ninjaone.backendinterviewproject.catalog.infra.entity.entity.ServiceEntity;

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
    private UUID traceId;

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

    public UUID getTraceId() {
        return traceId;
    }

    public void setTraceId(UUID traceId) {
        this.traceId = traceId;
    }
}

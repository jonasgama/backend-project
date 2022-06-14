package com.ninjaone.backendinterviewproject.customer.infra.entity.entity;

import com.ninjaone.backendinterviewproject.catalog.infra.entity.entity.DeviceEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="CUSTOMER_DEVICES")
public class CustomerDevicesEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private CustomerEntity customer;
    @ManyToOne
    private DeviceEntity device;
    private UUID traceId;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public DeviceEntity getDevice() {
        return device;
    }

    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    public UUID getTraceId() {
        return traceId;
    }

    public void setTraceId(UUID traceId) {
        this.traceId = traceId;
    }
}

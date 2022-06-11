package com.ninjaone.backendinterviewproject.entity;

import javax.persistence.*;

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
}

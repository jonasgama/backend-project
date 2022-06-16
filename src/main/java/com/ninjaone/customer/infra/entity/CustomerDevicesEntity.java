package com.ninjaone.customer.infra.entity;

import com.ninjaone.catalog.infra.entity.DeviceEntity;

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
    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

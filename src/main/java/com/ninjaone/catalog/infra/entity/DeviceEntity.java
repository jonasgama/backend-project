package com.ninjaone.catalog.infra.entity;


import javax.persistence.*;

@Table(name="DEVICES")
@Entity
public class DeviceEntity extends CatalogEntity{

    public DeviceEntity(){}

    public DeviceEntity(String item,
                         Double price,
                         String compatibility){
        super(item, price, compatibility);
    }

}

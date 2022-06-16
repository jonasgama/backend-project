package com.ninjaone.catalog.infra.entity;

import javax.persistence.*;

@Entity
@Table(name="SERVICES")
public class ServiceEntity extends CatalogEntity{
    public ServiceEntity(){}

    public ServiceEntity(String item,
                        Double price,
                        String compatibility){
        super(item, price, compatibility);
    }

}

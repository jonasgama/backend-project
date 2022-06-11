package com.ninjaone.backendinterviewproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMERS")
public class CustomerEntity {

    @Id
    private String name;

}

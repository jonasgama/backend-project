package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.CustomerRepository;
import com.ninjaone.backendinterviewproject.dto.CustomerDTO;
import com.ninjaone.backendinterviewproject.entity.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(
            CustomerRepository customerRepository
    ) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity find(String customerId) throws Exception {
        Optional<CustomerEntity> entity = customerRepository.findById(customerId);
        if(!entity.isPresent()){
            throw new Exception("customer not found");
        }
        return entity.get();
    }
}

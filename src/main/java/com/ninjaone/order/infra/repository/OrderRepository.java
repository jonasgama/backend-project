package com.ninjaone.order.infra.repository;

import com.ninjaone.order.infra.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
    Set<OrderEntity> findByCustomerNameAndCreatedAtBetween(String customerName, LocalDateTime init, LocalDateTime end);
}

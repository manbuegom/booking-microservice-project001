package com.manbuegom.booking_microservice.repository;

import com.manbuegom.booking_microservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

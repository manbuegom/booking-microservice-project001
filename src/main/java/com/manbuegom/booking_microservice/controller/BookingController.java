package com.manbuegom.booking_microservice.controller;

import com.manbuegom.booking_microservice.client.StockClient;
import com.manbuegom.booking_microservice.dto.OrderDTO;
import com.manbuegom.booking_microservice.entity.Order;
import com.manbuegom.booking_microservice.repository.OrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StockClient stockClient;

    @PostMapping("/order")
    @HystrixCommand(fallbackMethod = "fallbackToStockService")
    public String saveOrder(@RequestBody OrderDTO orderDTO){

        boolean inStock = orderDTO.getOrderItems().stream()
                .allMatch(orderItem -> stockClient.stockAvaliable(orderItem.getCode()));

        if(inStock){
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderItems(orderDTO.getOrderItems());

            orderRepository.save(order);
            return "Order saved";
        } else {
            return "Order cannot be saved";
        }


    }

    private String fallBackToStockService(){
        return "Something went wrong, please after some time";
    }
}

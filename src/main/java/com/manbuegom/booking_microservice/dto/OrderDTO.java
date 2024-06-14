package com.manbuegom.booking_microservice.dto;

import com.manbuegom.booking_microservice.entity.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private List<OrderItem> orderItems;

}

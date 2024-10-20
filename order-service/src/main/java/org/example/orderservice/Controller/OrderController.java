package org.example.orderservice.Controller;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.Service.OrderService;
import org.example.orderservice.dto.OrderRequest;
import org.example.orderservice.dto.OrderResponse;
import org.example.orderservice.dto.OrderResponseToCustomer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/placeOrder")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseToCustomer placeOrder(@RequestBody OrderRequest request){
       OrderResponse orderResponse = orderService.placeOrder(request);
        return new OrderResponseToCustomer("Order Placed Successfully", orderResponse.id(), orderResponse.orderNumber());
    }

}

package org.example.orderservice.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.Client.InventoryClient;
import org.example.orderservice.dto.OrderResponse;
import org.example.orderservice.Model.Order;
import org.example.orderservice.Repository.OrderRepository;
import org.example.orderservice.dto.OrderRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    public OrderResponse placeOrder(OrderRequest request){
       var isProductInStock = inventoryClient.isInStock(request.skuCode(), request.quantity());


       if(isProductInStock) {
           Order order = new Order();
           order.setOrderNumber(UUID.randomUUID().toString());
           order.setPrice(request.price());
           order.setSkuCode(request.skuCode());
           order.setQuantity(request.quantity());

           orderRepository.save(order);
           log.info("Order placed successfully. Order Id: " + order.getId());
           return new OrderResponse(order.getId(), order.getOrderNumber());
       }else{
           throw  new RuntimeException("Not in stock");
       }
    }
}

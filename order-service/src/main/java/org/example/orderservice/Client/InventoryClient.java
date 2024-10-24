package org.example.orderservice.Client;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {
        Logger log = LoggerFactory.getLogger(InventoryClient.class);
    @GetExchange("/api/inventory") //OpenFeign -> RestClient
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    boolean isInStock(@RequestParam String skuCode , @RequestParam Integer quantity);

    default boolean fallbackMethod(String skuCode ,Integer quantity , Throwable throwable){
        log.info("Cannot get skuCode {} , failure reason {}",skuCode,throwable.getMessage());
        return false;
    }
}

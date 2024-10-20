package org.example.inventoryservice.Service;

import lombok.RequiredArgsConstructor;
import org.example.inventoryservice.Model.Inventory;
import org.example.inventoryservice.Repo.InventoryRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepo inventoryRepo;


    public boolean isInStock(String skuCode , Integer quantity){
       return inventoryRepo.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);
    }
}

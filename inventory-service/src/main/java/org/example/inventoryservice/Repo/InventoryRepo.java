package org.example.inventoryservice.Repo;

import org.example.inventoryservice.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Long> {

  boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode , Integer quantity);
}

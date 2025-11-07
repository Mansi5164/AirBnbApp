package com.coding.shuttle.airBnb.repository;

import com.coding.shuttle.airBnb.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}

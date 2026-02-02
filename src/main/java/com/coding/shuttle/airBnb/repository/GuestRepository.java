package com.coding.shuttle.airBnb.repository;

import com.coding.shuttle.airBnb.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
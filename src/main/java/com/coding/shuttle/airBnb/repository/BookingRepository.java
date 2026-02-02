package com.coding.shuttle.airBnb.repository;

import com.coding.shuttle.airBnb.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

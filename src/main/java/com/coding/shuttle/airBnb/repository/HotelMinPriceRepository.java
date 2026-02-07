package com.coding.shuttle.airBnb.repository;
import com.coding.shuttle.airBnb.dto.HotelPriceDTO;
import com.coding.shuttle.airBnb.entity.Hotel;
import com.coding.shuttle.airBnb.entity.HotelMinPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface HotelMinPriceRepository extends JpaRepository<HotelMinPrice, Long> {

    @Query("""
           SELECT new com.coding.shuttle.airBnb.dto.HotelPriceDTO(i.hotel, AVG(i.price))
           FROM HotelMinPrice i
           WHERE i.hotel.city = :city
               AND i.date BETWEEN :startDate AND :endDate
               AND i.hotel.active = true
           GROUP BY i.hotel
           """)
    Page<HotelPriceDTO> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );


    Optional<HotelMinPrice> findByHotelAndDate(Hotel hotel, LocalDate date);
}

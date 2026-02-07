package com.coding.shuttle.airBnb.repository;

import com.coding.shuttle.airBnb.entity.Hotel;
import com.coding.shuttle.airBnb.entity.Inventory;
import com.coding.shuttle.airBnb.entity.Room;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    void deleteByRoom(Room room);


    /*
    Ye query Inventory table se aise unique hotels nikalti hai jinke paas given city me
    startDate se endDate tak har din ke liye required number of rooms available ho. Isme
    pehle check hota hai ki inventory closed na ho, phir availability verify hoti hai using
    total rooms minus booked rooms, aur uske baad GROUP BY hotel aur room ke through dates
    ko count kiya jaata hai. HAVING COUNT(i.date) = dateCount ensure karta hai ki user ke
    poore stay duration ke sabhi din ke liye room available ho, na ki sirf ek-do din ke liye.
    DISTINCT use karke duplicate hotels avoid kiye jaate hain aur result pagination ke saath
    return hota hai, jo real booking systems ke liye scalable hota hai.
     */
    @Query("""
           SELECT DISTINCT i.hotel
           FROM Inventory i
           WHERE i.city = :city
               AND i.date BETWEEN :startDate AND :endDate
               AND i.closed = false
               AND (i.totalCount - i.bookedCount - i.reservedCount) >= :roomsCount
           GROUP BY i.hotel , i.room
           HAVING COUNT(i.date) = :dateCount
           """)
    Page<Hotel> findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount,
            @Param("dateCount") Long dateCount,
            Pageable pageable
    );

    @Query("""
            SELECT i
            FROM Inventory i
            WHERE i.room.id = :roomId
               AND i.date BETWEEN :startDate AND :endDate
               AND i.closed = false
               AND (i.totalCount - i.bookedCount - i.reservedCount) >= :roomsCount
           """)
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Inventory> findAndLockAvailableInventory(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("roomsCount") Integer roomsCount
    );

    List<Inventory> findByHotelAndDateBetween(Hotel hotel, LocalDate startDate, LocalDate endDate);
}

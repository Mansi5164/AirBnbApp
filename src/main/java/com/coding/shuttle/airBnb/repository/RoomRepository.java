package com.coding.shuttle.airBnb.repository;

import com.coding.shuttle.airBnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}

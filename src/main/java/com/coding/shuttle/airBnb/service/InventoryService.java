package com.coding.shuttle.airBnb.service;

import com.coding.shuttle.airBnb.dto.HotelDto;
import com.coding.shuttle.airBnb.dto.HotelPriceDTO;
import com.coding.shuttle.airBnb.dto.HotelSearchRequest;
import com.coding.shuttle.airBnb.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDTO> searchHotels(HotelSearchRequest hotelSearchRequest);
}

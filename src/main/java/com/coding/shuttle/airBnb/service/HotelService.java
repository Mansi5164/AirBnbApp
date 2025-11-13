package com.coding.shuttle.airBnb.service;

import com.coding.shuttle.airBnb.dto.HotelDto;
import com.coding.shuttle.airBnb.entity.Hotel;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);

}
